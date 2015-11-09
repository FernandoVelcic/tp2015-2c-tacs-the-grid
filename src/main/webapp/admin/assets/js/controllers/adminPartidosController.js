adminapp.controller('adminPartidosController', function($scope, AdminPartidos) {
    AdminPartidos.query(function(data) {
        $scope.partidos = data.items;
    });
});