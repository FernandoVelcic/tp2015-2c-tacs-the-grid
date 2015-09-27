app.controller('mainController', function($scope, $http, Partido) {
    Partido.query(function(data) {
        $scope.partidos = data.items;
    });
});