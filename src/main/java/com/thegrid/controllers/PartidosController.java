package com.thegrid.controllers;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiReference;
import com.google.api.server.spi.config.Named;
import com.thegrid.Constants;
import com.thegrid.models.Partido;
import com.thegrid.services.DatastoreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(
        name = "partidosmanager",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
public class PartidosController extends ApiController {

    public List<Partido> listPartidos(HttpServletRequest request) throws Exception {
        //AuthRequired(request);
        return DatastoreService.getOfy().load().type(Partido.class).list();
    }

    public Partido getPartido(@Named("id") Long id) {
        return DatastoreService.getOfy().load().type(Partido.class).id(id).now();
    }

    @ApiMethod(httpMethod = "delete")
    public void deletePartido(@Named("id") Long id) {
        getPartido(id).delete();
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
