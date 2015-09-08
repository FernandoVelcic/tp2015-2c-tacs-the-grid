package com.thegrid.controllers;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.ObjectifyService;
import com.thegrid.Constants;
import com.thegrid.models.Inscripto;
import com.thegrid.models.Partido;
import com.thegrid.models.Recomendacion;
import com.thegrid.models.Usuario;
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
public class UsuariosController {


    public List<Usuario> listUsuarios() {
        return DatastoreService.getOfy().load().type(Usuario.class).list();
    }

    public Usuario getUsuario(@Named("id") Long id) {
        return DatastoreService.getOfy().load().type(Usuario.class).id(id).now();
    }

    @ApiMethod(httpMethod = "delete")
    public void deleteUsuario(@Named("id") Long id) {
        DatastoreService.getOfy().delete().type(Usuario.class).id(id);
    }

    @ApiMethod(httpMethod = "post")
    public Usuario insertUsuario(Usuario usuario) {
        DatastoreService.getOfy().save().entity(usuario).now();
        return usuario;
    }

}
