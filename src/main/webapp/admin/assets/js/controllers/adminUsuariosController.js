adminapp.controller('adminUsuariosController', function($scope, AdminUsuarios) {
    AdminUsuarios.query(function(data) {
        $scope.usuarios = data.items;
        $scope.usuarios.forEach(function(p) {console.log(p)});
    });

    $scope.eliminar = function(usuario) {
        AdminUsuarios.delete({ id: usuario.id}, function(response) {
            $scope.deseleccionar();
            $scope.usuarios.splice($scope.usuarios.indexOf(usuario), 1);
            console.log("Usuario eliminado! ID: " + usuario.id);
        });
    };

    $scope.info = function (usuario) {
        $scope.usuarioSeleccionado = usuario;
    }

    $scope.deseleccionar = function() {
        $scope.usuarioSeleccionado = undefined;
    }
});