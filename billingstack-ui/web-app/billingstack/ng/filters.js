angular.module('bsf',[])
	.filter('checked', function () {
    return function (array) {
      if(array) {
        return array.filter(function(el) {
          return el.checked
        })
      } else {
        return []
      }
    };
  })