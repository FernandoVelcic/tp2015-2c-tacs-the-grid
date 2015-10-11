app.controller('partidosController', function($scope, $http, $location, Partido, Recomendacion, Inscripto) {
    Partido.query(function(data) {
        $scope.partidos = data.items;
    });


    $("[name='switchcheckbox']").bootstrapSwitch();

    $scope.deletePartido = function(partido){
        Partido.delete({ id: partido.id},
                    function () {$scope.partidos.splice($scope.partidos.indexOf(partido), 1);});
    };

    $scope.recomendar = function(partido){
        var recomendacion = {
            'partido': {
                'id': partido.id
            }
        };
        Recomendacion.save(recomendacion, function() {})
    };

    $scope.add = function(){
        Partido.save({
                        'deporte': $scope.deporte,
                        'cant_personas': $scope.cantPersonas,
                        'lugar': $scope.address
                    },
                    function(response){
                        $scope.partidos.push(response);
                            $location.path("/")
                    });
    };

    $scope.newPartido = function(){
                $location.path("/app/partidos/nuevo");
    };

    $scope.placeChanged = function(place) {
        $scope.types = "['address']";
        $scope.place = this.getPlace();
        $scope.map.setCenter($scope.place.geometry.location);
    }
});