package com.utd.kt.sendreceivecheckpoint;

public class SendReceive implements Runnable {

	public static void initiateSendReceive(){
		Thread t= new Thread(new SendReceive());
		t.start();
		Sender.initiateSends();
	}
	@Override
	public void run() {
		Receiver.receive();
		
	}

	
}
