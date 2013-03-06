package com.billingstack

import grails.converters.JSON

class InvoiceStatesController {

		def invoiceStatesService

		def list() {
			try {
				render invoiceStatesService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create() {
			try {
				render invoiceStatesService.create(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String invoiceStateId) {
			try {
				render invoiceStatesService.show(invoiceStateId)
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try { 
				render invoiceStatesService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String invoiceStateId) {
			try {
				invoiceStatesService.delete(invoiceStateId)
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
