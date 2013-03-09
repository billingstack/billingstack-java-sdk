package com.billingstack

import grails.converters.JSON

class BillingStackController {

    def create() {
      try {
        def json = request.JSON
        println json
        def user
        if(json.token) {
          //get merchant.id
        } else if(json.api_key && json.api_secret) {
          user = User.findByApiKeyAndApiSecret(json.api_key, json.api_secret)
        } else {
          user = User.findByUsername(json.username)
        }
        if(user) {
          if(!user.password.equals(json.password)) {
            throw new RuntimeException("wrong.password")
          }
          def access = [
            token : (UUID.randomUUID() as String).replaceAll('-',""),
            endpoint : createLink(controller : 'application', absolute : true) as String
          ]
          if(json.account) {
            def account = Account.findByIdOrName(json.account, json.account)
            def ur = AccountUserRole.findAllByUserAndAccount(user, account)
            access.account_id = account.id
            access.account_name = account.name
            access.account_roles = ur.collect { it.role.name }
            
          }
          render(text: access as JSON, contentType: 'application/json', encoding:"UTF-8")
          //def tokens = hazelcastService.map("tokens")
          //tokens.put(access.token.id, access.token)
          
        } else {
          response.status = 403
          def error = ["error":"user.not.found"]
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
