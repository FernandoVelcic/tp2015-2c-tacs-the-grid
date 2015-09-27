app.controller('friendsPartidosController', function($scope, $http, $location, FriendPartido) {
    FriendPartido.query(function(data) {
        $scope.partidos = data.items;
    });

    $scope.anotarme = function(partido){

    };
});