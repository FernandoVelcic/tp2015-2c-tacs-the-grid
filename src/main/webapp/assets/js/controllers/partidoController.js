app.controller('partidoController', function($scope, $location, $routeParams, Partido, Inscripto, Recomendacion, AccionesPartido) {

    $scope.accionesPartido = AccionesPartido;

    Partido.get({ id: $routeParams.id }, function(data) {
        $scope.partido = data;
    });

    Inscripto.query(function(data) {
        $scope.inscriptos = data.items;
    });

    $scope.onEliminar = function(partido){
        Partido.delete({ id: partido.id},
            function () {$scope.partidos.splice($scope.partidos.indexOf(partido), 1);});
    };
    $scope.onRecomendar = function(partido){
        var recomendacion = {
            'partido': {
                'id': partido.id
            }
        };
        Recomendacion.save(recomendacion, function() {})
    };
});
