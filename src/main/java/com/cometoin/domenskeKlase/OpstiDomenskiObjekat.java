/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.domenskeKlase;

import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public interface OpstiDomenskiObjekat {

    String vratiImeTabele();

    public String vratiVrednostiZaInsert();

    public String vratiKoloneZaInsert();

    public boolean napuniSve(ResultSet rs);

    public boolean napuniSve(ResultSet rs, ResultSet rsStavke);

    public LinkedList vratiSveOvogTipa(ResultSet rs);

    public String vratiAtributPretrazivanja();

    public String postaviVrednostiAtributaZaUpdate();

    public String vratiUslovZaNadjiSlog();

    public String vratiUslovZaObrisiSlog();

    public String vratiUslovZaNadjiSlogove();
}
