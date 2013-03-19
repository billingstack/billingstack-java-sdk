package com.billingstack

import grails.converters.JSON

class UserRolesController {

		def userRolesService
		
		def list(String merchantId) {
			try {
				def filters = [:]
				render userRolesService.listUsers(merchantId, filters) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}
		
		def create(String merchantId, String userId, String roleId) {
			try {
				render userRolesService.create(merchantId, userId, roleId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String merchantId, String userId, String roleId) {
			try {
				userRolesService.delete(merchantId, userId, roleId)
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
