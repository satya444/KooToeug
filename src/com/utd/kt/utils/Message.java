package com.utd.kt.utils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;

	private int seqNo;
	public int getSeqNo() {
		return seqNo;
	}
	public Map<Integer, Integer> getVc() {
		return vc;
	}
	public int getMsgType() {
		return msgType;
	}
	public int getSrcId() {
		return srcId;
	}
	public HashSet<Integer> getAllSources() {
		return allSources;
	}
	private Map<Integer, Integer> vc;
	private int msgType;
	private int srcId;
	private HashSet<Integer> allSources= new HashSet<>();
	private int llsVal;
	public int getLlsVal() {
		return llsVal;
	}
	public void setLlsVal(int llsVal) {
		this.llsVal = llsVal;
	}
	public int getLlrVal() {
		return llrVal;
	}
	public void setLlrVal(int llrVal) {
		this.llrVal = llrVal;
	}
	private int llrVal;
	public void addMe(int id){
		allSources.add(id);
	}
	public void addAll(HashSet<Integer> ids){
		allSources.addAll(ids);
	}
	public Message(int seqNo, Map<Integer,Integer> vc, int msgType, int srcId){
		this.seqNo=seqNo;
		this.vc=vc;
		this.msgType=msgType;
		this.srcId=srcId;
	}
	private int sendClk;
	public int getSendClk() {
		return sendClk;
	}
	public Message(int sendClk,int srcId){
		this.srcId=srcId;
		this.sendClk=sendClk;
	}
}
