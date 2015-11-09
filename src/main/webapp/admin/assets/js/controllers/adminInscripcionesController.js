adminapp.controller('adminInscripcionesController', function($scope, AdminInscripciones) {
    AdminInscripciones.query(function(data) {
        $scope.inscriptos = data.items;
        $scope.inscriptos.forEach(function(p) {console.log(p)});
    });
});