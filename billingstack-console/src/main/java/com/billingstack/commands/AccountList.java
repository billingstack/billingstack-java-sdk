package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.Account;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class AccountList extends Command {
	
	public AccountList() {
		super("account-list");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		final List<Account> accounts = env.getBillingStack().accounts().list();
		
		Table t = new Table(new TableModel<Account>(accounts) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 36, Column.ALIGN_LEFT),
					new Column("name", 20, Column.ALIGN_LEFT),
					new Column("title", 20, Column.ALIGN_LEFT),
				};
			}

			@Override
			public String[][] getRows() {
				String[][] rows = new String[accounts.size()][];
				for(int i = 0; i < accounts.size(); i++) {
					rows[i] = new String[]{
						accounts.get(i).getId(),
						accounts.get(i).getName(),
						accounts.get(i).getTitle(),
					};
				}
				return rows;
			}
		});
		System.out.println(t.render());
	}

}
