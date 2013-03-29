merchant.controller('SubscriptionCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
  $scope.refresh = function() {
    if($scope.params.subscription == "0") {
			$http.get($scope.config.endpoint+'/customers/'+$scope.config.customer_id+'/payment-methods')
        .success(function(data) {
          $scope.payment_methods = data;
      })
      $scope.item = {
        name : "subscription.0",
        title : "Subscription 0",
        description : "My first Subscription",
				plan_id : "",
        customer_id : $scope.config.customer_id
      }
			$scope.save = function() {
		    $http.post($scope.config.endpoint+'/subscriptions', $scope.item)
		      .success(function(data) {
		        $location.path('/subscriptions')
		      })
		  }
    } else {
      $scope.searching = true;
      $http.get($scope.config.endpoint+'/subscriptions/'+$scope.params.subscription).success(function(data) {
         $scope.item = data;
         $scope.searching = false;
      })
		  $scope.update = function() {
		    $location.path('/subscriptions')
		  }
		  $scope.remove = function() {
		    $location.path('/subscriptions')
		  }
    }
  }
  $scope.refresh()
}])