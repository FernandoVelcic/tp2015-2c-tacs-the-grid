app.service('fbAuth',['$rootScope', 'TokenHandler','$http',  function($rootScope, tokenHandler, $http) {

    this.getLoginStatus = function(){
        var _self = this;
        FB.getLoginStatus(function (response){_self.setUserInfo(response)});
    }

    this.watchAuthenticationStatusChange = function() {
        var _self = this;
        FB.Event.subscribe('auth.authResponseChange', function (response){_self.setUserInfo(response)});
    }

    this.getUserInfo = function() {
        var _self = this;
        FB.api('/me',
            {
                fields: 'first_name,last_name,name,picture'
            },
            function(res) {
                $rootScope.user = _self.user = res;
                $rootScope.$apply();
            }
        );
    }

    this.setUserInfo = function(response){
        var _self = this;
        if (response.status === 'connected') {
            //Seteo custom header
            $http.defaults.headers.common['x-access-token']= response.authResponse.accessToken;
            window.localStorage['accessToken'] = response.authResponse.accessToken;
            _self.getUserInfo();
            $rootScope.$apply(function() {
                $rootScope.authTemplate = authTemplates[1];
            });
        }
        else {
            $http.defaults.headers.common['x-access-token']= null;
            window.localStorage['accessToken'] = null;
            $rootScope.$apply(function(){
                $rootScope.user = {};
                $rootScope.authTemplate = authTemplates[0];
            });
        }
    }
}]);