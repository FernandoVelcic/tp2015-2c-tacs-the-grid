package com.thegrid.models;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import lombok.Getter;

@Entity
public class Usuario implements IModel {
    @Getter
    @Id
    private Long id;

    @Getter
    private Long facebook_id;

    public Usuario() {
        //mock
        facebook_id = 5L;
    }

    @Override
    public void delete() {

    }
}
