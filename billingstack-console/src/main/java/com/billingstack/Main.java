package com.billingstack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;

import com.billingstack.commands.Bootstrap;
import com.billingstack.commands.Clean;
import com.billingstack.commands.Command;
import com.billingstack.commands.CurrencyList;
import com.billingstack.commands.CustomerCreate;
import com.billingstack.commands.CustomerDelete;
import com.billingstack.commands.CustomerEnvironment;
import com.billingstack.commands.CustomerList;
import com.billingstack.commands.CustomerShow;
import com.billingstack.commands.Echo;
import com.billingstack.commands.EnvironmentUpdate;
import com.billingstack.commands.Exit;
import com.billingstack.commands.FixedPlanItemCreate;
import com.billingstack.commands.KeystoneRoleCreate;
import com.billingstack.commands.KeystoneRoleList;
import com.billingstack.commands.KeystoneServiceList;
import com.billingstack.commands.KeystoneTenantCreate;
import com.billingstack.commands.KeystoneTenantDelete;
import com.billingstack.commands.KeystoneTenantList;
import com.billingstack.commands.KeystoneUserCreate;
import com.billingstack.commands.KeystoneUserDelete;
import com.billingstack.commands.KeystoneUserList;
import com.billingstack.commands.KeystoneUserShow;
import com.billingstack.commands.LanguageList;
import com.billingstack.commands.MerchantCreate;
import com.billingstack.commands.MerchantDelete;
import com.billingstack.commands.MerchantEnvironment;
import com.billingstack.commands.MerchantList;
import com.billingstack.commands.NovaServerList;
import com.billingstack.commands.OpenStackSourceCreate;
import com.billingstack.commands.OpenStackSubscribe;
import com.billingstack.commands.OpenStackTenantEnvironment;
import com.billingstack.commands.PlanCreate;
import com.billingstack.commands.PlanDelete;
import com.billingstack.commands.PlanList;
import com.billingstack.commands.PlanShow;
import com.billingstack.commands.ProductDelete;
import com.billingstack.commands.ProductList;
import com.billingstack.commands.RoleList;
import com.billingstack.commands.SubscriptionCreate;
import com.billingstack.commands.SubscriptionList;
import com.billingstack.commands.UserCreate;
import com.billingstack.commands.UserList;
import com.billingstack.commands.UserShow;

public class Main {
	
	private static Map<String, Command> commands = new HashMap<String, Command>();
	
	static {
		add(new KeystoneUserList());
		add(new KeystoneUserCreate());
		add(new KeystoneUserShow());
		add(new KeystoneUserDelete());
		add(new KeystoneTenantList());
		add(new KeystoneTenantCreate());
		add(new KeystoneTenantDelete());
		add(new KeystoneRoleList());
		add(new KeystoneRoleCreate());
		add(new KeystoneServiceList());
		add(new OpenStackTenantEnvironment());
		add(new NovaServerList());
		add(new EnvironmentUpdate());
		add(new Bootstrap());
		add(new Clean());
		add(new OpenStackSourceCreate());
		add(new OpenStackSubscribe());
		add(new RoleList());
		add(new UserList());
		add(new UserCreate());
		add(new UserShow());
		add(new LanguageList());
		add(new CurrencyList());
		add(new MerchantList());
		add(new MerchantCreate());
		add(new MerchantDelete());
		add(new MerchantEnvironment());
		add(new ProductList());
		add(new ProductDelete());
		add(new PlanList());
		add(new PlanCreate());
		add(new PlanShow());
		add(new PlanDelete());
		add(new FixedPlanItemCreate());
		add(new CustomerList());
		add(new CustomerCreate());
		add(new CustomerShow());
		add(new CustomerDelete());
		add(new CustomerEnvironment());
		add(new SubscriptionList());
		add(new SubscriptionCreate());
		add(new Echo());
		add(new Exit());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		try {
			banner();
			HelpFormatter helpFormatter = new HelpFormatter();
			CommandLineParser commandLineParser = new GnuParser();
			ConsoleReader consoleReader = new ConsoleReader();
			consoleReader.addCompleter(completer());
			Environment env = new Environment();
			String input = consoleReader.readLine(env.getPrompt());
			while(input != null) {
				String[] cmd = parse(input);
				if(cmd.length > 0) {
					Command command = commands.get(cmd[0]);
					if(command != null) {
						try {
							CommandLine commandLine = commandLineParser.parse(command.getOptions(), Arrays.copyOfRange(cmd, 1, cmd.length));
							command.call(env,commandLine);
						} catch (Exception e) {
							helpFormatter.printHelp(command.getName(), command.getOptions());
							e.printStackTrace();
							//Console.red(e.getMessage());
						}
					}
				}
				input = consoleReader.readLine(env.getPrompt());
			}
		} catch (Exception e) {
			e.printStackTrace();
			//HelpFormatter formatter = new HelpFormatter();
			//formatter.printHelp("myapp", header, options, footer, true);
		}
	}
	
