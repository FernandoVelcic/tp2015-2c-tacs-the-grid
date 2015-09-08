var userController = function($scope, $http) {
    $http.get("/_ah/api/partidosmanager/v1/partido/")
        .success(function (response) {$scope.partidos = response.items;});

    $scope.delete = function(index){
        var partido = $scope.partidos[index];
        console.log(partido);
        $http.delete("/_ah/api/partidosmanager/v1/partido/", {'id': partido.id})
            .success(function (response) {$scope.partidos.splice(index, 1);});
    }

    $scope.add = function(){
        $http.post("/_ah/api/partidosmanager/v1/partido/",
            {
                'deporte': $scope.deporte,
                'cant_personas': $scope.cantPersonas,
                'lugar': $scope.lugar
            }).success(function(response){
                    $scope.partidos.push(response);
                });
    }
}

