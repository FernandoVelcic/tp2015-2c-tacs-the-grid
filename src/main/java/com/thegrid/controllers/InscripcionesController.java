package com.thegrid.controllers;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.thegrid.Constants;
import com.thegrid.models.Inscripto;
import com.thegrid.services.DatastoreService;

import java.util.List;


public class InscripcionesController extends ApiController {

    public List<Inscripto> listInscripciones() {
        return DatastoreService.getOfy().load().type(Inscripto.class).list();
    }

    public Inscripto getInscripto(@Named("id") Long id) {
        return DatastoreService.getOfy().load().type(Inscripto.class).id(id).now();
    }

    @ApiMethod(httpMethod = "delete")
    public void deleteInscripto(@Named("id") Long id) {
        getInscripto(id).delete();
    }

    @ApiMethod(httpMethod = "post")
    public Inscripto insertInscripto(Inscripto inscripto) {
        DatastoreService.getOfy().save().entity(inscripto).now();
        return inscripto;
    }
}
