package org.billingstack.console;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Language;
import org.openstack.console.utils.Column;
import org.openstack.console.utils.Table;
import org.openstack.console.utils.TableModel;

public class LanguageList extends BillingStackCommand {
	
	public LanguageList() {
		super("language-list");
	}

	@Override
	public void execute(BillingStackEndpoint bs, CommandLine cmd) {
		final List<Language> languages = bs.languages().list();
		
		Table t = new Table(new TableModel<Language>(languages) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("name", 4, Column.ALIGN_LEFT),
					new Column("title", 32, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[] getRow(Language language) {
				return new String[]{
					language.getName(),
					language.getTitle()
				};
			}
		});
		System.out.println(t.render());
	}

}
