package com.thegrid.controllers;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.thegrid.Constants;
import com.thegrid.models.Usuario;
import com.thegrid.services.DatastoreService;

import java.util.List;


@Api(
        name = "partidosmanager",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
public class UsuariosController extends ApiController {

    @ApiMethod(path = "admin/usuario")
    public List<Usuario> listUsuarios() {
        return DatastoreService.getOfy().load().type(Usuario.class).list();
    }

    @ApiMethod(path = "admin/usuario/{id}")
    public Usuario getUsuario(@Named("id") Long id) {
        return DatastoreService.getOfy().load().type(Usuario.class).id(id).now();
    }

    @ApiMethod(path = "admin/usuario")
    public void deleteUsuario(@Named("id") Long id) {
        getUsuario(id).delete();
    }

    @ApiMethod(httpMethod = "post", path = "admin/usuario")
    public Usuario insertUsuario(Usuario usuario) {
        DatastoreService.getOfy().save().entity(usuario).now();
        return usuario;
    }
}
