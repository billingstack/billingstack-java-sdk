package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.ListUsers;
import org.openstack.keystone.model.User;
import org.openstack.keystone.model.Users;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class KeystoneUserList extends OpenStackCommand {
	
	public KeystoneUserList() {
		super("keystone-user-list");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		KeystoneClient keystone = getKeystoneClient(env);
		
		final Users users = keystone.execute(new ListUsers());
		
		Table t = new Table(new TableModel<User>(users.getList()) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 32, Column.ALIGN_LEFT),
					new Column("name", 10, Column.ALIGN_LEFT),
					new Column("email", 22, Column.ALIGN_LEFT),
					new Column("tenant", 32, Column.ALIGN_LEFT),
					new Column("enabled", 7, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[] getRow(User user) {
				return new String[]{
					user.getId(),
					user.getName(),
					user.getEmail(),
					user.getTenantId(),
					user.getEnabled().toString()
				};
			}
		});
		System.out.println(t.render());
	}

}