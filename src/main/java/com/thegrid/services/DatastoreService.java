package com.thegrid.services;


import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.thegrid.models.Inscripto;
import com.thegrid.models.Partido;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class DatastoreService {
    static {
        ObjectifyService.register(Partido.class);
        ObjectifyService.register(Inscripto.class);
    }

    public static Objectify getOfy() {
        return ofy();
    }


}
