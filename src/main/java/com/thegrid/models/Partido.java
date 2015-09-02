package com.thegrid.models;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

import com.thegrid.services.DatastoreService;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Entity
public class Partido {

    @Getter
    @Id
    private Long id;

    @Getter
    private String deporte;
    @Getter
    private Integer cant_personas;
    @Getter
    private String lugar;

    public Inscripto getInscripto() {
        return inscripto.get();
    }

    private Ref<Inscripto> inscripto;

    //private List<Inscripto> inscriptos = new ArrayList<Inscripto>();
    /*@Load
    private List<Ref<Inscripto>> inscriptos = new ArrayList<Ref<Inscripto>>();
*/
    public Partido() {
        //mock
        this.deporte = "Futbol";
        this.cant_personas = 5;
        this.lugar = "Corrientes 3200";

        Inscripto insctest = new Inscripto();
        DatastoreService.getOfy().save().entity(insctest).now();
        //inscriptos.add(insctest);
        inscripto = Ref.create(insctest);

        //this.inscriptos.add(Ref.create(insctest));
    }
}
