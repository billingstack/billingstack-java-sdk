package com.billingstack

import grails.converters.JSON

class RolesController {

		def rolesService

		def list() {
			try {
				render rolesService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create() {
			try {
				render rolesService.create(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String roleId) {
			try {
				render rolesService.show(roleId)
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try { 
				render rolesService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String roleId) {
			try {
				rolesService.delete(roleId)
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
