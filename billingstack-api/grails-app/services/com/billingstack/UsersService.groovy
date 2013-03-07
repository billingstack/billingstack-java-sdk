package com.billingstack

class UsersService {
	
	def map(user) {
		def entity = [
			id : user.id,
			username : user.username
		]
		entity
	}

	def list(String merchantId, String customerId, filters) {
		def list = AccountUserRole.createCriteria().list() {
			projections {
				distinct "user"
			}
			eq "account.id", customerId ? customerId : merchantId
		}
		list.collect { map(it) }
	}

	def create(entity) {
		def user = User.newInstance(
			username : entity.username,
			password : entity.password
		)
		map(user.save(flush : true, failOnError : true))
	}

	def show(String userId) {
		def user = map(User.get(userId))
		user
	}

	def update(entity) {
		def user = User.get(entity.id)
		map(user)
	}

	def delete(String userId) {
		User.load(userId).delete(flush : true)
	}

}
