import com.billingstack.*

class BootStrap {

    def init = { servletContext ->
    	Language.newInstance(name : "spa", title : "Spanish").save()
    	Language.newInstance(name : "eng", title : "English").save()
    	Language.newInstance(name : "nor", title : "Norwegian").save()

    	Currency.newInstance(name : "eur", title : "Euro").save()
    	Currency.newInstance(name : "usd", title : "US Dollar").save()
    	Currency.newInstance(name : "nok", title : "Norwegian Krone").save()

        def braintree = PaymentGatewayProvider.newInstance(
            name : "braintree",
            title : "Braintree Payments",
            description : "Braintree Payments"
        )
        braintree.addToMethods(
            type : "credit-card",
            name : "visa",
            title : "VISA"
        )
        braintree.addToMethods(
            type : "credit-card",
            name : "master-card",
            title : "MasterCard"
        )
        braintree.save(flush : true, failOnError : true)
    }
    def destroy = {
    }
}
