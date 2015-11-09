app.controller('partidoController', function($scope, $location, $routeParams, Partido, PartidoInscripto, Recomendacion, AccionesPartido) {

    $scope.accionesPartido = AccionesPartido;

    Partido.get({ id: $routeParams.id }, function(data) {
        $scope.partido = data;

        PartidoInscripto.query({ id: $routeParams.id }, function(data1) {
            $scope.inscriptos = data1.items;
        });
    });

    $scope.onEliminar = function(partido){
        Partido.delete({ id: partido.id},
            function () {$location.path("app/partidos");});
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
