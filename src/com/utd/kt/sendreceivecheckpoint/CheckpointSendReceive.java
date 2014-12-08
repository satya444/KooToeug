package com.utd.kt.sendreceivecheckpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckpointSendReceive implements Runnable {

	public static Object obj = new Object();

	public static void initaiteAll() {
		Thread t = new Thread(new CheckpointSendReceive());
		t.start();
		SendReceive.initiateSendReceive();

	}

	@Override
	public void run() {

		System.out.println("Enter 1 for CheckPoint 2 for Recovery");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int inputVal = Integer.parseInt(br.readLine());
			synchronized (obj) {
				if (inputVal == 1) {
					
				} else if (inputVal == 2) {

				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
