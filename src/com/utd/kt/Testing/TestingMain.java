package com.utd.kt.Testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.nio.sctp.SctpChannel;
import com.utd.kt.constants.AllConstants;
import com.utd.kt.utils.NodeDetails;

public class TestingMain {


	public static HashMap<Integer, NodeDetails> mapNodes = new HashMap<>();
	public static HashMap<Integer, SctpChannel> connectionSocket=new HashMap<>();
	public static HashMap<Integer,Integer> finalClks= new HashMap<>();
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
		outFlsfile= "/home/dilip/kooToeug/"+myNodeId+"/outFls.txt";
		outLlrfile= "/home/dilip/kooToeug/"+myNodeId+"/outLlr.txt";
		outLlsfile= "/home/dilip/kooToeug/"+myNodeId+"/outLls.txt";
		outVcFile= "/home/dilip/kooToeug/"+myNodeId+"/outVc.txt";
		outCheckPointFile="/home/dilip/kooToeug/"+myNodeId+"/outCheckPoint.txt";
		TestingReadConfiguration.readBasicConfiguration(AllConstants.basicConfigFile);
		TestingReadConfiguration.readOutputVcFile(outVcFile);
		if(myNodeId==1){
			neighbors.add(2);
			neighbors.add(3);
		}
		if(myNodeId==2){
			neighbors.add(1);
			neighbors.add(3);
		}
		if(myNodeId==3){
			neighbors.add(1);
			neighbors.add(2);
		
		}
		TestingConnectionManager.createConnections();
		TestingSendReceive.initiateSendReceive();
		
		
		
	}
}
