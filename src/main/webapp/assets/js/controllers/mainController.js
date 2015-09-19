app.controller('mainController', function($scope, $http, Partido) {
    Partido.query(function(data) {
        console.log(JSON.stringify(data));
        $scope.partidos = data.items;
    });
});