
var app = angular.module('app', ['ngRoute', 'ngResource']);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider

        .when('/', {
            templateUrl: 'views/home.html',
            controller: 'mainController'
        })
        .when('/recomendaciones', {
            templateUrl: 'views/recomendaciones.html',
            controller: 'recomendacionesController'
        })
        .when('/user', {
            templateUrl: 'views/user.html',
            controller: 'userController'
        });

    $locationProvider.html5Mode(true);
});

app.controller('userController', userController);

app.controller('mainController', mainController);
app.controller('recomendacionesController', recomendacionesController);