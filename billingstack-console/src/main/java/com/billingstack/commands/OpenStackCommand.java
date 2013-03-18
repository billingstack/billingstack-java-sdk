package com.billingstack.commands;

import java.util.logging.Logger;

import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.Authenticate;
import org.openstack.keystone.model.Access;
import org.openstack.nova.NovaClient;

import com.billingstack.Environment;

public abstract class OpenStackCommand extends Command {

	public OpenStackCommand(String name) {
		super(name);
	}
	
	public static KeystoneClient getKeystoneClient(Environment env) {
		//now we should go to openstack / keystone
		KeystoneClient keystone = new KeystoneClient((String) env.getProperty("keystone.endpoint"));
		keystone.enableLogging(Logger.getLogger("keystone"), 100000);
		
		
		Access access = keystone.execute(Authenticate.withPasswordCredentials(
				(String) env.getProperty("keystone.username"), 
				(String) env.getProperty("keystone.password")
		).withTenantName((String) env.getProperty("keystone.tenant_name")));
				
		keystone = new KeystoneClient((String) env.getProperty("keystone.endpoint"), access.getToken().getId());
		keystone.enableLogging(Logger.getLogger("keystone"), 100000);
		return keystone;
	}
	
	public static NovaClient getNovaClient(Environment env) {
		//now we should go to openstack / keystone
		KeystoneClient keystone = new KeystoneClient((String) env.getProperty("keystone.endpoint"));
		keystone.enableLogging(Logger.getLogger("keystone"), 100000);
		
		
		Access access = keystone.execute(Authenticate.withPasswordCredentials(
				(String) env.getProperty("keystone.username"), 
				(String) env.getProperty("keystone.password")
		).withTenantName((String) env.getProperty("keystone.tenant_name")));
		
		String endpoint = env.getProperty("nova.endpoint") + "/" + env.getProperty("billingstack.console.tenant");
		
		NovaClient nova = new NovaClient(endpoint, access.getToken().getId());
		nova.enableLogging(Logger.getLogger("nova"), 100000);
		return nova;
	}

}
