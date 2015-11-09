adminapp.controller('homeController', function($scope, $rootScope, $http) {
    $scope.tengoHeader = function() {
        return $http.defaults.headers.common['x-access-token'];
    };

    $scope.mostrarMensajeNoLoggeo = function() {
      return $rootScope.loggedUser != undefined && !$rootScope.loggedUser;
    };
});