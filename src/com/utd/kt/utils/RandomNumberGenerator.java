package com.utd.kt.utils;

import java.util.Random;

import com.utd.kt.main.AosMain;

public class RandomNumberGenerator {

	private static int size= AosMain.neighborIds.size();
	public static int getRandom() {
	    int rnd = new Random().nextInt(size);
	    return AosMain.neighborIds.get(rnd);
	}
}
