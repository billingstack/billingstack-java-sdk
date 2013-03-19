package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.billingstack.User;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.collect.Lists;

public class UserShow extends MerchantCommand {
	
	public UserShow() {
		super("user-show");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			
			final User user = getMerchant(env, cmd).user(args[0]).show();
			
			Table t = new Table(new TableModel<User>(Lists.newArrayList(user)) {

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
}
