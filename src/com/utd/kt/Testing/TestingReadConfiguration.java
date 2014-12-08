package com.utd.kt.Testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.utd.kt.Testing.TestingMain;
import com.utd.kt.utils.NodeDetails;

public class TestingReadConfiguration {
	
	 
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
					TestingMain.mapNodes.put(nodeId, nd);
					
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void readOutputVcFile(String outVcFile) {
			File f = new File(outVcFile);
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line = null;
				while ((line = br.readLine()) != null) {
					if (line.length() == 0 || line.contains("**")) {
						continue;
					}
					String[] lineSplit = line.split(" ");
					Integer nodeId = Integer.parseInt(lineSplit[0]);
					Integer clockVal= Integer.parseInt(lineSplit[1]);
					TestingMain.finalClks.put(nodeId, clockVal);
				}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		}

}
