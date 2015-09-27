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

    public List<Partido> listPartidos() {
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
        DatastoreService.getOfy().save().entity(partido).now();
        return partido;
    }

    @ApiMethod(path="friends/partidos")
    public List<Partido> listFriendsPartidos() {
        return DatastoreService.getOfy().load().type(Partido.class).list();
    }
}
