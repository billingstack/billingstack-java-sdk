package com.billingstack

import grails.converters.JSON

class MetadataController {

		def metadatasService

		def list() {
			try {
				def metadatas = metadatasService.list()
				return metadatas.collect { metadatasService.map(it) } as JSON
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def create() {
			try {
				def metadata = request.JSON
				metadatasService.create(metadata)
				return metadatasService.map(metadata)
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def show(String metadataId) {
			try {
				def metadata = metadatasService.show(metadataId)
				return metadatasService.map(it)
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def update() {
			try {
				def metadata = request.JSON
				metadatasService.update(metadata)
				return metadatasService.map(metadata)
			} catch(e) {
				render onError(e) as JSON
			}
		}

		def delete(String metadataId) {
			try {
				metadatasService.delete(metadataId)
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
