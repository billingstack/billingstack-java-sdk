merchant.controller('CustomerUsageCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
  $scope.refresh = function() {
    if($scope.params.usage == "0") {
      $scope.item = {
        subscription_id : $scope.params.subscription,
        product_name : "storage",
        provider : 'openstack',
        resource : 'tenant:123',
        volume : 100,
        measure : 'gb'
      }
    } else {
      $scope.searching = true;
      $http.get($scope.config.endpoint+'/usage/'+$scope.params.usage)
        .success(function(data) {
          $scope.item = data;
          $scope.searching = false;
        })
    }
  }
  $scope.save = function() {
    $http.post($scope.config.endpoint+'/usage', [$scope.item])
      .success(function(data) {
        console.log(data);
        //$location.path('/subscriptions')
      })
  }
  $scope.update = function() {
    $location.path('/subscriptions')
  }
  $scope.remove = function() {
    $location.path('/subscriptions')
  }
  $scope.refresh()
}])