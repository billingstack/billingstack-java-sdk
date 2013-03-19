package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.Subscription;
import org.billingstack.User;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class UserList extends Command {
	
	public UserList() {
		super("user-list");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		final List<User> users = env.getBillingStack().users().list();
		
		Table t = new Table(new TableModel<User>(users) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 36, Column.ALIGN_LEFT),
					new Column("username", 20, Column.ALIGN_LEFT)
				};
			}
			
			@Override
			public String[] getRow(User user) {
				return new String[]{
					user.getId(),
					user.getUsername()
				};
			}

		});
		System.out.println(t.render());
	}
}
