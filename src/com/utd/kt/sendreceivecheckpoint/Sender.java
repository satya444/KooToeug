package com.utd.kt.sendreceivecheckpoint;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.utd.kt.main.AosMain;
import com.utd.kt.networkprotocol.ConnectionManager;
import com.utd.kt.utils.Message;
import com.utd.kt.utils.RandomNumberGenerator;
import com.utd.kt.vclsrfls.VectorClockLLRFLS;

public class Sender {

	public Map<Integer, Integer> seqNoMap = new HashMap<>();

	public void invokeAllSends() {
		populateSeqNoMap();
		int i = 0;
		while (i < 4) {
			synchronized (SendReceiveCheckpoint.obj) {
				int destId = RandomNumberGenerator.getRandom();
				int myclock = VectorClockLLRFLS.vc.get(AosMain.myNodeId);
				myclock = myclock + 1;
				VectorClockLLRFLS.vc.put(AosMain.myNodeId, myclock);
				int currSeqNo = seqNoMap.get(destId);
				currSeqNo++;
				seqNoMap.put(destId, currSeqNo);

				// Update FLS if this is the first message after taking
				// CheckPoint

				int flsVal = VectorClockLLRFLS.fls.get(destId);
				if (flsVal == 0) {
					flsVal = currSeqNo;
					VectorClockLLRFLS.fls.put(destId, flsVal);
				}
				Message m = new Message(currSeqNo, VectorClockLLRFLS.vc, 0,
						AosMain.myNodeId);

				ConnectionManager.sendMessage(m, destId);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
		}
	}

	public void sendllr(){
		Iterator<Integer> itr= AosMain.neighborIds.iterator();
		while(itr.hasNext()){
			Integer nextAddr= itr.next();
			int myclock = VectorClockLLRFLS.vc.get(AosMain.myNodeId);
			myclock = myclock + 1;
			VectorClockLLRFLS.vc.put(AosMain.myNodeId, myclock);
			Message m= new Message(0,VectorClockLLRFLS.vc,1,AosMain.myNodeId);
			Integer llrVal= VectorClockLLRFLS.llr.get(nextAddr);
			m.setLlrSendVal(llrVal);
			ConnectionManager.sendMessage(m,nextAddr);
		}
	}
	public void populateSeqNoMap() {
		Iterator<Integer> itr = AosMain.neighborIds.iterator();
		while (itr.hasNext()) {
			seqNoMap.put(itr.next(), 0);
		}
	}

}
