merchant.controller('BillingStackCtrl', ['$scope','$routeParams','$location','config', function($scope,$routeParams,$location,config) {
  $scope.params = $routeParams;
  $scope.config = config;
}])