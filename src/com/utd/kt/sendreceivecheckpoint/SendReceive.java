package com.utd.kt.sendreceivecheckpoint;

public class SendReceive implements Runnable {


	public void initiateSendReceive() {
		
		
			Thread t = new Thread(new SendReceive());
			t.start();
			Sender s = new Sender();
			s.invokeAllSends();
	}

	@Override
	public void run() {
		Receiver r = new Receiver();
		r.receive();
	}

}
