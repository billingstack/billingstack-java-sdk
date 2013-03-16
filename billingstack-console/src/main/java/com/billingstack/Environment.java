package com.billingstack;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;

import com.billingstack.utils.Console;
import com.google.common.collect.ImmutableMap;

public class Environment {
	
	public static final String PROMPT = "environment.prompt";

	public static final String MERCHANT = "environment.merchant";
	
	public static final String CUSTOMER = "environment.customer";

	private BillingStack client = new BillingStack();
	
	private Map<String, String> properties = new HashMap<String, String>();
	
	public Environment() {
		properties.put(Environment.PROMPT, new Console().green("billingstack> ").toString());
	}

	public BillingStackEndpoint getBillingStack() {
		BillingStackEndpoint target = client.create(properties.get("endpoint"));
		return target;
	}
	
	public void destroy() {
		client.close();
	}
	
	public void setProperty(String name, String value) {
		properties.put(name, value);
	}
	
	public String getProperty(String name) {
		return properties.get(name);
	}
	
	public Map<String, String> getProperties() {
		return ImmutableMap.copyOf(properties);
	}

	public void setLoggingEnabled(boolean enabled) {
		if (enabled) {
			client.enableLogging(Logger.getLogger("billinstack"), 1000000);
		} else {
			client.disableLogging();
		}
	}
	
	public String getPrompt() {
		Console console = new Console();
		console.green("billingstack");
		if(properties.get(Environment.MERCHANT) != null) {
			console.yellow(":");
			console.green(properties.get(Environment.MERCHANT));
		}
		if(properties.get(Environment.CUSTOMER) != null) {
			console.yellow(":");
			console.green(properties.get(Environment.CUSTOMER));
		}
		console.green("> ");
		return console.toString();
	}
	
}
