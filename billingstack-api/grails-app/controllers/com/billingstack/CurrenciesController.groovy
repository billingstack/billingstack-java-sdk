package com.billingstack

import grails.converters.JSON

class CurrenciesController {

		def currenciesService

		def list() {
			try {
				render currenciesService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create() {
			try {
				render currenciesService.create(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String currencyName) {
			try {
				def currency = currenciesService.show(currencyName)
				return currenciesService.map(it)
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				def currency = request.JSON
				currenciesService.update(currency)
				return currenciesService.map(currency)
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String currencyName) {
			try {
				currenciesService.delete(currencyName)
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
