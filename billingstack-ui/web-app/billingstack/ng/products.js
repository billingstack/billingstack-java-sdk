merchant.controller('ProductCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
  $scope.refresh = function() {
    if($scope.params.product == "0") {
      $scope.item = {
        name : "storage",
        title : "Storage",
        description : "Storage (GB)",
				properties : {}
      }
			$scope.save = function() {
	      $http.post($scope.config.endpoint+'/products', $scope.item).success(function(data) {
	          $location.path('/products')
				})
	    }
    } else {
      $scope.searching = true;
      $http.get($scope.config.endpoint+'/products/'+$scope.params.product).success(function(data) {
          $scope.item = data;
          $scope.searching = false;
      })
			$scope.save = function() {
				$http.put($scope.config.endpoint+'/products/'+$scope.item.id, $scope.item).success(function(data) {
	          $location.path('/products')
				})
	    }
	    $scope.remove = function() {
	      $http.delete($scope.config.endpoint+'/products/'+$scope.item.id).success(function(data) {
	          $location.path('/products')
				})
	    }
    }
  }
  $scope.refresh()
}])