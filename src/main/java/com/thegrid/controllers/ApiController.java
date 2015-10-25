package com.thegrid.controllers;



import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import com.thegrid.Constants;
import com.thegrid.models.Usuario;

import javax.servlet.http.HttpServletRequest;


public class ApiController {

    protected Usuario AuthRequired(HttpServletRequest request) throws Exception {
        if(request.getHeader("x-access-token") == null) {
            throw new Exception("x-access-token is required");
        }

        FacebookClient facebookClient = new DefaultFacebookClient(request.getHeader("x-access-token"), Constants.FACEBOOK_APP_SECRET, Version.VERSION_2_5);
        User user = facebookClient.fetchObject("me", User.class);

        return new Usuario();
    }
}
