package com.thegrid.models;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.thegrid.services.DatastoreService;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Usuario implements IModel {
    @Getter
    @Id
    private Long id;

    @Getter
    @Setter
    @Index
    private String facebook_id;

    @Getter
    @Setter
    private String token;

    public Usuario() {
        //mock
        facebook_id = "5";
    }

    @Override
    public void delete() {
        DatastoreService.getOfy().delete().entity(this);
    }
}
