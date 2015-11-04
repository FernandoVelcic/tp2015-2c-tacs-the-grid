package com.thegrid.controllers;



import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import com.thegrid.Constants;
import com.thegrid.models.Usuario;
import com.thegrid.services.DatastoreService;

import javax.servlet.http.HttpServletRequest;


public class ApiController {

    protected Usuario AuthRequired(HttpServletRequest request) throws Exception {
        String token = request.getHeader("x-access-token");

        if(token == null) {
            throw new Exception("x-access-token is required");
        }

        FacebookClient facebookClient = new DefaultFacebookClient(token, Constants.FACEBOOK_APP_SECRET, Version.VERSION_2_5);
        User user = facebookClient.fetchObject("me", User.class);

        Usuario usuario = DatastoreService.getOfy().load().type(Usuario.class).filter("facebook_id", user.getId()).first().now();

        if(usuario == null) {
            usuario = new Usuario();
            usuario.setFacebook_id(user.getId());
        }

        usuario.setToken(token);

        DatastoreService.getOfy().save().entity(usuario).now();

        return usuario;
    }
}
