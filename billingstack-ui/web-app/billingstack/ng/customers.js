merchant.controller('CustomerCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
  $scope.refresh = function() {
    if(!$scope.config.customer_id) {
			console.log('x')
      $scope.item = {
        name : "",
        title : "",
        language : "nor",
        currency : "nok"
      }
      $scope.save = function() {
        $http.post($scope.config.endpoint+'/customers', $scope.item)
          .success(function(data) {
            $location.path('/customers')
          })
          .error(function(data) {
            $('.top-right').notify({
                type : 'error',
                message: { text: data.error }
            }).show();
          })
      }
    } else {
      $scope.searching = true;
      $http.get($scope.config.endpoint+'/customers/'+$scope.config.customer_id)
        .success(function(data) {
          $scope.item = data;
          $scope.searching = false;
        })
      $scope.save = function() {
        $http.put($scope.config.endpoint+'/customers/'+$scope.config.customer_id, $scope.item)
          .success(function(data) {
            $location.path('/')
          })
          .error(function(data) {
            $('.top-right').notify({
                type : 'error',
                message: { text: data.error }
            }).show();
          })
      }
			$scope.remove = function() {
        $location.path('/customers')
      }
    }
  }
  $scope.refresh()
}])