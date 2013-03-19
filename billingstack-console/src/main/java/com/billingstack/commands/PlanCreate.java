package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Plan;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.collect.Lists;

public class PlanCreate extends MerchantCommand {
	
	public PlanCreate() {
		super("plan-create");
	}

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		
		final Plan plan = getMerchant(env, cmd).plans().create(new Plan(){{
			setName(cmd.getOptionValue("name"));
			setTitle(cmd.getOptionValue("title"));
			setDescription(cmd.getOptionValue("description"));
		}});
		
		Table t = new Table(new TableModel<Plan>(Lists.newArrayList(plan)) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 36, Column.ALIGN_LEFT),
					new Column("name", 16, Column.ALIGN_LEFT),
					new Column("title", 32, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[] getRow(Plan plan) {
					return new String[]{
						plan.getId(),
						plan.getName(),
						plan.getTitle()
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
		opts.addOption(null, "name", true, "plan name");
		opts.addOption(null, "title", true, "plan title");
		opts.addOption(null, "description", true, "plan description");
		return opts;
	}
	
}
