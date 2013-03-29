modules = {
	application {
    //resource url:'js/application.js'
    resource url:'billingstack/js/underscore-min.js'
    resource url:'billingstack/js/jquery-1.9.1.min.js'
    resource url:'bootstrap/js/bootstrap.min.js'
    resource url:'billingstack/js/angular.min.js'
		resource url:'billingstack/js/bootstrap-notify.js'
		resource url:'billingstack/ng/directives.js'
		resource url:'billingstack/ng/filters.js'
	}
	merchant {
    dependsOn 'application'
    resource url:'billingstack/ng/merchant.js'
		resource url:'billingstack/ng/billingstack.js'
		resource url:'billingstack/ng/customers.js'
		resource url:'billingstack/ng/products.js'
		resource url:'billingstack/ng/plans.js'
		resource url:'billingstack/ng/subscriptions.js'
		resource url:'billingstack/ng/invoices.js'
		resource url:'billingstack/ng/transactions.js'
	}
	settings {
    dependsOn 'application'
    resource url:'billingstack/ng/settings.js'
	}
	customer {
    dependsOn 'application'
    resource url:'billingstack/ng/customer.js'
		resource url:'billingstack/ng/billingstack.js'
		resource url:'billingstack/ng/customers.js'
		resource url:'billingstack/ng/payment_methods.js'
		resource url:'billingstack/ng/subscriptions.js'
		resource url:'billingstack/ng/invoices.js'
		resource url:'billingstack/ng/transactions.js'
		resource url:'billingstack/ng/usage.js'
	}
}