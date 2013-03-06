package com.billingstack

import grails.converters.JSON

class InvoicesController {

		def invoicesService

		def list() {
			try {
				render invoicesService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create() {
			try {
				render invoicesService.create(params.merchantId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String invoiceId) {
			try {
				render invoicesService.show(invoiceId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				render invoicesService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String invoiceId) {
			try {
				invoicesService.delete(invoiceId)
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
