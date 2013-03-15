package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.Role;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class RoleList extends Command {

	@Override
	public void execute(Environment env, CommandLine cmd) {
		final List<Role> roles = env.getBillingStack().roles().list();
		
		Table t = new Table(new TableModel<Role>(roles) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("name", 32, Column.ALIGN_LEFT),
					new Column("title", 32, Column.ALIGN_LEFT),
				};
			}

			@Override
			public String[][] getRows() {
				String[][] rows = new String[roles.size()][];
				for(int i = 0; i < roles.size(); i++) {
					rows[i] = new String[]{
						roles.get(i).getName(),
						roles.get(i).getTitle(),
					};
				}
				return rows;
			}
		});
		System.out.println(t.render());
	}

}
