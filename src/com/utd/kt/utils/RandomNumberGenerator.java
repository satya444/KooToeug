package com.utd.kt.utils;

import java.util.Random;

import com.utd.kt.main.AosMain;


public class RandomNumberGenerator {

	private static int size= AosMain.neighbors.size();
	public static int generateRandomNumber(){
		int rnd = new Random().nextInt(size);;
		return AosMain.neighbors.get(rnd);
	}
}
