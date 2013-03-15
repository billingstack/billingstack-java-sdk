package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.Merchant;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class MerchantList extends Command {

	@Override
	public void execute(Environment env, CommandLine cmd) {
		final List<Merchant> merchants = env.getBillingStack().merchants().list();
		
		Table t = new Table(new TableModel<Merchant>(merchants) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 32, Column.ALIGN_LEFT),
					new Column("name", 16, Column.ALIGN_LEFT),
					new Column("title", 32, Column.ALIGN_LEFT),
					new Column("language", 8, Column.ALIGN_RIGHT),
					new Column("currency", 8, Column.ALIGN_RIGHT),
				};
			}

			@Override
			public String[][] getRows() {
				String[][] rows = new String[merchants.size()][];
				for(int i = 0; i < merchants.size(); i++) {
					rows[i] = new String[]{
						merchants.get(i).getId(),
						merchants.get(i).getName(),
						merchants.get(i).getTitle(),
						merchants.get(i).getLanguage(),
						merchants.get(i).getCurrency()
					};
				}
				return rows;
			}
		});
		System.out.println(t.render());
	}

	

}
