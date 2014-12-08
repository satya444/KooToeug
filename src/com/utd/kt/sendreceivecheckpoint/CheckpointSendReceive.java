package com.utd.kt.sendreceivecheckpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.utd.kt.VcLlrFlc.VectorLlrFlsLls;
import com.utd.kt.utils.FileFeatures;

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
					System.out.println("CHECKPOINT INITIATED");
					FileFeatures.writeAll();
					print();
					Thread.sleep(10000);
					Sender.sendLlr();
				} else if (inputVal == 2) {

				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void print(){
		for(Integer itr :VectorLlrFlsLls.vc.keySet()){
			System.out.println(itr +" "+VectorLlrFlsLls.vc.get(itr));
			
		}
		System.out.println("************************************");
		for(Integer itr : VectorLlrFlsLls.fls.keySet()){
			System.out.println(itr +" "+VectorLlrFlsLls.fls.get(itr));
		}
		for(Integer itr : VectorLlrFlsLls.llr.keySet()){
			System.out.println(itr+ " "+VectorLlrFlsLls.llr.get(itr));
		}
		for(Integer itr : VectorLlrFlsLls.lls.keySet()){
			System.out.println(itr+" "+VectorLlrFlsLls.lls.get(itr));
		}
	}
}
