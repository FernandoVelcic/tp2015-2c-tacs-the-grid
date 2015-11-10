adminapp.controller('homeController', function($scope, $location, $route) {
    $scope.goHome = function() {
        alert("asdasd");
        $location.path("/");
        $route.reload();
    };
});