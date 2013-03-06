package com.billingstack

import grails.converters.JSON

class UserRolesController {

		def userRolesService

		def update(String userId, String roleId) {
			try {
				render userRolesService.create(userId, roleId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String userId, String roleId) {
			try {
				userRolesService.delete(userId, roleId)
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
