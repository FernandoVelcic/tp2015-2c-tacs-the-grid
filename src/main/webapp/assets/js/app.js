
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
        .when('/app/friends/partidos', {
            templateUrl: 'views/friends/partidos.html',
            controller: 'friendsPartidosController'
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

app.factory("Partido", function($resource) {
    return $resource("/_ah/api/partidosmanager/v1/partido/:id", null,
        {
            'query': { method:'GET', isArray: false }
        });
});

app.factory("Inscripto", function($resource) {
    return $resource("/_ah/api/partidosmanager/v1/inscripto/:id", null,
        {
            'query': { method:'GET', isArray: false }
        });
});

app.factory("FriendPartido", function($resource) {
    return $resource("/_ah/api/partidosmanager/v1/friends/partidos/:id", null,
        {
            'query': { method:'GET', isArray: false }
        });
});

app.factory("Recomendacion", function($resource) {
    return $resource("/_ah/api/partidosmanager/v1/recomendacion/:id", null,
        {
            'query': { method:'GET', isArray: false }
        });
});