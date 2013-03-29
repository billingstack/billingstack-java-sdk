merchant.controller('TransactionCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
  $scope.refresh = function() {
    if($scope.params.transaction == "0") {
      $http.get($scope.config.endpoint+'/invoices?customer_id='+$scope.config.customer_id).success(function(data) {
        $scope.invoices = data;
      })
      $scope.item = {
				customer_id : $scope.config.customer_id,
				invoices : []
      }
      $scope.save = function() {
	      $http.post($scope.config.endpoint+'/transactions', $scope.item).success(function(data) {
	        $location.path('/transactions')
	      })
	    }
    } else {
      $scope.searching = true;
      $http.get($scope.config.endpoint+'/transactions/'+$scope.params.transaction).success(function(data) {
        $scope.item = data;
        $scope.searching = false;
      })
      $scope.update = function() {
        $location.path('/transactions')
      }
      $scope.remove = function() {
        $location.path('/transactions')
      }
    }
  }
  $scope.refresh()
}])