package com.utd.kt.VcLlrFlc;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import com.utd.kt.main.AosMain;
import com.utd.kt.sendreceivecheckpoint.CheckpointSendReceive;
import com.utd.kt.sendreceivecheckpoint.Sender;
import com.utd.kt.utils.FileFeatures;

public class VectorLlrFlsLls {

	public static Map<Integer,Integer> llr= new HashMap<>();
	public static Map<Integer,Integer> fls = new HashMap<>();
	public static Map<Integer,Integer> lls = new HashMap<>();
	public static Map<Integer,Integer> vc = new HashMap<>();
	public static Map<Integer,Integer> seqNo = new HashMap<>();
	public static Map<Integer,Integer> checkPointVc= new HashMap<>();
	
	public static void reset(){
		fls.clear();
		llr.clear();
		Iterator<Integer> itr= AosMain.neighbors.iterator();
		while(itr.hasNext()){
			int nextAddr= itr.next();
			fls.put(nextAddr,0);
			llr.put(nextAddr, 0);
		}
	}
	public static void recoverRollback(HashSet<Integer> ids){
		synchronized (CheckpointSendReceive.obj) {
			FileFeatures.writeAll();
			reset();
			vc.clear();
			vc.putAll(checkPointVc);
			Sender.sendLlsToRest(ids);
		}
	}
 }
