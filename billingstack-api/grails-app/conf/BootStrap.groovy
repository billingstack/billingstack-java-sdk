import com.billingstack.*

class BootStrap {

    def paymentGatewayProvidersService

    def init = { servletContext ->
    	Language.newInstance(name : "spa", title : "Spanish").save()
    	Language.newInstance(name : "eng", title : "English").save()
    	Language.newInstance(name : "nor", title : "Norwegian").save()

    	Currency.newInstance(name : "eur", title : "Euro").save()
    	Currency.newInstance(name : "usd", title : "US Dollar").save()
    	Currency.newInstance(name : "nok", title : "Norwegian Krone").save()

        def braintree = paymentGatewayProvidersService.create([
            name : "braintree",
            title : "Braintree Payments",
            description : "Braintree Payments",
            methods : [
                [
                type : "credit-card",
                name : "visa",
                title : "VISA"
                ],
                [
                type : "credit-card",
                name : "master-card",
                title : "MasterCard"
                ]
            ]
        ])
    }
    def destroy = {
    }
}
