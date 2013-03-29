merchant.controller('CustomerPaymentMethodCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
  $scope.refresh = function() {
    if($scope.params.payment_method == "0") {
      $scope.item = {
          holder : "LUIS",
          number : "4111111111111111",
          expiration : "05/2013",
          cvv : "873"
      }
			$scope.save = function() {
		    $http.post($scope.config.endpoint+'/customers/'+$scope.config.customer_id+'/payment-methods', $scope.item)
		      .success(function(data) {
		        $location.path('/payment-methods')
		      })
		  }
    } else {
      $scope.searching = true;
      $http.get($scope.config.endpoint+'/customers/'+$scope.config.customer_id+'/payment-methods/'+$scope.params.payment_method).success(function(data) {
      	$scope.item = data;
      	$scope.searching = false;
      })
			$scope.update = function() {
		    $location.path('/payment-methods')
		  }
		  $scope.remove = function() {
		    $location.path('/payment-methods')
		  }
    }
  }
  $scope.refresh()
}])