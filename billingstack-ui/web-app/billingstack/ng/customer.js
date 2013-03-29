var merchant = angular.module('customer',[])
  .config(["$routeProvider",function($routeProvider){
    $routeProvider
      .when('/',{templateUrl : 'templates/customer', controller : "CustomerCtrl"})
      .when('/payment-methods',{templateUrl : 'templates/customer_payment_methods'})
      .when('/payment-methods/:payment_method',{templateUrl : 'templates/customer_payment_method', controller : "CustomerPaymentMethodCtrl"})
      .when('/subscriptions',{templateUrl : 'templates/customer_subscriptions'})
      .when('/subscriptions/:subscription',{templateUrl : 'templates/customer_subscription', controller : "SubscriptionCtrl"})
      .when('/subscriptions/:subscription/usage',{templateUrl : 'templates/customer_usages'})
      .when('/subscriptions/:subscription/usage/:usage',{templateUrl : 'templates/customer_usage', controller : "CustomerUsageCtrl"})
      .when('/usage',{templateUrl : 'templates/customer_usages'})
      .when('/usage/:usage',{templateUrl : 'templates/customer_usage', controller : "CustomerUsageCtrl"})
      .when('/invoices',{templateUrl : 'templates/customer_invoices'})
      .when('/invoices/:invoice',{templateUrl : 'templates/customer_invoice', controller : "InvoiceCtrl"})
      .when('/transactions',{templateUrl : 'templates/customer_transactions'})
      .when('/transactions/:transaction',{templateUrl : 'templates/customer_transaction', controller : "TransactionCtrl"})
      .otherwise({redirectTo : '/customers'})
  }])

