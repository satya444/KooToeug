package com.utd.kt.sendreceivecheckpoint;

import java.util.HashSet;
import java.util.Iterator;

import com.utd.kt.VcLlrFlc.VectorLlrFlsLls;
import com.utd.kt.main.AosMain;
import com.utd.kt.networkprotocol.ConnectionManager;
import com.utd.kt.utils.Message;
import com.utd.kt.utils.RandomNumberGenerator;

public class Sender {

	public static void initiateSends() {
		while (true) {
			int destId = RandomNumberGenerator.generateRandomNumber();
			
			synchronized (CheckpointSendReceive.obj) {
			int seqNo = VectorLlrFlsLls.seqNo.get(destId);
			seqNo++;
			VectorLlrFlsLls.seqNo.put(destId,seqNo);
			
				int myclock = VectorLlrFlsLls.vc.get(AosMain.myNodeId);
				myclock++;
				VectorLlrFlsLls.vc.put(AosMain.myNodeId, myclock);
				Message m = new Message(seqNo, VectorLlrFlsLls.vc, 0,
						AosMain.myNodeId);
				m.addMe(AosMain.myNodeId);
				int flsVal= VectorLlrFlsLls.fls.get(destId);
				if(flsVal==0){
					VectorLlrFlsLls.fls.put(destId, seqNo);
				}
				ConnectionManager.sendMessage(m, destId);
				CheckpointSendReceive.print();
				System.out.println("********************************************");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static void sendLls() {

		synchronized(CheckpointSendReceive.obj){
			int myClk= VectorLlrFlsLls.vc.get(AosMain.myNodeId);
			myClk++;
			VectorLlrFlsLls.vc.put(AosMain.myNodeId, myClk);
			
			Message m= new Message(0,VectorLlrFlsLls.vc,2,AosMain.myNodeId);
			Iterator<Integer> itr= AosMain.neighbors.iterator();
			while(itr.hasNext()){
				int nextAddr= itr.next();
				if(VectorLlrFlsLls.lls.get(nextAddr)!=0){
					m.setLlrVal(VectorLlrFlsLls.lls.get(nextAddr));
					m.addMe(AosMain.myNodeId);
					ConnectionManager.sendMessage(m, nextAddr);
				}
			}
		}
	}

	public static void sendLlr() {

		synchronized(CheckpointSendReceive.obj){
			int myClk= VectorLlrFlsLls.vc.get(AosMain.myNodeId);
			myClk++;
			VectorLlrFlsLls.vc.put(AosMain.myNodeId, myClk);
			Iterator<Integer> itr= AosMain.neighbors.iterator();
			while(itr.hasNext()){
				int nextAddr= itr.next();
				if(VectorLlrFlsLls.llr.get(nextAddr)!=0){
					Message m= new Message(0,VectorLlrFlsLls.vc,1,AosMain.myNodeId);
					m.setLlrVal(VectorLlrFlsLls.llr.get(nextAddr));
					m.addMe(AosMain.myNodeId);
					ConnectionManager.sendMessage(m, nextAddr);
				}
			}
		}
	}

	public static void sendLlsToRest(HashSet<Integer> destIds) {

		synchronized(CheckpointSendReceive.obj){
			int myClk= VectorLlrFlsLls.vc.get(AosMain.myNodeId);
			myClk++;
			VectorLlrFlsLls.vc.put(AosMain.myNodeId, myClk);
			Iterator<Integer> itr= AosMain.neighbors.iterator();
			while(itr.hasNext()){
				int nextAddr= itr.next();
				if(!destIds.contains(nextAddr)){
					Message m= new Message(0,VectorLlrFlsLls.vc,2,AosMain.myNodeId);
					m.addAll(destIds);
					m.addMe(AosMain.myNodeId);
					m.setLlsVal(VectorLlrFlsLls.lls.get(nextAddr));
					ConnectionManager.sendMessage(m, nextAddr);
				}
			}
		}
	}

	public static void sendLlrToRest(HashSet<Integer> destIds) {
		synchronized(CheckpointSendReceive.obj){
			int myClk= VectorLlrFlsLls.vc.get(AosMain.myNodeId);
			myClk++;
			VectorLlrFlsLls.vc.put(AosMain.myNodeId, myClk);
			Iterator<Integer> itr= AosMain.neighbors.iterator();
			while(itr.hasNext()){
				int nextAddr= itr.next();
				if(!destIds.contains(nextAddr)){
					Message m= new Message(0,VectorLlrFlsLls.vc,1,AosMain.myNodeId);
					m.addAll(destIds);
					m.addMe(AosMain.myNodeId);
					m.setLlrVal(VectorLlrFlsLls.llr.get(nextAddr));
					ConnectionManager.sendMessage(m, nextAddr);
				}
			}
		}
	}

}
