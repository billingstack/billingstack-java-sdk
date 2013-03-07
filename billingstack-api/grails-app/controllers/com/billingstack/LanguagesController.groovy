package com.billingstack

import grails.converters.JSON

class LanguagesController {

		def languagesService

		def list() {
			try {
				render languagesService.list() as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create() {
			try {
				render languagesService.create(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String languageName) {
			try {
				render languagesService.show(languageName) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				render languagesService.update(request.JSON) as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String languageName) {
			try {
				languagesService.delete(languageName)
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
