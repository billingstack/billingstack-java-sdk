class UrlMappings {

	static mappings = {
		
		/* BILLINGSTACK IDENTITY API */
		
		"/tokens"(controller : "billingStack") {
			action = [POST : "create", DELETE : "logout"]
		}
		"/roles"(controller : "roles"){
			action = [GET : "list", POST : "create"]
		}
		"/roles/$roleId"(controller : "roles"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}

		/* BILLINGSTACK CORE API */
		
		"/languages"(controller : "languages"){
			action = [GET : "list", POST : "create"]
		}
		"/languages/$languageName"(controller : "languages"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/currencies"(controller : "currencies"){
			action = [GET : "list", POST : "create"]
		}
		"/currencies/$currencyName"(controller : "currencies"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/invoice-states"(controller : "invoiceStates"){
			action = [GET : "list", POST : "create"]
		}
		"/invoice-states/$invoiceStateId"(controller : "invoiceStates"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/payment-gateway-providers"(controller : "paymentGatewayProviders"){
			action = [GET : "list", POST : "create"]
		}
		"/payment-gateway-providers/$paymentGatewayProviderId"(controller : "paymentGatewayProviders"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/payment-gateway-providers/$paymentGatewayProviderId/payment-methods"(controller : "paymentMethods"){
			action = [GET : "list", POST : "create"]
		}
		"/payment-gateway-providers/$paymentGatewayProviderId/payment-methods/$paymentMethodId"(controller : "paymentMethods"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/merchants"(controller : "merchants"){
			action = [GET : "list", POST : "create"]
		}
		"/merchants/$merchantId"(controller : "merchants"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/merchants/$merchantId/users"(controller : "users"){
			action = [GET : "list", POST : "create"]
		}
		"/merchants/$merchantId/users/$userId"(controller : "users") {
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/merchants/$merchantId/users/$userId/roles/$roleId"(controller : "userRoles"){
			action = [PUT : "create", DELETE : "delete"]
		}		
		"/merchants/$merchantId/products"(controller : "products"){
			action = [GET : "list", POST : "create"]
		}
		"/merchants/$merchantId/products/$productId"(controller : "products"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/merchants/$merchantId/plans"(controller : "plans"){
			action = [GET : "list", POST : "create"]
		}
		"/merchants/$merchantId/plans/$planId"(controller : "plans"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/merchants/$merchantId/plans/$planId/items"(controller : "planItems"){
			action = [GET : "list"]
		}
		"/merchants/$merchantId/plans/$planId/items/$productId"(controller : "planItems"){
			action = [GET : "show", DELETE : "delete", PUT : "create", PATCH : "update"]
		}
		"/merchants/$merchantId/plans/$planId/items/$planItemId/ranges"(controller : "ranges"){
			action = [GET : "list", POST : "create"]
		}
		"/merchants/$merchantId/plans/$planId/items/$planItemId/ranges/$planItemRangeId"(controller : "ranges"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/merchants/$merchantId/payment-gateways"(controller : "paymentGateways"){
			action = [GET : "list", POST : "create"]
		}
		"/merchants/$merchantId/payment-gateways/$paymentGatewayId"(controller : "paymentGateways"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/merchants/$merchantId/customers"(controller : "customers"){
			action = [GET : "list", POST : "create"]
		}
		"/merchants/$merchantId/customers/$customerId"(controller : "customers"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/merchants/$merchantId/customers/$customerId/payment-methods"(controller : "customerPaymentMethods"){
			action = [GET : "list", POST : "create"]
		}
		"/merchants/$merchantId/customers/$customerId/payment-methods/$paymentMethodId"(controller : "customerPaymentMethods"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/merchants/$merchantId/subscriptions"(controller : "subscriptions"){
			action = [GET : "list", POST : "create"]
		}
		"/merchants/$merchantId/subscriptions/$subscriptionId"(controller : "subscriptions"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/merchants/$merchantId/subscriptions/$subscriptionId/usage"(controller : "usage"){
			action = [GET : "list", POST : "create"]
		}
		"/merchants/$merchantId/subscriptions/$subscriptionId/usage/$usageId"(controller : "usage"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/merchants/$merchantId/invoices"(controller : "invoices"){
			action = [GET : "list", POST : "create"]
		}
		"/merchants/$merchantId/invoices/$invoiceId"(controller : "invoices"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/merchants/$merchantId/invoices/$invoiceId/lines"(controller : "invoiceLines"){
			action = [GET : "list", POST : "create"]
		}
		"/merchants/$merchantId/invoices/$invoiceId/lines/$invoiceLineId"(controller : "invoiceLines"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/merchants/$merchantId/transactions"(controller : "transactions"){
			action = [GET : "list", POST : "create"]
		}
		"/merchants/$merchantId/transactions/$transactionId"(controller : "transactions"){
			action = [GET : "show", DELETE : "delete", PUT : "update"]
		}
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
