package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.User;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class UserList extends Command {
	
	public UserList() {
		super("role-list");
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
			public String[][] getRows() {
				String[][] rows = new String[data.size()][];
				for(int i = 0; i < data.size(); i++) {
					rows[i] = new String[]{
						users.get(i).getId(),
						users.get(i).getUsername()
					};
				}
				return rows;
			}
		});
		System.out.println(t.render());
	}
}
