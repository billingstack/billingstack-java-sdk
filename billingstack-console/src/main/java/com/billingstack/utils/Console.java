package com.billingstack.utils;

public class Console {
	
	public static final String RED = "\u001B[31m";
	
	public static final String GREEN = "\u001B[32m";
	
	public static final String YELLOW = "\u001B[33m";
	
	public static final String END = "\u001B[0m";
	
	private StringBuilder sb = new StringBuilder();
	
	public Console append(String text) {
		sb.append(text);
		return this;
	}
	
	public Console red(String text) {
		sb.append(Console.RED).append(text).append(END);
		return this;
	}
	
	public Console green(String text) {
		sb.append(Console.GREEN).append(text).append(END);
		return this;
	}
	
	public Console yellow(String text) {
		sb.append(Console.YELLOW).append(text).append(END);
		return this;
	}
	
	public static void log(String text) {
		System.out.println(new Console().yellow("| ").append(text));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return sb.toString();
	}
	
}
