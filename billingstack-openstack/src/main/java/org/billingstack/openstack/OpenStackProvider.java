package org.billingstack.openstack;

import java.util.List;

import org.billingstack.BillingStack;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.Product;

public class OpenStackProvider {
	
	private static final String PROVIDER_NAME = "openstack";
	
	private static final String[] PRODUCTS = { "storage.objects",
		"storage.objects.containers", "storage.objects.size", "image",
		"image.size", "image.upload", "image.update", "image.download",
		"image.serve", "image", "image.size", "image.upload",
		"image.update", "image.download", "image.serve", "image",
		"image.size", "image.upload", "image.update", "image.download",
		"image.serve", "instance", "instance:m1.tiny", "disk.read.bytes",
		"disk.read.requests", "disk.write.bytes", "disk.write.requests",
		"cpu", "cpu_util", "network.incoming.bytes",
		"network.incoming.packets", "network.outgoing.bytes",
		"network.outgoing.packets" };
	
	private static final String[] SOURCES = new String[]{"usa-west","usa-east","eu", "asia"};

	public void install() {
		
	}
	
	public static void main(String[] args) throws Exception {
		BillingStack bs = new BillingStack(Configuration.BILLINGSTACK_ENDPOINT);
		
		List<Merchant> merchants = bs.merchants().list();
		
		for(Merchant m : merchants) {
			
			System.out.println(m);
			
			MerchantTarget mt = bs.merchant(m.getId());
			
			for(final String sName : SOURCES) {
				
				for(final String pName : PRODUCTS) {
					
					Product product = mt.products().create(new Product(){{
						setProvider(PROVIDER_NAME);
						setSource(sName);
						setName(pName);
						setTitle(pName);
					}});
					
					System.out.println("\t" + product);
					
				}
				
			}
		}
	}
	
}
