package com.billingstack.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.Language;

import com.billingstack.Environment;
import com.billingstack.utils.Column;
import com.billingstack.utils.Table;
import com.billingstack.utils.TableModel;

public class LanguageList extends Command {

	@Override
	public void execute(Environment env, CommandLine cmd) {
		final List<Language> languages = env.getBillingStack().languages().list();
		
		Table t = new Table(new TableModel<Language>(languages) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("name", 16, Column.ALIGN_LEFT),
					new Column("title", 32, Column.ALIGN_LEFT),
				};
			}

			@Override
			public String[][] getRows() {
				String[][] rows = new String[languages.size()][];
				for(int i = 0; i < languages.size(); i++) {
					rows[i] = new String[]{
						languages.get(i).getName(),
						languages.get(i).getTitle(),
					};
				}
				return rows;
			}
		});
		System.out.println(t.render());
	}

}