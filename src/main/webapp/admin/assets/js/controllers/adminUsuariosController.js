adminapp.controller('adminUsuariosController', function($scope, AdminUsuarios) {
    AdminUsuarios.query(function(data) {
        $scope.usuarios = data.items;
        $scope.usuarios.forEach(function(p) {console.log(p)});
    });

    $scope.eliminar = function() {
        console.log("asd");
    };

    $scope.info = function (usuario) {
        $scope.usuarioSeleccionado = usuario;
    }

    $scope.deseleccionar = function() {
        $scope.usuarioSeleccionado = undefined;
    }
});