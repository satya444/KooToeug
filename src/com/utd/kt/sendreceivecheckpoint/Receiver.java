package com.utd.kt.sendreceivecheckpoint;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.util.Map;

import com.sun.nio.sctp.SctpChannel;
import com.utd.kt.main.AosMain;
import com.utd.kt.networkprotocol.ConnectionManager;
import com.utd.kt.utils.Message;
import com.utd.kt.vclsrfls.VectorClockLLRFLS;

public class Receiver {

	public synchronized void receive() {
		ByteBuffer byteBuffer = ByteBuffer.allocate(10000);
		try {
			while (true) {
				synchronized (SendReceiveCheckpoint.obj) {
					for (int id : AosMain.connectionSocket.keySet()) {
						SctpChannel schnl = AosMain.connectionSocket.get(id);

						byteBuffer.clear();
						schnl.configureBlocking(false);
						schnl.receive(byteBuffer, null, null);
						byteBuffer.flip();

						if (byteBuffer.remaining() > 0) {
							Message receivedMsg = (Message) deserialize(byteBuffer
									.array());
							if (receivedMsg.getMsgType() == 0) {
								System.out.println("RECEIVED MSG FROM "
										+ receivedMsg.getSrcId());
								Map<Integer, Integer> rcdVc = receivedMsg
										.getVc();
								for (Integer itr : VectorClockLLRFLS.vc
										.keySet()) {
									int rvdclkVal = rcdVc.get(itr);
									int myclkVal = VectorClockLLRFLS.vc
											.get(itr);
									int maxClk = Math.max(rvdclkVal, myclkVal);
									VectorClockLLRFLS.vc.put(itr, maxClk);
								}
								int myClk = VectorClockLLRFLS.vc
										.get(AosMain.myNodeId);
								myClk++;
								VectorClockLLRFLS.vc.put(AosMain.myNodeId,
										myClk);
								int llrVal = receivedMsg.getSeqNo();
								VectorClockLLRFLS.llr.put(
										receivedMsg.getSrcId(), llrVal);
								ConnectionManager.printVectors();
								System.out.println("** MY CLOCK **");
								for (Integer itr : VectorClockLLRFLS.vc
										.keySet()) {
									System.out.println(itr + " -- "
											+ VectorClockLLRFLS.vc.get(itr));
								}
							}
							else{
								int myClk = VectorClockLLRFLS.vc
										.get(AosMain.myNodeId);
								myClk++;
								VectorClockLLRFLS.vc.put(AosMain.myNodeId,
										myClk);
								int rcvdllr= receivedMsg.getLlrSendVal();
								int myfls= VectorClockLLRFLS.fls.get(receivedMsg.getSrcId());
								if(rcvdllr>=myfls && myfls!=0){
									
									//code to send llr to other neighbors
								}
							}
							byteBuffer.clear();
						}
					}
					// System.out.println("OUT OF FOR LOOP*****************");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object deserialize(byte[] array) {
		ObjectInputStream ois;
		ByteArrayInputStream bis = new ByteArrayInputStream(array);
		try {
			ois = new ObjectInputStream(bis);
			return ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
