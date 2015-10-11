app.controller('friendsPartidosController', function($scope, $http, $location, FriendPartido, Inscripto) {
    FriendPartido.query(function(data) {
        $scope.partidos = data.items;
        alert("Partidos cargados");
        $scope.partidosConBool = cargarPartidosConBool(data.items);   //TODO LINEA CON PROBLEMAS
        //alert("Partidos con Bool cargados");
    });
    Inscripto.query(function(data) {
        alert("Inscripciones cargadas");
        $scope.inscriptos = data.items;
    });

    cargarPartidosConBool = function(partidos){
        alert("Partidos: " + partidos.length + "- Inscriptos: " + $scope.inscriptos.length);
        var listaTemp = new Array();
        var i;
        for (i = 0; i < partidos.length; i++) {
            listaTemp.push(new PartidoConBool(partidos[i], estoyInscriptoEn(partidos[i])));
        }
        return listaTemp;
    }

    estoyInscriptoEn = function(partido) {
        var i;
        for (i = 0; i < $scope.inscriptos.length; i++) {
            //alert("partido" + i);
            if ($scope.inscriptos[i].partido.id == partido.id) {
                return true;
            }
        }
        return false;
    };

    $scope.anotarme = function(partidoConBool){
        var inscripto = {
            'partido': {
                'id': partidoConBool.partido.id
            }
        };

        Inscripto.save(inscripto, function(response){
            $scope.inscriptos.push(response);
            partidoConBool.estoyInscripto = true;
        });
    };

    $scope.desanotarme = function(partidoConBool){
        var inscripto = inscriptoDe(partidoConBool.partido);
        if(inscripto == null) return;

        alert(inscripto.id);

        Inscripto.delete({ id: inscripto.id}, function (response) {
                $scope.inscriptos.splice($scope.inscriptos.indexOf(inscripto), 1);
                partidoConBool.estoyInscripto = false;
            });
    };

    function inscriptoDe(partido) {
        for (var j = 0; j < $scope.inscriptos.length; j++) {
            if ($scope.inscriptos[j].partido.id == partido.id) {
                return $scope.inscriptos[j];
            }
        }
        return null;
    }

    //----------Prototipos-----------

    function PartidoConBool(partido, estoyInscriptoEn) {
        this.partido = partido;
        this.estoyInscripto = estoyInscriptoEn;
    }
});