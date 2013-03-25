import com.billingstack.*

class BootStrap {

    def init = { servletContext ->
    	Language.newInstance(name : "spa", title : "Spanish").save()
    	Language.newInstance(name : "eng", title : "English").save()
    	Language.newInstance(name : "nor", title : "Norwegian").save()

    	Currency.newInstance(name : "eur", title : "Euro").save()
    	Currency.newInstance(name : "usd", title : "US Dollar").save()
    	Currency.newInstance(name : "nok", title : "Norwegian Krone").save()
    }
    def destroy = {
    }
}
