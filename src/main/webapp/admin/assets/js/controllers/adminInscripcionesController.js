adminapp.controller('adminInscripcionesController', function($scope, AdminInscripciones) {

    $scope.inscriptoSeleccionado = undefined;

    AdminInscripciones.query(function(data) {
        $scope.inscriptos = data.items;
        $scope.inscriptos.forEach(function(p) {console.log(p)});
    });

    $scope.eliminar = function() {
      console.log("asd");
    };

    $scope.info = function (inscripto) {
        $scope.inscriptoSeleccionado = inscripto;
    }

    $scope.deseleccionar = function() {
        $scope.inscriptoSeleccionado = undefined;
    }
});