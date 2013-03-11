package com.billingstack.utils;

public class ColorPrinter {
	
	public static final String RED = "\u001B[31m";
	
	public static final String END = "\u001B[0m";
	
	public static StringBuilder red(String text) {
		StringBuilder sb = new StringBuilder();
		sb.append(RED).append(text).append(END);
		return sb;
	}
	
}
