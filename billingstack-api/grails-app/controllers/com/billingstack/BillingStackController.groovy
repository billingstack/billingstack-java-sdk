package com.billingstack

import grails.converters.JSON

class BillingStackController {

    def create() {
      try {
        def json = request.JSON
        def user
        if(json.token) {
          //get merchant.id
        } else if(json.api_key && json.api_secret) {
          user = User.findByApiKeyAndApiSecret(json.api_key, json.api_secret)
        } else if(json.merchant) {
          if(json.customer) {
            //customer login
            user = User.where {
              ( (merchant.id == json.merchant ||  merchant.name == json.merchant) && (customer.id == json.customer ||  customer.name == json.customer) && username == json.username && password == json.password)
            }.find()
          } else {
            //merchant login
            user = User.where {
              ( (merchant.id == json.merchant ||  merchant.name == json.merchant) && customer == null && username == json.username && password == json.password)
            }.find()
          }
        } else {
          //super login
          user = User.where {
            ( merchant == null && customer == null && username == json.username && password == json.password )
          }.find()
        }
        if(user) {
          def ur = UserRole.findAllByUser(user)
          def access = [
            token : (UUID.randomUUID() as String).replaceAll('-',""),
						endpoint : createLink(controller : 'application', absolute : true) as String
          ]
          if(json.merchant) {
            def merchant = Merchant.findByIdOrName(json.merchant, json.merchant)
            access.merchant_id = merchant.id
            access.merchant_name = merchant.name
						access.merchant_endpoint = createLink(controller : 'merchants', params : [id : merchant.id], absolute : true) as String
            if(json.customer) {
              def customer = Customer.findByIdOrName(json.customer, json.customer)
              access.customer_id = customer.id
							access.customer_name = customer.name
							access.customer_endpoint = createLink(controller : 'customers', params : [merchant : merchant.id, id : customer.id], absolute : true) as String
            }
          }
          //def tokens = hazelcastService.map("tokens")
          //tokens.put(access.token.id, access.token)
          render(text: access as JSON, contentType: 'application/json', encoding:"UTF-8")
        } else {
          response.status = 403
          def error = ["error":"Merchant not found"]
          render(text: error as JSON, contentType: 'application/json', encoding:"UTF-8")
        }
      } catch(e) {
          response.status = 500
          def error = ["error":e.message]
          render(text: error as JSON, contentType: 'application/json', encoding:"UTF-8")
          return
      }

    }

    def delete() {
      try {
        //def tokens = hazelcastService.map("tokens")
      } catch(e) {
          response.status = 500
          def error = ["error":e.message]
          render(text: error as JSON, contentType: 'application/json', encoding:"UTF-8")
          return
      } 
    }
}