	public static void add(Command command) {
		commands.put(command.getName(), command);
	}
	
	public static Completer completer() {

        List<Completer> c = new ArrayList<Completer>();
        for (String cmd : commands.keySet()) {

            List<Completer> cmdCompleters = new ArrayList<Completer>();
            cmdCompleters.add(new StringsCompleter(cmd));
            cmdCompleters.add(commands.get(cmd).getCompleter());
            ArgumentCompleter ac = new ArgumentCompleter(cmdCompleters);
            c.add(ac);
        }
        return new AggregateCompleter(c);
	}
	
	public static String[] parse(String input) {
        if (input == null || input.length() == 0) {
            //no command? no string
            return new String[0];
        }
        // parse with a simple finite state machine

        final int normal = 0;
        final int inQuote = 1;
        final int inDoubleQuote = 2;
        int state = normal;
        StringTokenizer tok = new StringTokenizer(input, "\"\' ", true);
        Vector v = new Vector();
        StringBuffer current = new StringBuffer();
        boolean lastTokenHasBeenQuoted = false;

        while (tok.hasMoreTokens()) {
            String nextTok = tok.nextToken();
            switch (state) {
            case inQuote:
                if ("\'".equals(nextTok)) {
                    lastTokenHasBeenQuoted = true;
                    state = normal;
                } else {
                    current.append(nextTok);
                }
                break;
            case inDoubleQuote:
                if ("\"".equals(nextTok)) {
                    lastTokenHasBeenQuoted = true;
                    state = normal;
                } else {
                    current.append(nextTok);
                }
                break;
            default:
                if ("\'".equals(nextTok)) {
                    state = inQuote;
                } else if ("\"".equals(nextTok)) {
                    state = inDoubleQuote;
                } else if (" ".equals(nextTok)) {
                    if (lastTokenHasBeenQuoted || current.length() != 0) {
                        v.addElement(current.toString());
                        current = new StringBuffer();
                    }
                } else {
                    current.append(nextTok);
                }
                lastTokenHasBeenQuoted = false;
                break;
            }
        }
        if (lastTokenHasBeenQuoted || current.length() != 0) {
            v.addElement(current.toString());
        }
        if (state == inQuote || state == inDoubleQuote) {
            throw new RuntimeException("unbalanced quotes in " + input);
        }
        String[] args = new String[v.size()];
        v.copyInto(args);
        return args;
    }
	
	public static final void banner() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("Welcome to BillingStack Console").append("\n\n");
		sb.append("Below you can show a set of useful commands").append("\n\n");
		sb.append("billingstack> set-property -key billingstack.endpoint -value http://<your_billingstack_host:port>/v1").append("\n");
		sb.append("billingstack> set-property -key logging -value true").append("\n");
		sb.append("billingstack> set-property -key keystone.endpoint -value http://<your_keystone_admin_host:port>/v2.0").append("\n");
		sb.append("billingstack> set-property -key keystone.username -value <your_keystone_admin_username>").append("\n");
		sb.append("billingstack> set-property -key keystone.password -value <your_keystone_admin_password>").append("\n");
		sb.append("billingstack> set-property -key keystone.tenant_name -value <your_keystone_admin_tenant_name>").append("\n");
		sb.append("billingstack> set-property -key identity.endpoint -value http://<your_keystone_public_host:port>/v2.0").append("\n");
		sb.append("billingstack> set-property -key nova.endpoint -value http://<your_nova_public_host:port>/v2").append("\n");
		sb.append("billingstack> merchant-list").append("\n\n");
		System.out.println(sb);
	}

}

