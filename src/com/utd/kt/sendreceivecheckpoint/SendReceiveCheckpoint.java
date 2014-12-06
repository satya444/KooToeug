package com.utd.kt.sendreceivecheckpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

public class SendReceiveCheckpoint implements Runnable {
	public static AtomicInteger commLock = new AtomicInteger(0);
	public static Object obj= new Object();

	public void instantiateSendReciveCheckpoint() {
		Thread t = new Thread(new SendReceiveCheckpoint());
		t.start();
		SendReceive sr = new SendReceive();
		sr.initiateSendReceive();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(10000);
			System.out.println("PRESS 1 TO TAKE SNAPSHOT ");
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			Integer i= Integer.parseInt(br.readLine());
			if(i==1){
				synchronized(obj){
					
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
