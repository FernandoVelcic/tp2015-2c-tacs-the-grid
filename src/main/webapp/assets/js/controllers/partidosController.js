app.controller('partidosController', function($scope, $http, $location) {
    $http.get("/_ah/api/partidosmanager/v1/partido/")
        .success(function (response) {$scope.partidos = response.items;});

    $scope.delete = function(index){
        var partido = $scope.partidos[index];
        $http.delete("/_ah/api/partidosmanager/v1/partido/" + partido.id)
            .success(function (response) {$scope.partidos.splice(index, 1);});
    };

    $scope.add = function(){
        $http.post("/_ah/api/partidosmanager/v1/partido/",
            {
                'deporte': $scope.deporte,
                'cant_personas': $scope.cantPersonas,
                'lugar': $scope.lugar
            }).success(function(response){
                    $scope.partidos.push(response);
                    $location.path("/");
            });
    };
});