/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.domenskeKlase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;

@Entity
public class Racun implements OpstiDomenskiObjekat,Serializable {

    private int sifra;
    private String datum;
    private double ukupnaVrednost;
    private boolean obradjen;
    private String nazivPartnera;
    private List<StavkaRacuna> stavkeRacuna;

    public Racun() {
    }

    public Racun(int sifra, String datum, double ukupnaVrednost, boolean obradjen, String nazivPartnera) {
        this.sifra = sifra;
        this.datum = datum;
        this.ukupnaVrednost = ukupnaVrednost;
        this.obradjen = obradjen;
        this.nazivPartnera = nazivPartnera;
    }

    public List getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(List stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getNazivPartnera() {
        return nazivPartnera;
    }

    public void setNazivPartnera(String nazivPartnera) {
        this.nazivPartnera = nazivPartnera;
    }

    public boolean isObradjen() {
        return obradjen;
    }

    public void setObradjen(boolean obradjen) {
        this.obradjen = obradjen;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public double getUkupnaVrednost() {
        return ukupnaVrednost;
    }

    public void setUkupnaVrednost(double ukupnaVrednost) {
        this.ukupnaVrednost = ukupnaVrednost;
    }

    public String vratiImeTabele() {
        return "Racun";
    }

    public String vratiVrednostiZaInsert() {
        return "'" + sifra + "','" + datum + "'," + ukupnaVrednost + ",'" + obradjen + "','" + nazivPartnera + "'";
    }

    public String vratiKoloneZaInsert() {
        return "(sifra,datum, ukupnaVrednost, obradjen, nazivPartnera)";
    }

    public boolean napuniSve(ResultSet rs) {
        try {
            try {
                rs.next();
                sifra = rs.getInt("Max");
                System.out.println(sifra);
                return true;
            } catch (SQLException sqe) {
            }
            sifra = rs.getInt("sifra");
            System.out.println(sifra);
            datum = rs.getString("datum");
            ukupnaVrednost = rs.getDouble("ukupnaVrednost");
            obradjen = Boolean.parseBoolean(rs.getString("obradjen"));
            nazivPartnera = rs.getString("nazivPartnera");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Proizvod.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean napuniSve(ResultSet rs, ResultSet rsStavke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public LinkedList vratiSveOvogTipa(ResultSet rs) {
        LinkedList listaRacuna = new LinkedList();
        try {
            while (rs.next()) {
                Racun r = new Racun();
                r.setSifra(rs.getInt("sifra"));
                r.setDatum(rs.getString("Datum"));
                r.setUkupnaVrednost(rs.getDouble("ukupnaVrednost"));
                r.setObradjen(Boolean.parseBoolean(rs.getString("obradjen")));
                //r.setObradjen(rs.getBoolean("obradjen"));
                r.setNazivPartnera(rs.getString("nazivPartnera"));
                listaRacuna.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Proizvod.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaRacuna;
    }

    public String vratiAtributPretrazivanja() {
        return "sifra";
    }

    public String postaviVrednostiAtributaZaUpdate() {
        return "datum = '" + datum + "', ukupnaVrednost  = " + ukupnaVrednost
                + ", obradjen  = '" + obradjen + "', nazivPartnera = '" + nazivPartnera + "'";
    }

    public String vratiUslovZaNadjiSlog() {
        return "sifra =" + sifra;
    }

    public String vratiUslovZaObrisiSlog() {
        return "sifra =" + sifra;
    }

    public String vratiUslovZaNadjiSlogove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String vratiPodatkeKaoString() {
        return sifra + " " + datum + " " + ukupnaVrednost + " " + obradjen + " " + nazivPartnera;
    }
	
    @Override
	public boolean napuniSve(OpstiDomenskiObjekat odoIzBaze) {
		throw new RuntimeException("nije implementirano");
	}
}
