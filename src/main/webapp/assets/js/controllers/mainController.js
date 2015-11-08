app.controller('mainController', function($scope, $rootScope, $http) {
    $scope.tengoHeader = function() {
      return $http.defaults.headers.common['x-access-token'];
    };
});