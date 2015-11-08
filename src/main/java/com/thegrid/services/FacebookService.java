package com.thegrid.services;


import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import com.thegrid.Constants;
import com.thegrid.models.Partido;
import com.thegrid.models.Usuario;

public class FacebookService {
    public static FacebookClient getFacebookClient(String token) {
        return new DefaultFacebookClient(token, Constants.FACEBOOK_APP_SECRET, Version.VERSION_2_5);
    }

    public static String getName(Usuario usuario) {
        if(usuario.getToken() == null)
            return "Unknown";

        FacebookClient facebookClient = getFacebookClient(usuario.getToken());
        User user = facebookClient.fetchObject("me", User.class);

        return user.getName();
    }

    public static void publishPartido(Partido partido) {
        String publish_message = "Partido: " + partido.getId();

        FacebookClient facebookClient = getFacebookClient(partido.getUsuario().getToken());
        facebookClient.publish("me/feed", FacebookType.class,
                Parameter.with("message", publish_message));
    }

}
