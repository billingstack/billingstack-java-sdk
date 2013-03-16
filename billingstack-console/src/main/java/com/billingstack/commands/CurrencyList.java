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
					new Column("title", 32, Column.ALIGN_LEFT),
				};
			}

			@Override
			public String[][] getRows() {
				String[][] rows = new String[currencies.size()][];
				for(int i = 0; i < currencies.size(); i++) {
					rows[i] = new String[]{
							currencies.get(i).getName(),
							currencies.get(i).getTitle(),
					};
				}
				return rows;
			}
		});
		System.out.println(t.render());
	}

}
