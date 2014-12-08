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

		while (true) {
			System.out.println("Enter 1 for CheckPoint 2 for Recovery");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				int inputVal = Integer.parseInt(br.readLine());
				synchronized (obj) {
					if (inputVal == 1) {
						System.out.println("CHECKPOINT INITIATED");
						VectorLlrFlsLls.checkPointVc.clear();
						VectorLlrFlsLls.checkPointVc.putAll(VectorLlrFlsLls.vc);
						VectorLlrFlsLls.lls.clear();
						VectorLlrFlsLls.lls.putAll(VectorLlrFlsLls.seqNo);
						FileFeatures.writeAll();
						print();
						Sender.sendLlr();
						VectorLlrFlsLls.reset();
					} else if (inputVal == 2) {
						System.out.println("RECOVERY INITIATED");
						VectorLlrFlsLls.vc.clear();
						VectorLlrFlsLls.vc.putAll(VectorLlrFlsLls.checkPointVc);
						VectorLlrFlsLls.reset();
						FileFeatures.writeAll();
						print();
						Sender.sendLls();

					}
					Thread.sleep(8000);
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

	}

	public static void print() {
		synchronized (obj) {
			System.out.println("VECTOR CLOCK");
			for (Integer itr : VectorLlrFlsLls.vc.keySet()) {
				System.out.println(itr + " " + VectorLlrFlsLls.vc.get(itr));

			}
			System.out.println("************************************");
			System.out.println("FLS ");
			for (Integer itr : VectorLlrFlsLls.fls.keySet()) {
				System.out.println(itr + " " + VectorLlrFlsLls.fls.get(itr));
			}
			System.out.println("************************************");
			System.out.println("LLR");
			for (Integer itr : VectorLlrFlsLls.llr.keySet()) {
				System.out.println(itr + " " + VectorLlrFlsLls.llr.get(itr));
			}
			System.out.println("************************************");
			System.out.println("LLS");
			for (Integer itr : VectorLlrFlsLls.lls.keySet()) {
				System.out.println(itr + " " + VectorLlrFlsLls.lls.get(itr));
			}
			System.out.println("************************************");

		}
	}
}
