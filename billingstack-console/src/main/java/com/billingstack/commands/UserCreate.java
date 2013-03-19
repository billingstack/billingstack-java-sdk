package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.User;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.collect.Lists;

public class UserCreate extends MerchantCommand {
	
	public UserCreate() {
		super("user-create");
	}

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		
		final User user = getMerchant(env, cmd).users().create(new User(){{
			setUsername(cmd.getOptionValue("username"));
			setPassword(cmd.getOptionValue("password"));
		}});
		
		Table t = new Table(new TableModel<User>(Lists.newArrayList(user)) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 36, Column.ALIGN_LEFT),
					new Column("username", 16, Column.ALIGN_LEFT),
					new Column("password", 32, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[] getRow(User user) {
					return new String[]{
						user.getId(),
						user.getUsername(),
						user.getPassword()
					};
			}
		});
		System.out.println(t.render());
	}

	
	/* (non-Javadoc)
	 * @see com.billingstack.commands.Command#getOptions()
	 */
	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption(null, "username", true, "user name");
		opts.addOption(null, "password", true, "user title");
		return opts;
	}
	
}
