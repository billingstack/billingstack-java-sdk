package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.DeleteUser;

import com.billingstack.Environment;
import com.billingstack.utils.Console;

public class KeystoneUserDelete extends OpenStackCommand {
	
	public KeystoneUserDelete() {
		super("keystone-user-delete");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		KeystoneClient keystone = getKeystoneClient(env);
		
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			keystone.execute(new DeleteUser(args[0]));
			System.out.println(new Console().green("OK"));
		}
		
	}
	
}
