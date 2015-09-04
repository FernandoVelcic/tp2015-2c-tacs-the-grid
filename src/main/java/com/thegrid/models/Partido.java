package com.thegrid.models;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import com.thegrid.services.DatastoreService;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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

    private Ref<Usuario> usuario;
    public Usuario getUsuario() {
        return usuario.get();
    }

    //private List<Ref<Inscripto>> inscriptos = new ArrayList<Ref<Inscripto>>();

    public Partido() {
        //mock
        this.deporte = "Futbol";
        this.cant_personas = 5;
        this.lugar = "Corrientes 3200";

        Usuario usertest = new Usuario();
        DatastoreService.getOfy().save().entity(usertest).now();
        usuario = Ref.create(usertest);

        //this.inscriptos.add(Ref.create(insctest));
    }

}
