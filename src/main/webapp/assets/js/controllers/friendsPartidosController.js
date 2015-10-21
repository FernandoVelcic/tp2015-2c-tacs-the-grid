app.controller('friendsPartidosController', function($scope, $http, $location, FriendPartido, Inscripto, AccionesPartido) {
    $scope.accionesPartido = AccionesPartido;

    FriendPartido.query(function(data) {
        $scope.partidos = data.items.map(function(partido){
            partido.inscripcion = undefined;
            return partido;
        });

        $scope.partidos.forEach(function(partido){
            partido.cantInscriptos=0;
        });

        Inscripto.query(function(data) {
            data.items.forEach(function(inscripto){
                var partido = $scope.partidos.find(function(p){ return p.id ==  inscripto.partido.id});
                partido.inscripcion = inscripto;
                partido.cantInscriptos++;
            });
        });
    });
});