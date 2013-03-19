package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.ListTenants;
import org.openstack.keystone.model.Tenant;
import org.openstack.keystone.model.Tenants;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class KeystoneTenantList extends OpenStackCommand {
	
	public KeystoneTenantList() {
		super("keystone-tenant-list");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		KeystoneClient keystone = getKeystoneClient(env);
		
		final Tenants tenants = keystone.execute(new ListTenants());
		
		Table t = new Table(new TableModel<Tenant>(tenants.getList()) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 32, Column.ALIGN_LEFT),
					new Column("name", 32, Column.ALIGN_LEFT),
					new Column("description", 32, Column.ALIGN_LEFT),
					new Column("enabled", 7, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[] getRow(Tenant tenant) {
				return new String[]{
					tenant.getId(),
					tenant.getName(),
					tenant.getDescription(),
					tenant.getEnabled().toString()
				};
			}
		});
		System.out.println(t.render());
	}

}
