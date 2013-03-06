package com.billingstack

import grails.converters.JSON

class PlanItemsController {

		def planItemsService

		def list() {
			try {
				render planItemsService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create(String planId, String productId) {
			try {
				render planItemsService.create(planId, productId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String planId, String productId) {
			try {
				return planItemsService.show(planId, productId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update(String planId, String productId) {
			try {
				render planItemsService.update(planId, productId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String planId, String productId) {
			try {
				planItemsService.delete(planId, productId)
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
