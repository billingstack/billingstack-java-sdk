package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.DeleteTenant;

import com.billingstack.Environment;
import com.billingstack.utils.Console;

public class KeystoneTenantDelete extends OpenStackCommand {
	
	public KeystoneTenantDelete() {
		super("keystone-tenant-delete");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		KeystoneClient keystone = getKeystoneClient(env);
		
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			keystone.execute(new DeleteTenant(args[0]));
			System.out.println(new Console().green("OK"));
		}
		
	}
	
}
