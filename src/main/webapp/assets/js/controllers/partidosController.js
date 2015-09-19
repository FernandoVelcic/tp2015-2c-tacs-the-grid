app.controller('partidosController', function($scope, $http, $location, Partido) {
    Partido.query(function(data) {
        $scope.partidos = data.items;
    });

    $scope.deletePartido = function(partido){
        Partido.delete({ id: partido.id},
                    function () {$scope.partidos.splice($scope.partidos.indexOf(partido), 1);});
    };

    $scope.add = function(){
        Partido.save({
                        'deporte': $scope.deporte,
                        'cant_personas': $scope.cantPersonas,
                        'lugar': $scope.lugar
                    },
                    function(response){
                        $scope.partidos.push(response);
                        $location.path("/");
                });
    };
});