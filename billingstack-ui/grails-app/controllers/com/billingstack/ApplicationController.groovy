package com.billingstack

import org.billingstack.*

import javax.annotation.PostConstruct

class ApplicationController {
	
		static scope = "singleton"
  
    def grailsApplication
    
		def bs

		@PostConstruct
		private void init() {
			def bsc = new BillingStack()
			bs = bsc.create(grailsApplication.config.billingstack.endpoint)
			bs.logger()
		}

    def signUp() {
      if(request.post) {
        try {
          def merchant = bs.merchants().create(new Merchant(
            name : params.name,
            title : params.title,
            language : params.language,
            currency : params.currency
          ))
					/*
          def builder = new groovy.json.JsonBuilder()
          def slurper = new groovy.json.JsonSlurper()
          builder.call([
            name : params.name,
            language : params.language,
            currency : params.currency,
            user :[
              username : params.user.username,
              password : params.user.password
            ]
          ])
          def response = http.preparePost(grailsApplication.config.billingstack.endpoint+'/merchants')
            .setBody(builder.toString())
            .execute()
            .get()
          slurper.parseText(response.responseBody)
					*/
          redirect(action : "signIn")
        } catch(e) {
          flash.error = e.message
        }
      } else {
				[
					languages : bs.languages().list(),
					currencies : bs.currencies().list()
				]
			}
    }
    
    def signIn() {
      if(request.post) {
        try {
					def merchant = bs.merchants().list().find {
						it.name == params.merchant
					}
					if(merchant) {
						session.access = [
	            endpoint : "${grailsApplication.config.billingstack.endpoint}/merchants/${merchant.id}"
	          ]
						redirect(action : "merchant")
					} else {
						flash.error = "Merchant not found"
					}
          

          /*
          def builder = new groovy.json.JsonBuilder()
          def slurper = new groovy.json.JsonSlurper()
          builder.call([
              merchant : params.merchant,
              customer : params.customer,
              username : params.username,
              password : params.password
          ])
          def response = http.preparePost(grailsApplication.config.billingstack.endpoint+'/authenticate')
            .setBody(builder.toString())
            .execute()
            .get()
          if(response.statusCode == 200) {
            session.access = slurper.parseText(response.responseBody)
						if(session.access.customer) {
							redirect(action : "customer")
						} else {
							redirect(action : "merchant")
						}
          } else {
            throw new RuntimeException(slurper.parseText(response.responseBody).error)
          }
					*/
					
        } catch(e) {
          flash.error = e.message
        }
      }
    }
    
    def signOut() {
      redirect(action : "signIn")
    }

		def merchant() {
			[
				languages : bs.languages().list(),
				currencies : bs.currencies().list()
			]
		}
		
		def customer(String customer) {
			//if(!session.access.customer) {
			//	session.access.customer = [endpoint : "${session.access.merchant.endpoint}/customers/${customer}" ]
			//}
			[
				languages : bs.languages().list(),
				currencies : bs.currencies().list()
			]
		}
		
		def settings() {
			[
				languages : bs.languages().list(),
				currencies : bs.currencies().list(),
				paymentGatewayProviders : bs.paymentGatewayProviders().list()
			]
		}

}
