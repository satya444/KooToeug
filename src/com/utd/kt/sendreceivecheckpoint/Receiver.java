package com.utd.kt.sendreceivecheckpoint;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.util.Map;

import com.sun.nio.sctp.SctpChannel;
import com.utd.kt.VcLlrFlc.VectorLlrFlsLls;
import com.utd.kt.main.AosMain;
import com.utd.kt.utils.FileFeatures;
import com.utd.kt.utils.Message;

public class Receiver {

	public static void receive() {
		ByteBuffer byteBuffer = ByteBuffer.allocate(10000);
		try {
			while (true) {
				synchronized (CheckpointSendReceive.obj) {
				//Thread.sleep(1000);
					for (int id : AosMain.connectionSocket.keySet()) {
						SctpChannel schnl = AosMain.connectionSocket.get(id);

						byteBuffer.clear();
						schnl.configureBlocking(false);
						schnl.receive(byteBuffer, null, null);
						byteBuffer.flip();
						if (byteBuffer.remaining() > 0) {
							Message receivedMsg = (Message) deserialize(byteBuffer
									.array());
							int myclk = VectorLlrFlsLls.vc
									.get(AosMain.myNodeId);
							myclk++;
							VectorLlrFlsLls.vc.put(AosMain.myNodeId, myclk);
							//System.out.println("RECEIVED MSG FROM "+receivedMsg.getSrcId());
							if (receivedMsg.getMsgType() == 0) {
								Map<Integer, Integer> rcdVcClk = receivedMsg
										.getVc();
								for (Integer itr : VectorLlrFlsLls.vc.keySet()) {
									int destClk = VectorLlrFlsLls.vc.get(itr);
									int rcdClk = rcdVcClk.get(itr);
									int max = Math.max(destClk, rcdClk);
									VectorLlrFlsLls.vc.put(itr, max);
									int llrVal= receivedMsg.getSeqNo();
									VectorLlrFlsLls.llr.put(receivedMsg.getSrcId(), llrVal);
									//CheckpointSendReceive.print();
								}
							} else if (receivedMsg.getMsgType() == 1) {
								int myFls = VectorLlrFlsLls.fls.get(receivedMsg
										.getSrcId());
								int rcdLlr = VectorLlrFlsLls.llr
										.get(receivedMsg.getSrcId());
								if (rcdLlr >= myFls && myFls != 0) {
									System.out.println("TAKING CHECKPOINT");
									VectorLlrFlsLls.checkPointVc.clear();
									VectorLlrFlsLls.checkPointVc.putAll(VectorLlrFlsLls.vc);
									VectorLlrFlsLls.lls.clear();
									VectorLlrFlsLls.lls.putAll(VectorLlrFlsLls.seqNo);
									FileFeatures.writeAll();
									CheckpointSendReceive.print();
									
									Sender.sendLlrToRest(receivedMsg
											.getAllSources());
									VectorLlrFlsLls.reset();
								}
								else{
									//FileFeatures.writeAll();
								}
							} else if (receivedMsg.getMsgType() == 2) {
								int myllr = VectorLlrFlsLls.llr.get(receivedMsg
										.getSrcId());
								int rcdLls = VectorLlrFlsLls.lls
										.get(receivedMsg.getSrcId());
								if (myllr > rcdLls) {
									System.out.println("ROLLBACK STARTED");
									VectorLlrFlsLls.recoverRollback(receivedMsg.getAllSources());
									Sender.sendLlsToRest(receivedMsg
											.getAllSources());
								}
								else{
									//FileFeatures.writeAll();
								}
							}
							byteBuffer.clear();
							Thread.sleep(1000);
						}
					}
					// System.out.println("OUT OF FOR LOOP*****************");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Object deserialize(byte[] array) {
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
