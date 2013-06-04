package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.billingstack.MerchantTarget;
import org.billingstack.User;

import com.google.common.collect.Lists;
import com.woorea.openstack.console.utils.Column;
import com.woorea.openstack.console.utils.Table;
import com.woorea.openstack.console.utils.TableModel;

public class UserShow extends MerchantCommand {
	
	public UserShow() {
		super("user-show");
	}

	@Override
	public void execute(MerchantEnvironment env, CommandLine cmd) {
		
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			
			final User user = env.ENDPOINT.user(args[0]).show();
			
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
