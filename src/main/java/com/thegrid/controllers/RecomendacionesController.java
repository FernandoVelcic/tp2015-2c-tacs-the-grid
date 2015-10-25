package com.thegrid.controllers;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.thegrid.Constants;
import com.thegrid.models.Recomendacion;
import com.thegrid.services.DatastoreService;

import java.util.List;

@Api(
        name = "partidosmanager",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
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
