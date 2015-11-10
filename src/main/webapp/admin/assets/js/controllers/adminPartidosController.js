adminapp.controller('adminPartidosController', function($scope, AdminPartidos) {
    AdminPartidos.query(function(data) {
        $scope.partidos = data.items;
        $scope.partidos.forEach(function(p) {console.log(p)});
    });


    $scope.eliminar = function(partido) {
          AdminPartidos.delete({ id: partido.id},
                function () {$location.path("app/partidos");});
    };


    $scope.info = function (partido) {
        $scope.partidoSeleccionado = partido;
    }


    $scope.deseleccionar = function() {
        $scope.partidoSeleccionado = undefined;
    }

});