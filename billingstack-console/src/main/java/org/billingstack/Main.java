package org.billingstack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.billingstack.console.BillingStackEnvironment;
import org.openstack.console.Console;
import org.openstack.console.Environment;
import org.openstack.console.keystone.KeystoneEnvironment;
import org.openstack.console.nova.NovaEnvironment;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		Environment environment = new Environment();
		environment.register(KeystoneEnvironment.KEYSTONE);
		environment.register(NovaEnvironment.NOVA);
		environment.register(BillingStackEnvironment.BILLINGSTACK);
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/console.properties"));
		
		Console console = new Console(environment, properties);
		console.start();
	}

}
