var merchant = angular.module('merchant',[])
  .config(["$routeProvider",function($routeProvider){
    $routeProvider
      .when('/products',{templateUrl : 'templates/products'})
      .when('/products/:product',{templateUrl : 'templates/product', controller : "ProductCtrl"})
      .when('/plans',{templateUrl : 'templates/plans'})
      .when('/plans/create',{templateUrl : 'templates/plan_create', controller : "PlanCtrl"})
      .when('/plans/:plan',{templateUrl : 'templates/plan', controller : "PlanCtrl"})
      .when('/subscriptions',{templateUrl : 'templates/subscriptions'})
      .when('/subscriptions/:subscription',{templateUrl : 'templates/subscription', controller : "SubscriptionCtrl"})
      .when('/invoices',{templateUrl : 'templates/invoices'})
      .when('/invoices/:invoice',{templateUrl : 'templates/invoice', controller : "InvoiceCtrl"})
      .when('/transactions',{templateUrl : 'templates/transactions'})
      .when('/transactions/:transaction',{templateUrl : 'templates/transaction', controller : "TransactionCtrl"})
      .when('/customers',{templateUrl : 'templates/customers'})
      .when('/customers/0',{templateUrl : 'templates/customer_create', controller : "CustomerCtrl"})
      .otherwise({redirectTo : '/customers'})
  }])
  .controller('BillingStackCtrl', ['$scope','$routeParams','$location','config', function($scope,$routeParams,$location,config) {
    $scope.params = $routeParams;
    $scope.config = config;
  }])
  .controller('ProductCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
    $scope.refresh = function() {
      if($scope.params.product == "0") {
        $scope.item = {
          name : "storage",
          title : "Storage",
          description : "Storage (GB)",
					properties : {}
        }
      } else {
        $scope.searching = true;
        $http.get($scope.config.endpoint+'/products/'+$scope.params.product)
          .success(function(data) {
            $scope.item = data;
            $scope.searching = false;
          })
      }
    }
    $scope.save = function() {
      $http.post($scope.config.endpoint+'/products', $scope.item)
        .success(function(data) {
          $location.path('/products')
        })
    }
    $scope.update = function() {
      $location.path('/products')
    }
    $scope.remove = function() {
      $location.path('/products')
    }
    $scope.refresh()
  }])
  .controller('PlanCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
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
      } else {
        $scope.searching = true;
        $http.get($scope.config.endpoint+'/products')
          .success(function(data) {
            $scope.products = data;
            $scope.searching = false;
          })
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
        
      }
    }
    $scope.save = function() {
      if(!$scope.item.id) {
        $http.post($scope.config.endpoint+'/plans', $scope.item)
          .success(function(data) {
            $location.path('/plans')
          })
      } else {
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
    }
    $scope.remove = function() {
      $location.path('/plans')
    }
    $scope.refresh()
  }])
  .controller('SubscriptionCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
    $scope.refresh = function() {
      if($scope.params.subscription == "0") {
        $scope.item = {
          name : "subscription.0",
          title : "Subscription 0",
          description : "My first Subscription",
        }
      } else {
        $scope.searching = true;
        $http.get($scope.config.endpoint+'/subscriptions/'+$scope.params.subscription)
          .success(function(data) {
            $scope.item = data;
            $scope.searching = false;
          })
      }
    }
    $scope.save = function() {
      $http.post($scope.config.endpoint+'/subscriptions', $scope.item)
        .success(function(data) {
          $location.path('/subscriptions')
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
  .controller('InvoiceCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
    $scope.refresh = function() {
      if($scope.params.invoice == "0") {
        $scope.item = {
          name : "invoice.0",
          title : "Invoice 0",
          description : "My first Invoice",
        }
      } else {
        $scope.searching = true;
        $http.get($scope.config.endpoint+'/invoices/'+$scope.params.invoice)
          .success(function(data) {
            $scope.item = data;
            $scope.searching = false;
          })
      }
    }
    $scope.save = function() {
      $http.post($scope.config.endpoint+'/invoices', $scope.item)
        .success(function(data) {
          $location.path('/invoices')
        })
    }
    $scope.update = function() {
      $location.path('/invoices')
    }
    $scope.remove = function() {
      $location.path('/invoices')
    }
    $scope.refresh()
  }])
  .controller('TransactionCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
    $scope.refresh = function() {
      if($scope.params.transaction == "0") {
        $scope.item = {
          name : "transaction.0",
          title : "Transaction 0",
          description : "My first Transaction",
        }
      } else {
        $scope.searching = true;
        $http.get($scope.config.endpoint+'/transactions/'+$scope.params.transaction)
          .success(function(data) {
            $scope.item = data;
            $scope.searching = false;
          })
      }
    }
    $scope.save = function() {
      $http.post($scope.config.endpoint+'/transactions', $scope.item)
        .success(function(data) {
          $location.path('/transactions')
        })
    }
    $scope.update = function() {
      $location.path('/transactions')
    }
    $scope.remove = function() {
      $location.path('/transactions')
    }
    $scope.refresh()
  }])
  .controller('CustomerCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
    $scope.refresh = function() {
      if(!$scope.params.customer) {
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
        $http.get($scope.config.endpoint+'/customers/'+$scope.params.customer)
          .success(function(data) {
            $scope.item = data;
            $scope.searching = false;
          })
        $scope.save = function() {
          $http.put($scope.config.endpoint+'/customers/'+$scope.item.id, $scope.item)
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
      }
    }
    
    $scope.update = function() {
      $location.path('/customers')
    }
    $scope.refresh()
  }])