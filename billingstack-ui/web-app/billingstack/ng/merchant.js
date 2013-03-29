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