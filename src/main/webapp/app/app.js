
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
        .when('/partidos', {
            templateUrl: 'views/partidos.html',
            controller: 'partidosController'
        });

    $locationProvider.html5Mode(true);
});