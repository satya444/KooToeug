package com.utd.kt.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.nio.sctp.SctpChannel;
import com.utd.kt.VcLlrFlc.VectorLlrFlsLls;
import com.utd.kt.constants.AllConstants;
import com.utd.kt.networkprotocol.ConnectionManager;
import com.utd.kt.sendreceivecheckpoint.CheckpointSendReceive;
import com.utd.kt.utils.NodeDetails;
import com.utd.kt.utils.ReadConfiguration;

public class AosMain {

	public static HashMap<Integer, NodeDetails> mapNodes = new HashMap<>();
	public static HashMap<Integer, SctpChannel> connectionSocket=new HashMap<>();
	public static int myRandomLabel;
	public static int myNodeId;
	public static int meAsDestination;
	public static List<Integer> neighbors= new ArrayList<>();
	public static String outLlrfile;
	public static String outLlsfile;
	public static String outFlsfile;
	public static String outVcFile;
	public static String outCheckPointFile;
	public static void main(String args[]){
		myNodeId= Integer.parseInt(args[0]);
		outFlsfile= "/home/kooToeug/"+myNodeId+"/outFls.txt";
		outLlrfile= "/home/kooToeug/"+myNodeId+"/outLlr.txt";
		outLlsfile= "/home/kooToeug/"+myNodeId+"/outLls.txt";
		outVcFile= "/home/kooToeug/"+myNodeId+"/outVc.txt";
		outCheckPointFile= "/home/kooToeug/"+myNodeId+"/outCheckPoint.txt";
		ReadConfiguration.readBasicConfiguration(AllConstants.basicConfigFile);
		VectorLlrFlsLls.vc.put(1, 0);
		VectorLlrFlsLls.vc.put(2, 0);
		VectorLlrFlsLls.vc.put(3, 0);
		VectorLlrFlsLls.checkPointVc.put(1, 0);
		VectorLlrFlsLls.checkPointVc.put(2, 0);
		VectorLlrFlsLls.checkPointVc.put(3, 0);
		if(myNodeId==1){
			neighbors.add(2);
			neighbors.add(3);
			VectorLlrFlsLls.fls.put(2, 0);
			VectorLlrFlsLls.fls.put(3, 0);
			VectorLlrFlsLls.llr.put(2, 0);
			VectorLlrFlsLls.llr.put(3, 0);
			VectorLlrFlsLls.lls.put(2, 0);
			VectorLlrFlsLls.lls.put(3, 0);
			VectorLlrFlsLls.seqNo.put(2, 0);
			VectorLlrFlsLls.seqNo.put(3, 0);
		}
		if(myNodeId==2){
			neighbors.add(1);
			neighbors.add(3);
			VectorLlrFlsLls.fls.put(1, 0);
			VectorLlrFlsLls.fls.put(3, 0);
			VectorLlrFlsLls.llr.put(1, 0);
			VectorLlrFlsLls.llr.put(3, 0);
			VectorLlrFlsLls.lls.put(1, 0);
			VectorLlrFlsLls.lls.put(3, 0);
			VectorLlrFlsLls.seqNo.put(1, 0);
			VectorLlrFlsLls.seqNo.put(3, 0);
		}
		if(myNodeId==3){
			neighbors.add(1);
			neighbors.add(2);
			VectorLlrFlsLls.fls.put(2, 0);
			VectorLlrFlsLls.fls.put(1, 0);
			VectorLlrFlsLls.llr.put(2, 0);
			VectorLlrFlsLls.llr.put(1, 0);
			VectorLlrFlsLls.lls.put(2, 0);
			VectorLlrFlsLls.lls.put(1, 0);
			VectorLlrFlsLls.seqNo.put(1, 0);
			VectorLlrFlsLls.seqNo.put(2, 0);
		}
		ConnectionManager.createConnections();
		CheckpointSendReceive.initaiteAll();
	}
}
