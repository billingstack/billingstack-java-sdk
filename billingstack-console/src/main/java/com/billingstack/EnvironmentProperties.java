package com.billingstack;


public enum EnvironmentProperties {

	PROMPT("prompt"),
	ENDPOINT("endpoint"),
	LOGGING("logging"),
	MERCHANT("merchant"),
	CUSTOMER("customer");
	
	private String name;
	
	private EnvironmentProperties(String name) {
		this.name = name;
	}
	
	public static EnvironmentProperties from(String name) {
	    if (name != null) {
	      for (EnvironmentProperties p : EnvironmentProperties.values()) {
	        if (p.name.equalsIgnoreCase(name)) {
	          return p;
	        }
	      }
	    }
	    return null;
	  }
	
}
