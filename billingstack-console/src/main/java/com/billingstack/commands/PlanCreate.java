package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Plan;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.collect.Lists;

public class PlanCreate extends Command {

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		
		final Plan plan = env.getBillingStack().merchant(cmd.getOptionValue('m')).plans().create(new Plan(){{
			setName(cmd.getOptionValue('m'));
			setTitle(cmd.getOptionValue('t'));
			setDescription(cmd.getOptionValue('d'));
		}});
		
		Table t = new Table(new TableModel<Plan>(Lists.newArrayList(plan)) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 32, Column.ALIGN_LEFT),
					new Column("name", 16, Column.ALIGN_LEFT),
					new Column("title", 32, Column.ALIGN_LEFT),
				};
			}

			@Override
			public String[][] getRows() {
				String[][] rows = new String[data.size()][];
				for(int i = 0; i < data.size(); i++) {
					rows[i] = new String[]{
						data.get(i).getId(),
						data.get(i).getName(),
						data.get(i).getTitle(),
					};
				}
				return rows;
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
		opts.addOption("m", "merchant", true, "merchant id");
		opts.addOption("n", "name", true, "plan name");
		opts.addOption("t", "title", true, "plan title");
		opts.addOption("d", "description", true, "plan description");
		return opts;
	}
	
}
