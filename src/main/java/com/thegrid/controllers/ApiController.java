package com.thegrid.controllers;


import com.google.api.server.spi.config.Api;
import com.thegrid.Constants;
import com.thegrid.models.Usuario;

import javax.servlet.http.HttpServletRequest;


public class ApiController {

    protected Usuario AuthRequired(HttpServletRequest request) throws Exception {
        if(request.getHeader("x-access-token") != null) {
            throw new Exception("x-access-token is required");
        }
        return new Usuario();
    }
}
