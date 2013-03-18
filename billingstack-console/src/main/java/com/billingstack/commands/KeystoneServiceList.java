package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.ListServices;
import org.openstack.keystone.model.Service;
import org.openstack.keystone.model.Services;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class KeystoneServiceList extends OpenStackCommand {
	
	public KeystoneServiceList() {
		super("keystone-service-list");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		KeystoneClient keystone = getKeystoneClient(env);
		
		final Services services = keystone.execute(new ListServices());
		
		Table t = new Table(new TableModel<Service>(services.getList()) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 32, Column.ALIGN_LEFT),
					new Column("type", 10, Column.ALIGN_LEFT),
					new Column("name", 10, Column.ALIGN_LEFT),
					new Column("description", 32, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[][] getRows() {
				String[][] rows = new String[data.size()][];
				for(int i = 0; i < data.size(); i++) {
					rows[i] = new String[]{
						data.get(i).getId(),
						data.get(i).getType(),
						data.get(i).getName(),
						data.get(i).getDescription()
					};
				}
				return rows;
			}
		});
		System.out.println(t.render());
	}

}
