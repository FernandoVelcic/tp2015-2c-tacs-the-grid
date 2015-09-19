app.controller('recomendacionesController', function ($scope, $http, Recomendacion) {
    Recomendacion.query(function(data) {
        $scope.recomendaciones = data.items;
    });

    $scope.rechazar = function(recomendacion){
        Recomendacion.delete({ id: recomendacion.id},
            function (response) {$scope.recomendaciones.splice($scope.recomendaciones.indexOf(recomendacion), 1);});
    };

    $scope.aceptar = function(recomendacion){

    };


});
