app.controller('recomendacionesController', function ($scope, $http) {
    $http.get("/_ah/api/partidosmanager/v1/recomendacion/")
        .success(function (response) {$scope.recomendaciones = response.items;});

    $scope.rechazar = function(recomendacion){
        $http.delete("/_ah/api/partidosmanager/v1/recomendacion/" + recomendacion.id)
            .success(function (response) {$scope.recomendaciones.splice($scope.recomendaciones.indexOf(recomendacion), 1);});
    };

    $scope.aceptar = function(recomendacion){

    };


});
