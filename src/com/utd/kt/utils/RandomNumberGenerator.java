package com.utd.kt.utils;

import java.util.Random;

import com.utd.kt.main.AosMain;


public class RandomNumberGenerator {

	public static int generateRandomNumber(){
		Random r= new Random();
		int randomNum= r.nextInt(AosMain.neighbors.size());
		return AosMain.neighbors.get(randomNum);
	}
}
