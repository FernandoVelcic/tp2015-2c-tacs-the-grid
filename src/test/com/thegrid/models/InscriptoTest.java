package com.thegrid.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class InscriptoTest {

    @Test
    public void testPartidoCompleto() throws Exception {
        Partido unPartido= new Partido();
        unPartido.setTotalParticipantes(1);
        Inscripto unInscripo = new Inscripto();

        assertEquals("partidos anotado","suplente",unInscripo.getPuesto());
    }
}