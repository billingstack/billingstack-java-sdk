package com.billingstack

class MetadataService {
	
	def map() {
		[
			:
		]
	}

	def list() {
		Metadata.list()
	}

	def create(billingStackEntityId, entity) {
		def metadata = [
			billingStackEntity : BillingStackEntity.load(billingStackEntityId)
		]
		map(metadata.save())
	}

	def show(String metadataId) {
		Metadata.get(metadataId)
	}

	def update(entity) {
		def metadata = Metadata.get(entity.id)
		metadata
	}

	def delete(String metadataId) {
		Metadata.load(metadataId).delete(flush : true)
	}

}
