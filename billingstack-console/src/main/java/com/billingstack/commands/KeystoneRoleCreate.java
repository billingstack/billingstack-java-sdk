package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.CreateRole;
import org.openstack.keystone.model.Role;
import org.openstack.keystone.model.Tenant;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.collect.Lists;

public class KeystoneRoleCreate extends OpenStackCommand {
	
	public KeystoneRoleCreate() {
		super("keystone-role-create");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		
		KeystoneClient keystone = getKeystoneClient(env);
		
		Role role = new Role();
		role.setName(cmd.getOptionValue("name"));
		role.setDescription(cmd.getOptionValue("description"));
		if(cmd.getOptionValue("enabled") != null) {
			role.setEnabled("True");
		}
		
		role = keystone.execute(new CreateRole(role));
		
		Table t = new Table(new TableModel<Role>(Lists.newArrayList(role)) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 32, Column.ALIGN_LEFT),
					new Column("name", 10, Column.ALIGN_LEFT),
					new Column("description", 32, Column.ALIGN_LEFT),
					new Column("enabled", 7, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[] getRow(Role tenant) {
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

	/* (non-Javadoc)
	 * @see com.billingstack.commands.Command#getOptions()
	 */
	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption(null, "name", true, "tenant name");
		opts.addOption(null, "description", true, "tenant description");
		opts.addOption(null, "enabled", false, "enabled");
		return opts;
	}
	
}
