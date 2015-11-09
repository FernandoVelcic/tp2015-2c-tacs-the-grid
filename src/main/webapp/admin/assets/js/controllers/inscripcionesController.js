app.controller('inscripcionesController', function($scope, $http, $location, Inscripto) {
    $scope.inscriptos = [];

    Inscripto.query(function(data) {
        $scope.inscriptos = data.items;
    });

    $scope.desanotarme = function(inscripto){
        Inscripto.delete({ id: inscripto.id},
            function () {$scope.inscriptos.splice($scope.inscriptos.indexOf(inscripto), 1);});
    };
});