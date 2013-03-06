package com.billingstack

class UsersService {
	
	def map(user) {
		def entity = [
			id : user.id
		]
		if(user.merchant) entity.merchant = user.merchant.id
		if(user.customer) entity.customer = user.customer.id
		entity.username = user.username
		entity
	}

	def list() {
		User.list().collect { map(it) }
	}

	def create(merchantId, entity) {
		def user = User.newInstance(
			merchant : Merchant.load(merchantId),
			username : entity.username,
			password : entity.password
		)
		map(user.save(failOnError : true))
	}
	
	def create(merchantId, customerId, entity) {
		def user = User.newInstance(
			merchant : Merchant.load(merchantId),
			customer : Customer.load(customerId),
			username : entity.username,
			password : entity.password
		)
		map(user.save(failOnError : true))
	}

	def show(String userId) {
		map(User.get(userId))
	}

	def update(entity) {
		def user = User.get(entity.id)
		map(user)
	}

	def delete(String userId) {
		User.load(userId).delete(flush : true)
	}

}
