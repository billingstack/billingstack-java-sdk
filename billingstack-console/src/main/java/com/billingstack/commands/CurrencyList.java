package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.Currency;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class CurrencyList extends Command {
	
	public CurrencyList() {
		super("currency-list");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		final List<Currency> currencies = env.getBillingStack().currencies().list();
		
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
