package org.billingstack.console;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Merchant;
import org.openstack.console.utils.Column;
import org.openstack.console.utils.Table;
import org.openstack.console.utils.TableModel;

public class MerchantList extends BillingStackCommand {
	
	public MerchantList() {
		super("merchant-list");
	}

	@Override
	public void execute(BillingStackEndpoint bs, CommandLine cmd) {
		final List<Merchant> merchants = bs.merchants().list();
		
		Table t = new Table(new TableModel<Merchant>(merchants) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 36, Column.ALIGN_LEFT),
					new Column("name", 16, Column.ALIGN_LEFT),
					new Column("title", 32, Column.ALIGN_LEFT),
					new Column("language", 8, Column.ALIGN_RIGHT),
					new Column("currency", 8, Column.ALIGN_RIGHT)
				};
			}

			@Override
			public String[] getRow(Merchant merchant) {
				return new String[]{
					merchant.getId(),
					merchant.getName(),
					merchant.getTitle(),
					merchant.getLanguage(),
					merchant.getCurrency()
				};
			}
			
		});
		System.out.println(t.render());
	}

	

}
