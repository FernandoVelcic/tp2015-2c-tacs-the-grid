
var app = angular.module('app', ['ngRoute', 'ngResource']);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider

        .when('/', {
            templateUrl: 'views/home.html',
            controller: 'mainController'
        })
        .when('/app', {
            templateUrl: 'views/home.html',
            controller: 'mainController'
        })
        .when('/app/recomendaciones', {
            templateUrl: 'views/recomendaciones.html',
            controller: 'recomendacionesController'
        })
        .when('/app/partidos', {
            templateUrl: 'views/partidos.html',
            controller: 'partidosController'
        })
        .when('/app/partidos/nuevo', {
            templateUrl: 'views/nuevoPartido.html',
            controller: 'partidosController'
        })
        .when('/error', {
            templateUrl: 'views/error.html',
            controller: 'emptyController'
        })
        .otherwise({
            redirectTo: '/error'
        });

    $locationProvider.html5Mode(true);
});