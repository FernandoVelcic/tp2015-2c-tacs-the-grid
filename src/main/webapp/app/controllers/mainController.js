var mainController = function($scope, $http) {
    $http.get("/_ah/api/partidosmanager/v1/partido")
        .success(function (response) {$scope.partidos = response.items;});
}