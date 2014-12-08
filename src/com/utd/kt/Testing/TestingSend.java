package com.utd.kt.Testing;

import java.util.Iterator;

import com.utd.kt.utils.Message;

public class TestingSend {

	public static void send(){
			Iterator<Integer> itr= TestingMain.neighbors.iterator();
			while(itr.hasNext()){
				int nextAddr= itr.next();
				int sendClk=TestingMain.finalClks.get(nextAddr);
				Message m= new Message(sendClk,TestingMain.myNodeId);
				
				TestingConnectionManager.sendMessage(m, nextAddr);
		}
	}
}
