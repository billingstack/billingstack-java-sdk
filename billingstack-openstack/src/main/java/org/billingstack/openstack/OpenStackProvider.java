package org.billingstack.openstack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.billingstack.BillingStack;
import org.billingstack.FixedPlanItem;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.Plan;
import org.billingstack.Product;
import org.billingstack.TimePlanItem;
import org.billingstack.TimeRangePricing;
import org.billingstack.VolumePlanItem;
import org.billingstack.VolumeRangePricing;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.Authenticate;
import org.openstack.keystone.model.Access;
import org.openstack.keystone.model.Authentication;
import org.openstack.keystone.model.Authentication.PasswordCredentials;
import org.openstack.nova.NovaClient;
import org.openstack.nova.api.FlavorsCore;
import org.openstack.nova.model.Flavor;

public class OpenStackProvider {
	
	private static final String PROVIDER_NAME = "openstack";
	
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
	
	public void install() {
		
	}
	
	public static void main(String[] args) throws Exception {
		
		BillingStack bs = new BillingStack(Configuration.BILLINGSTACK_ENDPOINT);
		
		List<Merchant> merchants = bs.merchants().list();
		
		for(Merchant m : merchants) {
			
			System.out.println(m);
			
			MerchantTarget mt = bs.merchant(m.getId());
			
			for(final String sName : SOURCES) {
				
				push(mt, sName, COMPUTE);
				pushInstanceTypes(mt, sName);
				push(mt, sName, NETWORK);
				push(mt, sName, IMAGE);
				push(mt, sName, VOLUME);
				push(mt, sName, STORAGE);
				push(mt, sName, ENERGY);
				
			}
			
			final List<Product> products = mt.products().list();
			
			mt.plans().create(new Plan() {{
				setName("plan.m");
				setTitle("Plan M");
			}});
			
			final List<Plan> plans = mt.plans().list();
			
			
			String pid = product(products, PROVIDER_NAME, SOURCES[0], "instance:m1.tiny");
			mt.plan(plans.get(0).getId()).item(pid).create(new FixedPlanItem(){{
				setPrice(new BigDecimal("0.99"));
			}});
			
			pid = product(products, PROVIDER_NAME, SOURCES[0], "instance:m1.tiny");
			mt.plan(plans.get(0).getId()).item(pid).update(new FixedPlanItem(){{
				setPrice(new BigDecimal("1.99"));
			}});
			
			pid = product(products, PROVIDER_NAME, SOURCES[0], "instance:m1.small");
			mt.plan(plans.get(0).getId()).item(pid).create(new VolumePlanItem(){{
				setPricing(new ArrayList<VolumeRangePricing>() {{
					VolumeRangePricing pricing0 = new VolumeRangePricing();
					pricing0.setEnd(new BigDecimal(9.99));
					pricing0.setPrice(new BigDecimal(10.00));
					add(pricing0);
					VolumeRangePricing pricing1 = new VolumeRangePricing();
					pricing1.setStart(new BigDecimal(10.00));
					pricing1.setEnd(new BigDecimal(19.99));
					pricing1.setPrice(new BigDecimal(8.00));
					add(pricing1);
					VolumeRangePricing pricing2 = new VolumeRangePricing();
					pricing2.setStart(new BigDecimal(20.00));
					pricing2.setPrice(new BigDecimal(5.00));
					add(pricing2);
				}});
			}});
			
			pid = product(products, PROVIDER_NAME, SOURCES[0], "instance:m1.large");
			mt.plan(plans.get(0).getId()).item(pid).create(new TimePlanItem(){{
				setPricing(new ArrayList<TimeRangePricing>() {{
					TimeRangePricing pricing0 = new TimeRangePricing();
					pricing0.setStart("08:00");
					pricing0.setEnd("14:59");
					pricing0.setPrice(new BigDecimal(8.00));
					add(pricing0);
					TimeRangePricing pricing1 = new TimeRangePricing();
					pricing1.setStart("15:00");
					pricing1.setEnd("07:59");
					pricing1.setPrice(new BigDecimal(8.00));
					add(pricing1);
				}});
			}});
			
			mt.plan(plans.get(0).getId()).show();
		}
		
		bs.close();
		
	}
	
	private static void push(MerchantTarget mt, final String source, String[][] products) {
		for(final String[] properties : products) {
			
			Product product = mt.products().create(new Product(){{
				setProvider(PROVIDER_NAME);
				setSource(source);
				setName(properties[0]);
				setTitle(properties[0]);
			}});
			
		}
	}
	
	private static void pushInstanceTypes(MerchantTarget mt, final String source) {
		KeystoneClient keystone = new KeystoneClient(Configuration.IDENTITY_ENDPOINT);
		//keystone.enableLogging(Logger.getLogger("keystone"), 100 * 1024);
		Authentication authentication = new Authentication();
		PasswordCredentials passwordCredentials = new PasswordCredentials();
		passwordCredentials.setUsername(Configuration.KEYSTONE_USERNAME);
		passwordCredentials.setPassword(Configuration.KEYSTONE_PASSWORD);
		authentication.setTenantName("admin");
		authentication.setPasswordCredentials(passwordCredentials);
		
		//access with scoped token
		Access access = keystone.execute(new Authenticate(authentication));
		
		NovaClient nova = new NovaClient(Configuration.NOVA_ENDPOINT + "/" + access.getToken().getTenant().getId(), access.getToken().getId());
		
		List<Flavor> flavors = nova.execute(new FlavorsCore.ListFlavors()).getList();
		
		for(final Flavor f : flavors) {
			
			Product product = mt.products().create(new Product(){{
				setProvider(PROVIDER_NAME);
				setSource(source);
				setName("instance:"+f.getName());
				setTitle("instance:"+f.getName());
			}});
			
		}
	}
	
	private static String product(List<Product> products, String provider, String source, String name) {
		for(Product p : products) {
			if(provider.equals(p.getProvider()) && source.equals(p.getSource()) && name.equals(p.getName())) {
				return p.getId();
			}
		}
		throw new RuntimeException("product.not.found");
	}
}
