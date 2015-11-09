app.controller('partidoController', function($scope, $location, $routeParams, Partido, Inscripto, Recomendacion, AccionesPartido) {

    $scope.accionesPartido = AccionesPartido;

    Partido.get({ id: $routeParams.id }, function(data) {
        $scope.partido = data;

        Inscripto.query(function(data1) {
            $scope.inscriptos = data1.items;

            var inscriptoEncontrado = data1.items.find( function(inscr){ return $scope.partido.id ==  inscr.partido.id } );

            $scope.partido.inscripcion = inscriptoEncontrado;
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
