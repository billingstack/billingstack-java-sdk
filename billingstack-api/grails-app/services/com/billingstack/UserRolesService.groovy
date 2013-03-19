package com.billingstack

class UserRolesService {
	
	def usersService
	
	def map(userRole) {
		[
			user : userRole.user.id,
			role : userRole.role.id
		]
	}

	def create(String userId, String roleId) {
		def userRole = UserRole.newInstance(
			user : User.load(userId),
			role : Role.load(roleId)
		)
		map(userRole.save(flush : true, failOnError : true))
	}

	def delete(String userId, String roleId) {
		def userRole = UserAccountRole.findByUserAndRole(
			user : User.load(userId),
			role : Role.load(roleId)
		).delete(flush : true)
	}

}
