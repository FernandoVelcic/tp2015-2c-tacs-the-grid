package com.thegrid.controllers;



import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.thegrid.Constants;
import com.thegrid.models.Inscripto;
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
public class InscripcionesController extends ApiController {

    public List<Inscripto> listInscripciones(HttpServletRequest request) throws Exception {
        Usuario usuario = AuthRequired(request);
        return DatastoreService.getOfy().load().type(Inscripto.class).list();
    }

    public Inscripto getInscripto(@Named("id") Long id, HttpServletRequest request) throws Exception {
        Usuario usuario = AuthRequired(request);
        return DatastoreService.getOfy().load().type(Inscripto.class).id(id).now();
    }

    @ApiMethod(httpMethod = "delete")
    public void deleteInscripto(@Named("id") Long id, HttpServletRequest request) throws Exception {
        Usuario usuario = AuthRequired(request);
        getInscripto(id, request).delete();
    }

    @ApiMethod(httpMethod = "post")
    public Inscripto insertInscripto(Inscripto inscripto, HttpServletRequest request) throws Exception {
        Usuario usuario = AuthRequired(request);
        inscripto.setUsuario(usuario);
        inscripto.notifyUser();
        DatastoreService.getOfy().save().entity(inscripto).now();
        return inscripto;
    }

    //Administracion

    @ApiMethod(path="admin/inscripto")
    public List<Inscripto> listInscripcionesAdmin(HttpServletRequest request) throws Exception {

        //TODO: Segurizacion
        Usuario usuario = AuthRequired(request);
        return DatastoreService.getOfy().load().type(Inscripto.class).list();
    }

    @ApiMethod(path="admin/inscripto/{id}")
    public Inscripto getInscriptoAdmin(@Named("id") Long id, HttpServletRequest request) throws Exception {

        //TODO: Segurizacion
        Usuario usuario = AuthRequired(request);
        return DatastoreService.getOfy().load().type(Inscripto.class).id(id).now();
    }

    @ApiMethod(httpMethod = "delete", path="admin/inscripto")
    public void deleteInscriptoAdmin(@Named("id") Long id, HttpServletRequest request) throws Exception {

        //TODO: Segurizacion
        Usuario usuario = AuthRequired(request);
        getInscripto(id, request).delete();
    }

    @ApiMethod(httpMethod = "post", path="admin/inscripto")
    public Inscripto insertInscriptoAdmin(Inscripto inscripto, HttpServletRequest request) throws Exception {

        //TODO: Segurizacion
        Usuario usuario = AuthRequired(request);
        inscripto.setUsuario(usuario);
        DatastoreService.getOfy().save().entity(inscripto).now();
        return inscripto;
    }
}
