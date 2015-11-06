package com.thegrid.controllers;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import com.thegrid.Constants;

import javax.servlet.http.HttpServletRequest;

public class Publicadorfb {
    public static void publicarBiografia(HttpServletRequest request) throws Exception {

        String xAT = request.getHeader("x-access-token");

        if(xAT == null) {
            throw new Exception("x-access-token is required");
        }

        FacebookClient facebookClient = new DefaultFacebookClient(xAT, Constants.FACEBOOK_APP_SECRET, Version.VERSION_2_5);

        FacebookType publishMessageResponse =
                facebookClient.publish("me/feed", FacebookType.class,
                        Parameter.with("message", "RestFB test"));

        System.out.println("Published message ID: " + publishMessageResponse.getId());
    }
}