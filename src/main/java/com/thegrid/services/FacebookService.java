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

    public static User getUser(String token) {
        return getFacebookClient(token).fetchObject("me", User.class);
    }

    public static String getName(Usuario usuario) {
        if(usuario.getToken() == null)
            return "Unknown";

        return getUser(usuario.getToken()).getName();
    }

    public static void publishPartido(Partido partido) {
        String publish_message = "Cree el partido: #" + partido.getId() + " para participar vengan a PartidosManager!";

        FacebookClient facebookClient = getFacebookClient(partido.getUsuario().getToken());
        facebookClient.publish("me/feed", FacebookType.class,
                Parameter.with("message", publish_message));
    }

}
