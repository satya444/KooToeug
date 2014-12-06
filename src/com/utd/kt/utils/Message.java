package com.utd.kt.utils;

import java.io.Serializable;
import java.util.Map;

public class Message implements Serializable{

	
	/**
	 * 0 for Application Message
	 * 1 for CheckPoint Message
	 */
	private int msgType;
	private int srcId;
	public int getSrcId() {
		return srcId;
	}
	public int getMsgType() {
		return msgType;
	}
	private int llrSendVal;
	public int getLlrSendVal() {
		return llrSendVal;
	}
	public void setLlrSendVal(int llrSendVal) {
		this.llrSendVal = llrSendVal;
	}
	private static final long serialVersionUID = 1L;
	public Message(int seqNo, Map<Integer,Integer> vc,int msgType,int srcId) {
		this.vc=vc;
		this.seqNo=seqNo;
		this.msgType=msgType;
		this.srcId=srcId;
	}
	
	private Map<Integer, Integer> vc;
	public Map<Integer, Integer> getVc() {
		return vc;
	}
	public void setVc(Map<Integer, Integer> vc) {
		this.vc = vc;
	}
	private int seqNo;
	public int getSeqNo() {
		return seqNo;
	}
	
}
