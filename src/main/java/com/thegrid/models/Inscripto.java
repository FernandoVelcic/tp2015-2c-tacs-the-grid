package com.thegrid.models;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;
import lombok.Getter;

@Entity
public class Inscripto {
    @Id
    @Getter
    private Long id;

 /*   @Load
    private Ref<Usuario> usuario;
    @Load
    private Ref<Partido> partido;*/
    @Getter
    private String puesto;

    public Inscripto() {
        puesto = "ASD";
    }
}
