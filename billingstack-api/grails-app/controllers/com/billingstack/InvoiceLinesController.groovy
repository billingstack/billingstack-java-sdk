package com.billingstack

import grails.converters.JSON

class InvoiceLinesController {

		def invoiceLinesService

		def list() {
			try {
				render invoiceLinesService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create() {
			try {
				render invoiceLinesService.create(params.invoiceId, request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String invoiceLineId) {
			try {
				render invoiceLinesService.show(invoiceLineId) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				render invoiceLinesService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String invoiceLineId) {
			try {
				invoiceLinesService.delete(invoiceLineId)
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
