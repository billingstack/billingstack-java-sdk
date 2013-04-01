package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.MerchantTarget;
import org.billingstack.Subscription;
import org.openstack.console.utils.Column;
import org.openstack.console.utils.Table;
import org.openstack.console.utils.TableModel;

import com.google.common.collect.Lists;

public class SubscriptionCreate extends MerchantCommand {
	
	public SubscriptionCreate() {
		super("subscription-create");
	}

	@Override
	public void execute(MerchantEnvironment env, final CommandLine cmd) {
		
		final Subscription subscription = env.ENDPOINT.subscriptions().create(new Subscription(){{
			setCustomerId(cmd.getOptionValue("customer"));
			setPlanId(cmd.getOptionValue("plan"));
			setPaymentMethod(cmd.getOptionValue("payment-method"));
			setResource(cmd.getOptionValue("resource"));
		}});
		
		Table t = new Table(new TableModel<Subscription>(Lists.newArrayList(subscription)) {

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
						subscription.getCustomerId(),
						subscription.getPlanId(),
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
		opts.addOption(null, "plan", true, "plan id");
		opts.addOption(null, "payment-method", true, "payment method id");
		opts.addOption(null, "resource", true, "resource id");
		return opts;
	}
	
}
