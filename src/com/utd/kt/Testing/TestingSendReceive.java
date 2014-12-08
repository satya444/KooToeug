package com.utd.kt.Testing;


public class TestingSendReceive implements Runnable{

	public static void initiateSendReceive(){
		Thread t= new Thread(new TestingSendReceive());
		t.start();
		TestingSend.send();
	}
	@Override
	public void run() {
		TestingReceive.receive();
		
	}

}
