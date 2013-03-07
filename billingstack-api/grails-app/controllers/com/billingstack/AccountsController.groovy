package com.billingstack

import grails.converters.JSON

class AccountsController {

	def accountsService

	def list() {
		try {
			render accountsService.list() as JSON
		} catch(e) {
			render onError(e) as JSON
		}
	}

	def create() {
		try {
			render accountsService.create(request.JSON) as JSON
		} catch(e) {
			render onError(e) as JSON
		}
	}

	def show(String accountId) {
		try {
			render accountsService.show(accountId) as JSON
		} catch(e) {
			render onError(e) as JSON
		}
	}

	def update() {
		try {
			render accountsService.update(request.JSON) as JSON
		} catch(e) {
			render onError(e) as JSON
		}
	}

	def delete(String accountId) {
		try {
			accountsService.delete(accountId)
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
