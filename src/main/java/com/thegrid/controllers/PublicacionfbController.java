package com.thegrid.controllers;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import com.thegrid.Constants;
import com.thegrid.controllers.ApiController;
import com.thegrid.models.PublicacionFB;

import javax.servlet.http.HttpServletRequest;

@Api(
        name = "partidosmanager",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
public class PublicacionFBController extends ApiController {

    @ApiMethod(httpMethod = "post")
    public PublicacionFB insertPublicacionFB(PublicacionFB publicacion, HttpServletRequest request) throws Exception {

        String xAT = request.getHeader("x-access-token");

        if(xAT == null) {
            throw new Exception("x-access-token is required");
        }
        System.out.println(xAT);

        FacebookClient facebookClient = new DefaultFacebookClient(xAT, Constants.FACEBOOK_APP_SECRET, Version.VERSION_2_5);

        FacebookType publishMessageResponse =
                facebookClient.publish("me/feed", FacebookType.class,
                        Parameter.with("message", publicacion.getMessage()));

        System.out.println("Published message ID: " + publishMessageResponse.getId());

        PublicacionFB publicacionFB = new PublicacionFB();
        publicacionFB.setMessage("Soy una devolucion");
        return publicacionFB;
    }
}