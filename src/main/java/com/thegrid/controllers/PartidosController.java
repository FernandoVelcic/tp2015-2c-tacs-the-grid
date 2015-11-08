package com.thegrid.controllers;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiReference;
import com.google.api.server.spi.config.Named;
import com.thegrid.Constants;
import com.thegrid.models.Partido;
import com.thegrid.models.Usuario;
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
        Usuario usuario = AuthRequired(request);
        return DatastoreService.getOfy().load().type(Partido.class).list();
    }

    public Partido getPartido(@Named("id") Long id, HttpServletRequest request) throws Exception {
        Usuario usuario = AuthRequired(request);
        return DatastoreService.getOfy().load().type(Partido.class).id(id).now();
    }

    @ApiMethod(httpMethod = "delete")
    public void deletePartido(@Named("id") Long id, HttpServletRequest request) throws Exception {
        Usuario usuario = AuthRequired(request);
        getPartido(id, request).delete();
    }

    @ApiMethod(httpMethod = "post")
    public Partido insertPartido(Partido partido, HttpServletRequest request) throws Exception {
        Usuario usuario = AuthRequired(request);
        partido.setUsuario(usuario);
        DatastoreService.getOfy().save().entity(partido).now();
        partido.publish();
        return partido;
    }

    @ApiMethod(path="friends/partido")
    public List<Partido> listFriendsPartidos(HttpServletRequest request) throws Exception {
        Usuario usuario = AuthRequired(request);
        return DatastoreService.getOfy().load().type(Partido.class).list();
    }

    //Administracion

    @ApiMethod(path="admin/partido")
    public List<Partido> listPartidosAdmin(HttpServletRequest request) throws Exception {

        //TODO: Segurizacion
        Usuario usuario = AuthRequired(request);
        return DatastoreService.getOfy().load().type(Partido.class).list();
    }

    @ApiMethod(path="admin/partido/{id}")
    public Partido getPartidoAdmin(@Named("id") Long id, HttpServletRequest request) throws Exception {

        //TODO: Segurizacion
        Usuario usuario = AuthRequired(request);
        return DatastoreService.getOfy().load().type(Partido.class).id(id).now();
    }

    @ApiMethod(httpMethod = "delete", path="admin/partido")
    public void deletePartidoAdmin(@Named("id") Long id, HttpServletRequest request) throws Exception {
        //TODO: Segurizacion
        Usuario usuario = AuthRequired(request);
        getPartido(id, request).delete();
    }

    @ApiMethod(httpMethod = "post", path="admin/partido")
    public Partido insertPartidoAdmin(Partido partido, HttpServletRequest request) throws Exception {
        //TODO: Segurizacion
        Usuario usuario = AuthRequired(request);
        partido.setUsuario(usuario);
        DatastoreService.getOfy().save().entity(partido).now();
        partido.publish();
        return partido;
    }
}
