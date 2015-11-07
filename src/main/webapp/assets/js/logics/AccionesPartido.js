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
               'message': "Me inscribo al partido " + partido.id + " ¡yeeeeey!"
            }

            var accessToken;

            FB.login(function(response){
                if (response.authResponse) {
                    accessToken = response.accessToken;
                    alert(response.authResponse.grantedScopes);

                    var objetoHTTP = {
                        method: 'POST',
                        url: '/_ah/api/partidosmanager/v1/publicacionfb',
                        headers: {'Content-Type': 'application/json',
                                  'x-access-token': accessToken},
                        data: publicacionFB
                    }

                    PublicacionFB.save(publicacionFB, function(response) {
                        alert(response);
                    });
                }
                else{
                    alert("No te logeaste correctamente!")
                }
            }, {
                scope: 'publish_actions',
                return_scopes: true
            });
        },
        desanotarme: function(partido){
            Inscripto.delete({ id: partido.inscripcion.id}, function (response) {
                partido.inscripcion = undefined;
            });
        }
    };
});
