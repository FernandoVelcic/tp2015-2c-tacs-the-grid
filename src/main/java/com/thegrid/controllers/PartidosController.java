package com.thegrid.controllers;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.thegrid.Constants;
import com.thegrid.models.Partido;
import com.thegrid.services.DatastoreService;

import java.util.ArrayList;
import java.util.List;

@Api(
        name = "partidosmanager",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
public class PartidosController {

    public static ArrayList<Partido> partidos = new ArrayList<Partido>();

    public List<Partido> listPartidos() {
        //partidos.add(new Partido());
        //return partidos;
        DatastoreService.getOfy().save().entity(new Partido()).now();
        return DatastoreService.getOfy().load().type(Partido.class).list();
    }

    public Partido getPartido(@Named("id") Long id) {
        return DatastoreService.getOfy().load().type(Partido.class).id(id).now();
    }

    @ApiMethod(httpMethod = "delete")
    public void deletePartido(@Named("id") Long id) {
        DatastoreService.getOfy().delete().type(Partido.class).id(id);
    }

    @ApiMethod(httpMethod = "post")
    public Partido insertPartido(Partido partido) {
        //Partido partido_new = new Partido();
        DatastoreService.getOfy().save().entity(partido).now();
        return partido;
    }




}
