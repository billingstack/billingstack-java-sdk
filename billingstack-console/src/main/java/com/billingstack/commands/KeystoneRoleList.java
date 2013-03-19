package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.ListRoles;
import org.openstack.keystone.model.Role;
import org.openstack.keystone.model.Roles;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class KeystoneRoleList extends OpenStackCommand {
	
	public KeystoneRoleList() {
		super("keystone-role-list");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		KeystoneClient keystone = getKeystoneClient(env);
		
		final Roles roles = keystone.execute(new ListRoles());
		
		Table t = new Table(new TableModel<Role>(roles.getList()) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 32, Column.ALIGN_LEFT),
					new Column("name", 10, Column.ALIGN_LEFT),
					new Column("description", 32, Column.ALIGN_LEFT),
					new Column("enabled", 7, Column.ALIGN_LEFT),
				};
			}

			@Override
			public String[] getRow(Role role) {
				return new String[]{
						role.getId(),
						role.getName(),
						role.getDescription(),
						role.getEnabled()
				};
			}
		});
		System.out.println(t.render());
	}

}
