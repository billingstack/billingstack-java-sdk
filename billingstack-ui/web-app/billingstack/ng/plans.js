merchant.controller('PlanCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
  $scope.refresh = function() {
    if(!$scope.params.plan) {
      $scope.item = {
        name : "plan.0",
        title : "Plan 0",
        description : "My first Plan",
        quotas : [
            {label : "gigabytes", key : "gigabytes", value : 1000},
            {label : "metadata_items", key : "gigabytes", value : 128},
            {label : "injected_files", key : "gigabytes", value : 5},
            {label : "security_group_rules", key : "gigabytes", value : 20},
            {label : "volumes", key : "gigabytes", value : 10},
            {label : "instances", key : "gigabytes", value : 10},
            {label : "security_groups", key : "gigabytes", value : 10},
            {label : "cores", key : "cores", value : 20},
            {label : "floating_ips", key : "floating_ips", value : 10},
            {label : "ram", key : "floating_ips", value : 51200},
            {label : "key_pairs", key : "floating_ips", value : 100},
            {label : "injected_file_content_bytes", key : "floating_ips", value : 10240},
            {label : "injected_file_path_bytes", key : "floating_ips", value : 255},
        ]
      }
			
			$scope.save = function() {
				$http.post($scope.config.endpoint+'/plans', $scope.item).success(function(data) {
	          $location.path('/plans')
				})
			}
    } else {

      $http.get($scope.config.endpoint+'/plans/'+$scope.params.plan)
        .success(function(data) {
          $scope.item = data;
          $scope.searching = false;
        })
      
      
      var addProduct = function(product) {
				$http.put($scope.config.endpoint+'/plans/'+$scope.params.plan+'/items/'+product.id, {}).success(function(data) {
					$scope.item.items.push(data)
				})
      }

      var removeProduct = function(product) {
					$http.delete($scope.config.endpoint+'/plans/'+$scope.params.plan+'/items/'+product.id).success(function(data) {
						$scope.item.items = _.filter($scope.item.items, function(el) {
							return el.product_id != product.id 
						});
					})
      } 
      
      $scope.refreshProduct = function(product) {
        if(product.checked) {
          addProduct(product)
        } else {
          removeProduct(product)
        }
      }

      $scope.containsProduct = function(product) {
				if($scope.item) {
					var ids = _.pluck($scope.item.items, 'product_id');
          return _.contains(ids, product.id);
				} else {
					return false;
				}
      }
      
      $scope.addRule = function($event, product, rule) {
        $event.preventDefault();
        product.pricing.push(rule);
      }
      
      $scope.removeRule = function(product, index) {
        product.pricing.splice(index,1)
      }
      
      $scope.addRange = function(rule, range) {
        if(!rule.ranges) {
          rule.ranges = []
        }
        rule.ranges.push({from : range.from, to : range.to, price : range.price})
        range.from = null;
        range.to = null;
        range.price = null;
      }
      
      $scope.removeRange = function(rule, index) {
        rule.ranges.splice(index,1)
      }
      
			$scope.save = function() {

					console.log($scope.item.items)
					angular.forEach($scope.item.items, function(it) {
						$http({method : 'PATCH', url : $scope.config.endpoint+'/plans/'+$scope.params.plan+'/items/'+it.product_id, data : it}).success(function(data) {
							console.log("PATCH");
							console.log(data);
						});
					});
					/*
		      $http.put($scope.config.endpoint+'/plans/'+$scope.params.plan, $scope.item)
		        .success(function(data) {
		          $location.path('/plans')
		        })
					*/
		  }
		  $scope.remove = function() {
		    $location.path('/plans')
		  }

    }
  }
  $scope.refresh()
}])