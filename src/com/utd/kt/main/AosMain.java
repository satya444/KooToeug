package com.utd.kt.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.nio.sctp.SctpChannel;
import com.utd.kt.constants.AllConstants;
import com.utd.kt.networkprotocol.ConnectionManager;
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
		ConnectionManager.createConnections();

	}
}
