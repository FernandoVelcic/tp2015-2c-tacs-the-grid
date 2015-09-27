package com.thegrid.models;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;
import com.thegrid.services.DatastoreService;
import lombok.Getter;

@Entity
public class Inscripto {
    @Id
    @Getter
    private Long id;

    @Getter
    private String puesto;

    private Ref<Usuario> usuario;
    public Usuario getUsuario() { return usuario.get(); }

    private Ref<Partido> partido;
    public Partido getPartido() { return partido.get(); }
    public void setPartido(Partido partido) { this.partido = Ref.create(partido); }

    public Inscripto(Ref<Partido> partido) {
        //mock
        puesto = "Titular";
        this.partido = partido;
    }

    public Inscripto() {
        //mock
        puesto = "Titular";

        Usuario usertest = new Usuario();
        DatastoreService.getOfy().save().entity(usertest).now();
        usuario = Ref.create(usertest);

        Partido partidotest = new Partido();
        DatastoreService.getOfy().save().entity(partidotest).now();
        partido = Ref.create(partidotest);
    }
}
