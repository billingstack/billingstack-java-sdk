angular.module('bsd',[])
  .directive('collection', ['$http', function($http) {
    return {
      restrict: 'C',
      link: function(scope, element, attrs) {
				attrs.$observe('path', function(path) {
					var httpConfig = {
						method : 'GET',
						url : scope.config.endpoint + attrs.path
					}
					if(attrs.filters != undefined) {
						httpConfig.params = scope.$eval(attrs.filters)
					}
					scope.refresh = function() {
						scope.searching = true;
						$http(httpConfig).success(function(data) {
		          scope.items = data;
							scope.searching = false;
		        });
					}
					scope.refresh();
				});
      }
    }
  }])
	.directive('resource', ['$http', function($http) {
    return {
      restrict: 'C',
      link: function(scope, element, attrs) {
				var url = scope.config.endpoint + attrs.path
				scope.show = function() {
					scope.searching = true;
					var httpConfig = {
						method : 'GET',
						url : scope.config.endpoint + attrs.path
					}
					if(attrs.filters != undefined) {
						httpConfig.params = scope.$eval(attrs.filters)
					}
					$http(httpConfig).success(function(data) {
						scope.items = data;
						scope.searching = false;
					});
				}
				scope.remove = function() {
					var httpConfig = {
						method : 'DELETE',
						url : scope.config.endpoint + attrs.path
					}
					if(attrs.filters != undefined) {
						httpConfig.params = scope.$eval(attrs.filters)
					}
					$http(httpConfig).success(function(data) {
						scope.refresh();
					})
				}
				//scope.refresh();
      }
    }
  }])