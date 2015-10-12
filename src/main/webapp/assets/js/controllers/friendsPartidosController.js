app.controller('friendsPartidosController', function($scope, $http, $location, FriendPartido, Inscripto) {
    FriendPartido.query(function(data) {
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

    $scope.anotarme = function(partido){
        var inscripto = {
            'partido': {
                'id': partido.id
            }
        };

        Inscripto.save(inscripto, function(response){
            partido.inscripcion = response;
        });
    };

    $scope.desanotarme = function(partido){
        Inscripto.delete({ id: partido.inscripcion.id}, function (response) {
            partido.inscripcion = undefined;
        });
    };
});