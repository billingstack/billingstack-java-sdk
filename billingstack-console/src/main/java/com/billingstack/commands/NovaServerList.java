package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.openstack.nova.NovaClient;
import org.openstack.nova.api.ServersCore.ListServers;
import org.openstack.nova.model.Server;
import org.openstack.nova.model.Servers;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class NovaServerList extends OpenStackCommand {
	
	public NovaServerList() {
		super("nova-server-list");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		NovaClient nova = getNovaClient(env);
		
		final Servers servers = nova.execute(new ListServers());
		
		Table t = new Table(new TableModel<Server>(servers.getList()) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 32, Column.ALIGN_LEFT),
					new Column("name", 10, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[][] getRows() {
				String[][] rows = new String[data.size()][];
				for(int i = 0; i < data.size(); i++) {
					rows[i] = new String[]{
						data.get(i).getId(),
						data.get(i).getName()
					};
				}
				return rows;
			}
		});
		System.out.println(t.render());
	}

}
