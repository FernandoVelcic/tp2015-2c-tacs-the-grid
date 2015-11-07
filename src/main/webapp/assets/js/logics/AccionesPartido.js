app.factory('AccionesPartido', function (Inscripto, PublicacionFB) {
    //aca podrian haber atributos privados

    //Funciones publicas
    return {
        anotarme: function(partido){
            var inscripto = {
                'partido': {
                    'id': partido.id
                }
            };

            Inscripto.save(inscripto, function(response){
                partido.inscripcion = response;
            });

            var publicacionFB = {
               'message': "Me acabo de inscribir al partido de " + partido.deporte +", en "+ partido.lugar
            }

            PublicacionFB.save(publicacionFB, function(response) {
            });
        },
        desanotarme: function(partido){
            Inscripto.delete({ id: partido.inscripcion.id}, function (response) {
                partido.inscripcion = undefined;
            });
        }
    };
});
