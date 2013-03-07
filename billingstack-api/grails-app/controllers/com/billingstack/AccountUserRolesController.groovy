package com.billingstack

import grails.converters.JSON

class AccountUserRolesController {

		def accountUserRolesService
		
		def listUsers(String accountId) {
			try {
				def filters = [:]
				render accountUserRolesService.listUsers(accountId, filters) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}
		
		def create(String accountId, String userId, String roleId) {
			try {
				render accountUserRolesService.create(accountId, userId, roleId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String accountId, String userId, String roleId) {
			try {
				accountUserRolesService.delete(accountId, userId, roleId)
				render(status : 204)
			} catch(e) {
				render onError(e) as JSON
			}
		}
		
		private onError(e) {
			log.error(e.message,e)
			response.status = 500
			["error":e.message]
		}

}
