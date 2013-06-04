package org.billingstack.console;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Currency;

import com.woorea.openstack.console.utils.Column;
import com.woorea.openstack.console.utils.Table;
import com.woorea.openstack.console.utils.TableModel;

public class CurrencyList extends BillingStackCommand {
	
	public CurrencyList() {
		super("currency-list");
	}

	@Override
	public void execute(BillingStackEndpoint bs, final CommandLine cmd) {
		
		final List<Currency> currencies = bs.currencies().list();
		
		Table t = new Table(new TableModel<Currency>(currencies) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("name", 3, Column.ALIGN_LEFT),
					new Column("title", 32, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[] getRow(Currency currency) {
				return new String[]{
					currency.getName(),
					currency.getTitle()
				};
			}
		});
		System.out.println(t.render());
	}

}
