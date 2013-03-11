package com.billingstack.utils;

public class Console {
	
	public static final String RED = "\u001B[31m";
	
	public static final String GREEN = "\u001B[32m";
	
	public static final String YELLOW = "\u001B[33m";
	
	public static final String END = "\u001B[0m";
	
	public static StringBuilder red(String text) {
		StringBuilder sb = new StringBuilder();
		sb.append(Console.RED).append(text).append(END);
		return sb;
	}
	
	public static void log(String text) {
		System.out.println(new StringBuilder().append(Console.YELLOW).append("| ").append(END).append(text));
	}
	
}
