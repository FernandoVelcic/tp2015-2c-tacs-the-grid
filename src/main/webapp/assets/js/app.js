
var app = angular.module('app', ['ngRoute', 'ngResource', 'ngMap']);

app.run(['$rootScope', '$window', 'fbAuth',
    function($rootScope, $window, fbAuth) {
        $window.fbAsyncInit = function() {
            FB.init({
                appId: '793357694115348',
                status: true,
                cookie: true,
                xfbml: true
            });
            fbAuth.watchAuthenticationStatusChange();
            fbAuth.getLoginStatus();
        };

        (function(d){
            var js,
                id = 'facebook-jssdk',
                ref = d.getElementsByTagName('script')[0];
            if (d.getElementById(id)) {
                return;
            }
            js = d.createElement('script');
            js.id = id;
            js.async = true;
            js.src = "//connect.facebook.net/en_US/all.js";
            ref.parentNode.insertBefore(js, ref);
        }(document));
    }]);

app.config(function($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);

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
        .when('/app/inscripciones', {
            templateUrl: 'views/inscripciones.html',
            controller: 'inscripcionesController'
        })
        .when('/app/partidos', {
            templateUrl: 'views/partidos.html',
            controller: 'misPartidosController'
        })
        .when('/app/partidos/nuevo', {
            templateUrl: 'views/nuevoPartido.html',
            controller: 'nuevoPartidoController'
        })
        .when('/app/partidos/:id', {
            templateUrl: 'views/partido.html',
            controller: 'partidoController'
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