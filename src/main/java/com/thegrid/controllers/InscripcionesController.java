package com.thegrid.controllers;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.thegrid.Constants;
import com.thegrid.models.Inscripto;
import com.thegrid.services.DatastoreService;

import java.util.List;

@Api(
        name = "partidosmanager",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
public class InscripcionesController {

    public List<Inscripto> listInscripciones() {
        return DatastoreService.getOfy().load().type(Inscripto.class).list();
    }

    public Inscripto getInscripto(@Named("id") Long id) {
        return DatastoreService.getOfy().load().type(Inscripto.class).id(id).now();
    }

    @ApiMethod(httpMethod = "delete")
    public void deleteInscripto(@Named("id") Long id) {
        DatastoreService.getOfy().delete().type(Inscripto.class).id(id);
    }

    @ApiMethod(httpMethod = "post")
    public Inscripto insertInscripto(Inscripto inscripto) {
        DatastoreService.getOfy().save().entity(inscripto).now();
        return inscripto;
    }
}