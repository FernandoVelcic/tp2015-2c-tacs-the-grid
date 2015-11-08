app.controller('adminInscripcionesController', function($scope, AdminInscripciones) {
    AdminInscripciones.query(function(data) {
        $scope.inscriptos = data.items;
    });
});