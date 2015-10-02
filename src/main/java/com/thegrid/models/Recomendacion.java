package com.thegrid.models;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;
import com.thegrid.services.DatastoreService;
import lombok.Getter;

@Entity
public class Recomendacion {
    @Getter
    @Id
    private Long id;

    private Ref<Usuario> usuario;
    public Usuario getUsuario() { return usuario.get(); }

    private Ref<Usuario> from_usuario;
    public Usuario getFrom_usuario() { return from_usuario.get(); }

    private Ref<Partido> partido;
    public Partido getPartido() { return partido.get(); }
    public void setPartido(Partido partido) { this.partido = Ref.create(partido); }

    public Recomendacion() {
        //mock
        Usuario usertest = new Usuario();
        DatastoreService.getOfy().save().entity(usertest).now();
        usuario = Ref.create(usertest);

        Usuario usertest2 = new Usuario();
        DatastoreService.getOfy().save().entity(usertest2).now();
        from_usuario = Ref.create(usertest2);
    }
}
