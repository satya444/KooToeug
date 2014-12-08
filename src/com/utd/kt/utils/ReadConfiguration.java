package com.utd.kt.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.utd.kt.main.AosMain;


/**
 * @author Dilip
 * 
 *         github Profile: https://github.com/satya444
 * 
 */
public class ReadConfiguration {
	/**
	 * Parse config.txt file- This file contains NodeIds, their Ip addresses and
	 * portNumbers To populate mapNodes HashMap
	 * 
	 * @param inputFile
	 *            : path to input configuration file
	 */
	public static void readBasicConfiguration(String inputFile) {
		File f = new File(inputFile);
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.length() == 0) {
					continue;
				}
				String[] lineSplit = line.split(" ");
				Integer nodeId = Integer.parseInt(lineSplit[0]);
				String address = lineSplit[1];
				Integer portNumber = Integer.parseInt(lineSplit[2]);
				NodeDetails nd = new NodeDetails();
				nd.setAddress(address);
				nd.setNodeID(nodeId);
				nd.setPortNumber(portNumber);
				AosMain.mapNodes.put(nodeId, nd);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
