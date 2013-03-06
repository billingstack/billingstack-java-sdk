package com.billingstack

class TestController {
	
		def currenciesService
		
		def customerPaymentMethodsService
		
		def customersService
		
		def invoicesService
		
		def invoiceLinesService
		
		def languagesService
		
		def merchantsService
		
		def metadataService
		
		def paymentGatewayProvidersService
		
		def paymentGatewaysService
		
		def paymentMethodsService
		
		def planItemsService
		
		def plansService
		
		def productsService
		
		def rolesService
		
		def subscriptionsService
		
		def transactionsService
		
		def usageService
		
		def userRolesService
		
		def usersService

		def create() {
			def merchantAdmin = rolesService.create([name : 'merchant_admin'])
			def customerAdmin = rolesService.create([name : 'customer_admin'])
			def en = languagesService.create([name : 'en'])
			def usd = currenciesService.create([name : 'usd'])
			def status = invoiceStatesService.create([name : 'pending'])
			def braintreeProvider = paymentGatewayProvidersService.create([
				name : "braintree",
				title : "Braintree Payments",
				description : "Braintree description ..."
			])
			def braintreeVisa = paymentMethodsService.create(braintreeProvider.id, [
				name : "visa"
			])
			def braintreeMasterCard = paymentMethodsService.create(braintreeProvider.id, [
				name : "master-card"
			])
			def stacksherpa = merchantsService.create([
				name : "stacksherpa",
				title : "StackSherpa",
				language : 'en',
				currency : 'usd',
				metadata : [
					"key.1" : "value.1"
				]
			])
			stacksherpa = merchantsService.show(stacksherpa.id)
			
			
			def stacksherpaUser = usersService.create(stacksherpa.id, [
				username : "luis",
				password : "secret0"
			])
			def stacksherpaUserRole = userRolesService.create(stacksherpaUser.id, merchantAdmin.id)
			
			def instanceM1Tiny = productsService.create(stacksherpa.id, [
				name : "instance:m1.tiny",
				title : "instance:m1.tiny",
				description : "instance:m1.tiny",
				provider : "openstack-us-west-coast"
			])
			def storage = productsService.create(stacksherpa.id, [
				name : "instance:m1.tiny",
				title : "instance:m1.tiny",
				description : "instance:m1.tiny",
				provider : "openstack-us-west-coast"
			])
			def voIp = productsService.create(stacksherpa.id, [
				name : "instance:m1.tiny",
				title : "instance:m1.tiny",
				description : "instance:m1.tiny",
				provider : "openstack-us-west-coast"
			])
			
			
			def planS = plansService.create(stacksherpa.id, [
				name : "plan.s",
				title : "Small Plan",
				description : "512 MB | 1 Core | 1 Gb"
			])
			
			
			
			def planSinstanceM1Tiny = planItemsService.create(planS.id, [type : "fixed", product : instanceM1Tiny, price : 0.99])
			
			
			
			def planStorage = planItemsService.create(planS.id, [
				product : storage,
				type : "volume",
				pricing : [
					[
						end : 9.99,
						price : 1.00
					],
					[
						start : 10.00,
						end : 19.99,
						price : 0.50
					],
					[
						start : 2.00,
						price : 0.25
					]
				]
			])
			
			
			def planVoIp = planItemsService.create(planS.id, [
				product : voIp,
				type : "time",
				pricing : [
					[
						start : "09:00",
						end : "14:59",
						price : 1.00
					],
					[
						start : "15:00",
						end : "17:59",
						price : 0.50
					],
					[
						start : "18:00",
						end : "08:59",
						price : 0.25
					]
				]
			])
			
			
			
			def braintree = paymentGatewaysService.create(stacksherpa.id, [
				provider : braintreeProvider.id,
				title : "BrainTree"
			])
			
			
			
			def woorea = customersService.create(stacksherpa.id, [
				name : "woorea",
				title : "Woorea Solutions, S.L",
				language : 'en',
				currency : 'usd',
				metadata : [
					"key.1" : "value.1"
				]
			])
			def wooreaUser = usersService.create(stacksherpa.id, woorea.id, [
				username : "luis",
				password : "secret0"
			])
			
			def wooreaUserRole = userRolesService.create(stacksherpaUser.id, customerAdmin.id)
			
			def wooreaPaymentMethod = customerPaymentMethodsService.create(woorea.id, [
				method : braintreeVisa.id
			])
			
			def wooreaSubscription = subscriptionsService.create(woorea.id, [
				plan:planS.id,
				payment_method : wooreaPaymentMethod.id
			])
		
			
			def wooreaSubscriptionUsage = usageService.create(wooreaSubscription.id, [
				:
			])
			
			def invoice = invoicesService.create(stacksherpa.id, [
				customer : woorea.id,
				identifier : "invoice.001",
				due : new Date(),
				subTotal : 1.0,
				taxPercentage : 0.21,
				taxTotal : 0.21,
				total : 0.79,
				status : "pending",
				currency : 'usd'
			])
			
			def transaction = transactionsService.create(stacksherpa.id, [
				invoices : [invoice.id]
			])
			render transaction
		}
		
		def delete() {
		
		}
}
