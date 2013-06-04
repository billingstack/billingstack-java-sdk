package org.billingstack.console;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Role;

import com.woorea.openstack.console.utils.Column;
import com.woorea.openstack.console.utils.Table;
import com.woorea.openstack.console.utils.TableModel;

public class RoleList extends BillingStackCommand {
	
	public RoleList() {
		super("role-list");
	}

	@Override
	public void execute(BillingStackEndpoint bs, CommandLine cmd) {
		final List<Role> roles = bs.roles().list();
		
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
