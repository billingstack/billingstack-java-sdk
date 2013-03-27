angular.module('bsd',[])
  .directive('subscriptions', ['$http', function($http) {
    return {
      restrict: 'C',
      //replace: true,
      //templateUrl : 'templates/customer_subscriptions',
      link: function(scope, element, attrs) {
				scope.refresh = function() {
					
				}
      }
    }
  }]);