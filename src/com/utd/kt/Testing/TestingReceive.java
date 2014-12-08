package com.utd.kt.Testing;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

import com.sun.nio.sctp.SctpChannel;
import com.utd.kt.Testing.TestingMain;
import com.utd.kt.sendreceivecheckpoint.CheckpointSendReceive;
import com.utd.kt.utils.Message;

public class TestingReceive {

	public static void receive(){
		ByteBuffer byteBuffer = ByteBuffer.allocate(10000);
		try {
			while (true) {
				synchronized (CheckpointSendReceive.obj) {
				//Thread.sleep(1000);
					for (int id : TestingMain.connectionSocket.keySet()) {
						SctpChannel schnl = TestingMain.connectionSocket.get(id);

						byteBuffer.clear();
						schnl.configureBlocking(false);
						schnl.receive(byteBuffer, null, null);
						byteBuffer.flip();
						if (byteBuffer.remaining() > 0) {
							Message receivedMsg = (Message) deserialize(byteBuffer
									.array());
							int myClk= TestingMain.finalClks.get(TestingMain.myNodeId);
							int rcdClk= receivedMsg.getSendClk();
							if(rcdClk>myClk){
								System.out.println("GLOBALLY INCONSISTENT");
							}
							else{
								System.out.println("GLOBALLY CONSISTENT");
							}
						}
					}
				}
			}
		}
			 catch (IOException e) {
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
