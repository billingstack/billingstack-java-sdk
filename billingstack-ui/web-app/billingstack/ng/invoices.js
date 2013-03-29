merchant.controller('InvoiceCtrl', ['$scope','$location','$http',function($scope,$location,$http) {
  $scope.refresh = function() {
    if($scope.params.invoice == "0") {
      $scope.invoice = {
        customer_id : $scope.config.customer_id,
        email : "luis@woorea.es",
        contact_name : "Luis Gervaso",
        number : "45",
        issued : "13/04/1980",
        subtotal : 0.00,
        tax : 0.00,
        total : 0.00,
        lines : []
      }
      $scope.save = function() {
        $http.post($scope.config.endpoint+'/invoices', $scope.invoice).success(function(data) {
            $location.path('/invoices')
        })
      }
    } else {
      $scope.searching = true;
      $http.get($scope.config.endpoint+'/invoices/'+$scope.params.invoice).success(function(data) {
        $scope.invoice = data;
        $scope.searching = false;
      })
      $scope.update = function() {
        $location.path('/invoices')
      }
      $scope.remove = function() {
        $location.path('/invoices')
      }
    }
		var calculate = function() {
      $scope.newLine.description = null;
      $scope.newLine.quantity = null;
      $scope.newLine.price = null;
      $scope.newLine.subtotal = null;
      var subtotal = 0;
      angular.forEach($scope.invoice.lines, function(line) {
        subtotal += line.subtotal;
      });
      $scope.invoice.subtotal = subtotal;
      $scope.invoice.total = subtotal; // - tax;
    }

    $scope.addLine = function(line) {
      if(!$scope.invoice.id) {
        $http.post($scope.config.endpoint+'/invoices', $scope.invoice).success(function(data) {
          $scope.invoice.id = data.id;
          $scope.addLine(line)
        })
      } else {
        $http.post($scope.config.endpoint+'/invoices/'+$scope.invoice.id+'/lines', line).success(function(data) {
          $scope.invoice.lines.push(data)
          calculate();
        })
      }
    }

    $scope.removeLine = function(index) {
      var line = $scope.invoice.lines[index]
      $http.delete($scope.config.endpoint+'/invoices/'+$scope.invoice.id+'/lines/'+line.id).success(function(data) {
        $scope.invoice.lines.splice(index,1)
      })
    }
  }
  $scope.refresh()
}])