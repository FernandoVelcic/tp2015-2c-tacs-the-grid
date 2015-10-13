app.controller('misPartidosController', function($scope, $location, Partido, Inscripto, Recomendacion, AccionesPartido) {

    $scope.accionesPartido = AccionesPartido;

    Partido.query(function(data) {
        $scope.partidos = data.items.map(function(partido){
            partido.inscripcion = undefined;
            return partido;
        });

        Inscripto.query(function(data) {
            data.items.forEach(function(inscripto){
                var partido = $scope.partidos.find(function(p){ return p.id ==  inscripto.partido.id});
                partido.inscripcion = inscripto;
            });
        });
    });

    $scope.onNuevoPartido = function(){
        $location.path("/app/partidos/nuevo");
    };

    $scope.onInfo = function(partido){
        $location.path("/app/partidos/" + partido.id);
    };

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
    }
});