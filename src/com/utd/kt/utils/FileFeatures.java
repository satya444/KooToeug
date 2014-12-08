package com.utd.kt.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import com.utd.kt.VcLlrFlc.VectorLlrFlsLls;
import com.utd.kt.main.AosMain;
import com.utd.kt.sendreceivecheckpoint.CheckpointSendReceive;


public class FileFeatures {
	public static synchronized void appendText(String fileName, String message) {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(fileName, true)))) {
			out.println(message);
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
			System.out.println("Error in writing to file" + fileName);
		}
	}

	public static synchronized void writeText(String fileName, String message) {
		// System.out.println("====HULLAAA :: "+message);
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(fileName, false)))) {
			out.println(message);
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
			System.out.println("Error in writing to file" + fileName);
		}
	}

	public static void writeAll(){
		synchronized(CheckpointSendReceive.obj){
			for(Integer itr : VectorLlrFlsLls.checkPointVc.keySet()){
				String out= itr+" "+VectorLlrFlsLls.checkPointVc.get(itr);
				appendText(AosMain.outCheckPointFile,out );
			}
			appendText(AosMain.outCheckPointFile, "************************");
			for(Integer itr : VectorLlrFlsLls.vc.keySet()){
				String out= itr+" "+VectorLlrFlsLls.vc.get(itr);
				appendText(AosMain.outVcFile,out );
			}
			appendText(AosMain.outVcFile, "************************");
			for(Integer itr : VectorLlrFlsLls.fls.keySet()){
				String out= itr+" "+VectorLlrFlsLls.fls.get(itr);
				appendText(AosMain.outFlsfile,out );
			}
			appendText(AosMain.outFlsfile, "************************");
			for(Integer itr : VectorLlrFlsLls.lls.keySet()){
				String out= itr+" "+VectorLlrFlsLls.lls.get(itr);
				appendText(AosMain.outLlsfile,out );
			}
			appendText(AosMain.outLlsfile, "************************");
			for(Integer itr : VectorLlrFlsLls.llr.keySet()){
				String out= itr+" "+VectorLlrFlsLls.llr.get(itr);
				appendText(AosMain.outLlrfile,out );
			}
			appendText(AosMain.outLlrfile, "************************");
		}
	}

}
