package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Subscription;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;
import com.google.common.collect.Lists;

public class SubscriptionList extends MerchantCommand {
	
	public SubscriptionList() {
		super("subscription-list");
	}

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		
		final List<Subscription> subscriptions = getMerchant(env, cmd).subscriptions().list();
		
		Table t = new Table(new TableModel<Subscription>(Lists.newArrayList(subscriptions)) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 32, Column.ALIGN_LEFT),
					new Column("customer", 32, Column.ALIGN_LEFT),
					new Column("plan", 32, Column.ALIGN_LEFT),
					new Column("resource", 32, Column.ALIGN_LEFT),
					new Column("payment method", 32, Column.ALIGN_LEFT),
					new Column("billingday", 2, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[] getRow(Subscription subscription) {
				return new String[]{
						subscription.getId(),
						subscription.getCustomer(),
						subscription.getPlan(),
						subscription.getResource(),
						subscription.getPaymentMethod(),
						subscription.getBillingDay().toString()
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
		opts.addOption(null, "customer", true, "customer id");
		return opts;
	}
	
}
