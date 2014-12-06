package com.utd.kt.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sun.nio.sctp.SctpChannel;
import com.utd.kt.constants.AllConstants;
import com.utd.kt.networkprotocol.ConnectionManager;
import com.utd.kt.sendreceivecheckpoint.SendReceive;
import com.utd.kt.sendreceivecheckpoint.SendReceiveCheckpoint;
import com.utd.kt.utils.Message;
import com.utd.kt.utils.ReadConfiguration;
import com.utd.kt.vclsrfls.VectorClockLLRFLS;


public class AosMain {

	/**
	 * @author Dilip
	 * 
	 *         github Profile: https://github.com/satya444
	 * 
	 */

	public static HashMap<Integer, com.utd.kt.utils.NodeDetails> mapNodes = new HashMap<>();
	public static HashMap<Integer, SctpChannel> connectionSocket=new HashMap<>();
	public static int myRandomLabel;
	public static int myNodeId;
	public static int meAsDestination;
	public static boolean sendExitFlag=false;
	public static boolean receiveExitFlag=false;
	/**
	 * list of my neighbors
	 */
	public static List<Integer> neighborIds= new ArrayList<>();
	@SuppressWarnings("rawtypes")
	public static Queue<List> sendQueue = new LinkedList<>();
	public static BlockingQueue<Message> receiveQueue= new LinkedBlockingQueue<>();
	public static void setMyNodeId(int myNodeId) {
		AosMain.myNodeId = myNodeId;
	}

	public static void main(String args[]) {
		myNodeId= Integer.parseInt(args[0]);
		ReadConfiguration.readBasicConfiguration(AllConstants.basicConfigFile);
		ConnectionManager.createConnections();
		//ReadConfiguration.readInputConfiguration(AllConstants.configFile);
		//System.out.println("PRINTING QUEUE "+sendQueue.toString());
		//myRandomLabel=RandomNumberGenerator.generateRandomNumber();
		VectorClockLLRFLS.vc.put(1, 0);
		VectorClockLLRFLS.vc.put(2, 0);
		VectorClockLLRFLS.vc.put(3, 0);
		if(myNodeId==1){
			VectorClockLLRFLS.fls.put(2, 0);
			VectorClockLLRFLS.fls.put(3, 0);
			VectorClockLLRFLS.llr.put(2, 0);
			VectorClockLLRFLS.llr.put(3, 0);
			neighborIds.add(2);
			neighborIds.add(3);
		}
		if(myNodeId==2){
			VectorClockLLRFLS.fls.put(1, 0);
			VectorClockLLRFLS.fls.put(3, 0);
			VectorClockLLRFLS.llr.put(1, 0);
			VectorClockLLRFLS.llr.put(3, 0);
			neighborIds.add(1);
			neighborIds.add(3);
		}
		if(myNodeId==3){
			VectorClockLLRFLS.fls.put(2, 0);
			VectorClockLLRFLS.fls.put(1, 0);
			VectorClockLLRFLS.llr.put(2, 0);
			VectorClockLLRFLS.llr.put(1, 0);
			neighborIds.add(1);
			neighborIds.add(2);
		}
		
		SendReceiveCheckpoint src= new SendReceiveCheckpoint();
		src.instantiateSendReciveCheckpoint();
	}


}
