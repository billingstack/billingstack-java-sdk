package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.DeleteUser;
import org.openstack.keystone.api.ListUsers;
import org.openstack.keystone.api.ShowUser;
import org.openstack.keystone.model.User;
import org.openstack.keystone.model.Users;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Console;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.collect.Lists;

public class KeystoneUserShow extends OpenStackCommand {
	
	public KeystoneUserShow() {
		super("keystone-user-show");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			KeystoneClient keystone = getKeystoneClient(env);
			User user = keystone.execute(new ShowUser(args[0]));
			Table t = new Table(new TableModel<User>(Lists.newArrayList(user)) {

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
				public String[][] getRows() {
					String[][] rows = new String[data.size()][];
					for(int i = 0; i < data.size(); i++) {
						rows[i] = new String[]{
							data.get(i).getId(),
							data.get(i).getName(),
							data.get(i).getEmail(),
							data.get(i).getTenantId(),
							data.get(i).getEnabled().toString()
						};
					}
					return rows;
				}
			});
			System.out.println(t.render());
		}
	}

}
