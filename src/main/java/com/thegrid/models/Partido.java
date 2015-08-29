package com.thegrid.models;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

import java.util.List;

@Entity
public class Partido {
    @Id
    private Long id;

    private String deporte;
    private Integer cant_personas;

    @Load
    private List<Ref<Inscripto>> inscriptos;
}
