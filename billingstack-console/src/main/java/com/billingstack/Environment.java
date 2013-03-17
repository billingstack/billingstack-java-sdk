package com.billingstack;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;

import com.billingstack.utils.Console;
import com.google.common.collect.ImmutableMap;

public class Environment {
	
	private static final Logger LOGGER = Logger.getLogger("billinstack");

	private BillingStack client = new BillingStack();
	
	private Map<EnvironmentProperties, String> properties = new HashMap<EnvironmentProperties, String>();
	
	public Environment() {
		properties.put(EnvironmentProperties.PROMPT, new Console().green("billingstack> ").toString());
		properties.put(EnvironmentProperties.LOGGING, "true");
		properties.put(EnvironmentProperties.ENDPOINT, "http://localhost:8080/billingstack-api");
	}

	public BillingStackEndpoint getBillingStack() {
		BillingStackEndpoint target = client.create(properties.get(EnvironmentProperties.ENDPOINT));
		if(getProperty(EnvironmentProperties.LOGGING) != null) {
			target.logger(LOGGER);
		}
		return target;
	}
	
	public void destroy() {
		client.close();
	}
	
	public void setProperty(EnvironmentProperties property, String value) {
		properties.put(property, value);
	}
	
	public String getProperty(EnvironmentProperties property) {
		return properties.get(property);
	}
	
	public Map<EnvironmentProperties, String> getProperties() {
		return ImmutableMap.copyOf(properties);
	}
	
	public String getPrompt() {
		Console console = new Console();
		console.green("billingstack");
		if(properties.get(EnvironmentProperties.MERCHANT) != null) {
			console.yellow(":");
			console.green(properties.get(EnvironmentProperties.MERCHANT));
		}
		if(properties.get(EnvironmentProperties.CUSTOMER) != null) {
			console.yellow(":");
			console.green(properties.get(EnvironmentProperties.CUSTOMER));
		}
		console.green("> ");
		return console.toString();
	}
	
}
