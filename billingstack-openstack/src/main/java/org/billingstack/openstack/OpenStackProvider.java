package org.billingstack.openstack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.Product;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.Authenticate;
import org.openstack.keystone.model.Access;
import org.openstack.keystone.model.Authentication;
import org.openstack.keystone.model.Authentication.PasswordCredentials;
import org.openstack.nova.NovaClient;
import org.openstack.nova.api.FlavorsCore;
import org.openstack.nova.model.Flavor;

public class OpenStackProvider {
	
	public static final String NAME = "openstack";
	
	private static final String[] SOURCES = new String[]{"usa-west","usa-east","eu", "asia"};
	
	private static final String[][] COMPUTE = {
		
		{"instance","Gauge","","inst ID","Duration of instance"},
		//{"instance:<type>","Gauge","inst ID","Duration of instance <type> (openstack types)"},
		{"memory","Gauge","B","inst ID","Volume of RAM in MB"},
		{"cpu","Cumulative","ns","inst ID","CPU time used"},
		{"vcpus","Gauge","vcpu","inst ID","Number of VCPUs"},
		{"disk.root.size","Gauge","B","inst ID","Size of root disk in GB"},
		{"disk.ephemeral.size" ,"Gauge","B","inst ID","Size of ephemeral disk in GB"},
		{"disk.io.requests","Cumulative","requests","inst ID","Number of disk io requests"},
		{"disk.io.bytes","Cumulative","B","inst ID","Volume of disk io in bytes"},
		{"network.incoming.bytes","Cumulative","B","iface ID","number of incoming bytes on the network"},
		{"network.outgoing.bytes ","Cumulative","B","iface ID","number of outgoing bytes on the network"},
		{"network.incoming.packets","Cumulative","packets","iface ID","number of incoming packets"},
		{"network.outgoing.packets","Cumulative","packets","iface ID","number of outgoing packets"}
	
	};
	
	private static final String[][] NETWORK = {
		
		{"network","Gauge","network","netw ID","Duration of network"},
		{"network.create","Delta","network","netw ID","Creation requests for this network"},
		{"network.update","Delta","network","netw ID","Update requests for this network"},
		{"subnet","Gauge","subnet","subnt ID","Duration of subnet"},
		{"subnet.create","Delta","subnet","subnt ID","Creation requests for this subnet"},
		{"subnet.update","Delta","subnet","subnt ID",	"Update requests for this subnet"},
		{"port","Gauge","port","port ID","Duration of port"},
		{"port.create","Delta","port","port ID","Creation requests for this port"},
		{"port.update","Delta","port","port ID","Update requests for this port"},
		{"router","Gauge","router","rtr ID","Duration of router"},
		{"router.create","Delta","router","rtr ID","Creation requests for this router"},
		{"router.update","Delta","router","rtr ID","Update requests for this router"},
		{"ip.floating","Gauge","ip","ip ID","Duration of floating ip"},
		{"ip.floating.create","Delta","ip","ip ID","Creation requests for this floating ip"},
		{"ip.floating.update","Delta","ip","ip ID","Update requests for this floating ip"}
	
	};
	
	private static final String[][] IMAGE = {
		
		{"image","Gauge","image","image ID","Image polling -> it (still) exists"},
		{"image.size","Gauge","B","image ID","Uploaded image size"},
		{"image.update","Delta","image","image ID","Number of update on the image"},
		{"image.upload","Delta","image","image ID","Number of upload of the image"},
		{"image.delete","Delta","image","image ID","Number of delete on the image"},
		{"image.download","Delta","B","image ID","Image is downloaded"},
		{"image.serve","Delta","B","image ID","Image is served out"},
	
	};
	
	private static final String[][] VOLUME = {
		
		{"volume","Gauge","volume","vol ID","Duration of volume"},
		{"volume.size","Gauge","GiB","vol ID","Size of volume"}
	
	};
	
	private static final String[][] STORAGE = {
		
		{"storage.objects","Gauge","objects","store ID","Number of objects"},
		{"storage.objects.size","Gauge","B","store ID","Total size of stored objects"},
		{"storage.objects.containers","Gauge","containers","store ID","Number of containers"},
		{"storage.objects.incoming.bytes","Delta","B","store ID","Number of incoming bytes"},
		{"storage.objects.outgoing.bytes","Delta","B","store ID","Number of outgoing bytes"}
	
	};
	
	private static final String[][] ENERGY = {
		
		{"energy","Cumulative","kWh","probe ID","Amount of energy"},
		{"power","Gauge","W","probe ID","Power consumption"}
	
	};
	
	public void execute(String[] args) {
		
	}
	
	public static void install(MerchantTarget mt, String sourceName) throws IOException {
		
		if(sourceName == null || sourceName.length() < 1) {
			throw new RuntimeException("source name not provided");
		}
		
		push(mt, sourceName, COMPUTE);
		pushInstanceTypes(mt, sourceName);
		push(mt, sourceName, NETWORK);
		push(mt, sourceName, IMAGE);
		push(mt, sourceName, VOLUME);
		push(mt, sourceName, STORAGE);
		push(mt, sourceName, ENERGY);
		
	}
	
	public static void main(String[] args) throws Exception {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/billingstack.properties"));
		
		BillingStack client = new BillingStack(properties);
		BillingStackEndpoint bs = client.create();
		
		List<Merchant> merchants = bs.merchants().list();
		
		for(Merchant m : merchants) {
			
			System.out.println(m);
			
			for(final String sName : SOURCES) {
				
			}
			
			
		}
		
		client.close();
		
	}
	
	private static void push(MerchantTarget mt, final String source, String[][] products) {
		for(final String[] properties : products) {
			
			Product product = mt.products().create(new Product(){{
				setName(properties[0]);
				setTitle(properties[0]);
				setProperties(new HashMap<String, Object>(){{
					put("provider", NAME);
					put("source", source);
				}});
			}});
			
		}
	}
	
	private static void pushInstanceTypes(MerchantTarget mt, final String source) throws IOException {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/billingstack.properties"));
		
		KeystoneClient keystone = new KeystoneClient(properties.getProperty("identity.endpoint"));
		//keystone.enableLogging(Logger.getLogger("keystone"), 100 * 1024);
		Authentication authentication = new Authentication();
		PasswordCredentials passwordCredentials = new PasswordCredentials();
		passwordCredentials.setUsername(properties.getProperty("keystone.username"));
		passwordCredentials.setPassword(properties.getProperty("keystone.password"));
		authentication.setTenantName("admin");
		authentication.setPasswordCredentials(passwordCredentials);
		
		//access with scoped token
		Access access = keystone.execute(new Authenticate(authentication));
		
		NovaClient nova = new NovaClient(properties.getProperty("nova.endpoint") + "/" + access.getToken().getTenant().getId());
		nova.token(access.getToken().getId());
		
		List<Flavor> flavors = nova.execute(new FlavorsCore.ListFlavors()).getList();
		
		for(final Flavor f : flavors) {
			
			Product product = mt.products().create(new Product(){{
				setName("instance:"+f.getName());
				setTitle("instance:"+f.getName());
				setProperties(new HashMap<String, Object>(){{
					put("provider", OpenStackProvider.NAME);
					put("source", source);
				}});
			}});
			
		}
	}
	
	
}
