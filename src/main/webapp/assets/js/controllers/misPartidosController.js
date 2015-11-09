app.controller('misPartidosController', function($scope,$location, Partido, Inscripto, Recomendacion, AccionesPartido) {

    $scope.partidos = [];

    $scope.accionesPartido = AccionesPartido;
    Partido.query(function(data) {
        $scope.partidos = data.items.map(function(partido){
            if(!partido)
                partido.inscripcion = undefined;
            console.log("Pase por la query de Partidos");
            return partido;
        });

        Inscripto.query(function(data) {
            data.items.forEach(function(inscripto){
                var partido = $scope.partidos.find(function(p){ return p.id ==  inscripto.partido.id});
                if(!partido)
                    partido.inscripcion = inscripto;
            });
            console.log("Pase por la query de Inscriptos");
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
