package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.Role;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class RoleList extends Command {
	
	public RoleList() {
		super("role-list");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		final List<Role> roles = env.getBillingStack().roles().list();
		
		Table t = new Table(new TableModel<Role>(roles) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 36, Column.ALIGN_LEFT),
					new Column("name", 20, Column.ALIGN_LEFT),
					new Column("title", 20, Column.ALIGN_LEFT),
				};
			}

			@Override
			public String[] getRow(Role role) {
				return new String[]{
					role.getId(),
					role.getName(),
					role.getTitle()
				};
			}
		});
		System.out.println(t.render());
	}

}
