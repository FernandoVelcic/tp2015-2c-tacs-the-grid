app.service('fbAuth',['$rootScope',  function($rootScope) {

    this.watchAuthenticationStatusChange = function() {

        var _self = this;
        $rootScope.authTemplate = authTemplates[0];
        FB.Event.subscribe('auth.authResponseChange', function(res) {
            if (res.status === 'connected') {
                _self.getUserInfo();
                $rootScope.authTemplate = authTemplates[1];
            }
            else {
                $rootScope.user = {};
                $rootScope.authTemplate = authTemplates[0];
            }
        });
    }

    this.getUserInfo = function() {
        var _self = this;
        FB.api('/me', function(res) {
            $rootScope.$apply(function() {
                $rootScope.user = _self.user = res;
            });
        });

    }
}]);