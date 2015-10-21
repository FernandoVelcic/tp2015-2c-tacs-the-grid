package com.thegrid.controllers;

import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.thegrid.models.Recomendacion;
import com.thegrid.services.DatastoreService;

import java.util.List;

public class RecomendacionesController extends ApiController {


    public List<Recomendacion> listRecomendaciones() {
        return DatastoreService.getOfy().load().type(Recomendacion.class).list();
    }

    public Recomendacion getRecomendacion(@Named("id") Long id) {
        return DatastoreService.getOfy().load().type(Recomendacion.class).id(id).now();
    }

    @ApiMethod(httpMethod = "delete")
    public void deleteRecomendacion(@Named("id") Long id) {
        getRecomendacion(id).delete();
    }

    @ApiMethod(httpMethod = "post")
    public Recomendacion insertRecomendacion(Recomendacion recomendacion) {
        DatastoreService.getOfy().save().entity(recomendacion).now();
        return recomendacion;
    }

}
