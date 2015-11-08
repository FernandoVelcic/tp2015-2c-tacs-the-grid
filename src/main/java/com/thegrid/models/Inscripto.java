package com.thegrid.models;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.thegrid.services.DatastoreService;
import lombok.Getter;

@Entity
public class Inscripto implements IModel {
    @Id
    @Getter
    private Long id;

    @Getter
    private String puesto;

    private Ref<Usuario> usuario;
    public Usuario getUsuario() { return usuario.get(); }
    public void setUsuario(Usuario usuario) {
        this.usuario = Ref.create(usuario);
    }

    @Index
    private Ref<Partido> partido;
    public Partido getPartido() { return partido.get(); }
    public void setPartido(Partido partido) {
        this.partido = Ref.create(partido);

        //Cuando se setee el partido actualizar puesto de inscripcion
        puesto = "Titular";
        if (partidoCompleto()){ puesto = "Suplente"; }
    }

    public boolean partidoCompleto() {
        return this.getPartido().getTotalInscriptos() >= this.getPartido().getCant_personas();
    }

    public Inscripto() {

    }


    @Override
    public void delete() {
        DatastoreService.getOfy().delete().entity(this);
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
