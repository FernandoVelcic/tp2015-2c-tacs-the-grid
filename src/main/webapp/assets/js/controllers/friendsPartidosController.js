app.controller('friendsPartidosController', function($scope, $http, $location, FriendPartido, Inscripto) {
    FriendPartido.query(function(data) {
        $scope.partidos = data.items;
    });

    $scope.anotarme = function(partido){
        var inscripto = {
            'partido': {
                'id': partido.id
            },
        };

        Inscripto.save(inscripto, function(response){
            $location.path("/");
        });
    };
});