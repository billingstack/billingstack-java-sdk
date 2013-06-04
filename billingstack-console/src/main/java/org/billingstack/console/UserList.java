package org.billingstack.console;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.MerchantTarget;
import org.billingstack.User;

import com.woorea.openstack.console.utils.Column;
import com.woorea.openstack.console.utils.Table;
import com.woorea.openstack.console.utils.TableModel;

public class UserList extends MerchantCommand {
	
	public UserList() {
		super("user-list");
	}

	@Override
	public void execute(MerchantEnvironment env, CommandLine cmd) {
		final List<User> users = env.ENDPOINT.users().list();
		
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
