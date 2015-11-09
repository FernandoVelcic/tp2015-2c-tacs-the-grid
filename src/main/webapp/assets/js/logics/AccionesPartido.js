app.factory('AccionesPartido', function (Inscripto) {
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

        },
        anotarme: function(partido, lambda){
            var inscripto = {
                'partido': {
                    'id': partido.id
                }

            };

            Inscripto.save(inscripto, lambda);

        },
        desanotarme: function(partido){
            Inscripto.delete({ id: partido.inscripcion.id}, function (response) {
                partido.inscripcion = undefined;
            });
        }
    };
});
