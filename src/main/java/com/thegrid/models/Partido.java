package com.thegrid.models;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import com.thegrid.services.DatastoreService;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Partido implements IModel {

    @Getter
    @Id
    private Long id;

    @Getter
    private String deporte;
    @Getter
    private Integer cant_personas;
    @Getter
    private String lugar;
    @Getter
    private Integer totalParticipantes;

    private Ref<Usuario> usuario;
    public Usuario getUsuario() {
        return usuario.get();
    }

    public Partido() {
        //mock
        this.deporte = "Futbol";
        this.cant_personas = 5;
        this.lugar = "Corrientes 3200";

        Usuario usertest = new Usuario();
        DatastoreService.getOfy().save().entity(usertest).now();
        usuario = Ref.create(usertest);
    }

    @Override
    public void delete() {
        DatastoreService.getOfy().delete().keys(DatastoreService.getOfy().load().type(Inscripto.class).filter("partido", this).keys());
        DatastoreService.getOfy().delete().keys(DatastoreService.getOfy().load().type(Recomendacion.class).filter("partido", this).keys());
        DatastoreService.getOfy().delete().entity(this);
    }

    public Integer getTotalInscriptos() {
        return DatastoreService.getOfy().load().type(Inscripto.class).filter("partido", this).count();
    }


    public Integer getCant_personas() {
        return cant_personas;
    }

    public Integer getTotalParticipantes() {
        return totalParticipantes;
    }

    public void setTotalParticipantes(Integer totalParticipantes) {
        this.totalParticipantes = totalParticipantes;
    }
}
