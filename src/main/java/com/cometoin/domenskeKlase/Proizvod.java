/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.domenskeKlase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Proizvod implements OpstiDomenskiObjekat,Serializable{

    private int sifraProizvoda;
    private String naziv;
    private String dobavljac;
    private String opis;
    private int kolicina;
    private double cena;

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setDobavljac(String dobavljac) {
        this.dobavljac = dobavljac;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setSifraProizvoda(int sifraProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
    }

    public double getCena() {
        return cena;
    }

    public String getDobavljac() {
        return dobavljac;
    }

    public int getKolicina() {
        return kolicina;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public int getSifraProizvoda() {
        return sifraProizvoda;
    }

    public String vratiImeTabele() {
        return "Proizvod";
    }

    public String vratiVrednostiZaInsert() {
        return "SIFRAPROIZVODA_SEKVENCA.nextval, '" + naziv + "'," + "'" + dobavljac + "'," + "'" + opis + "'," + kolicina + "," + cena;
    }

    public boolean napuniSve(ResultSet rs) {
        try {
            rs.next();
            try {
                sifraProizvoda = rs.getInt("Max");
                return true;
            } catch (SQLException sql) {
            }

            sifraProizvoda = rs.getInt("SifraProizvoda");
            naziv = rs.getString("Naziv");
            dobavljac = rs.getString("Dobavljac");
            opis = rs.getString("Opis");
            kolicina = rs.getInt("Kolicina");
            cena = rs.getDouble("Cena");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Proizvod.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public LinkedList vratiSveOvogTipa(ResultSet rs) {
        LinkedList listaProizvoda = new LinkedList();
        try {

            while (rs.next()) {
                Proizvod p = new Proizvod();
                p.setSifraProizvoda(rs.getInt("SifraProizvoda"));
                p.setNaziv(rs.getString("Naziv"));
                p.setDobavljac(rs.getString("Dobavljac"));
                p.setOpis(rs.getString("Opis"));
                p.setKolicina(rs.getInt("Kolicina"));
                p.setCena(rs.getDouble("Cena"));
                listaProizvoda.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Proizvod.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProizvoda;
    }

    public boolean napuniSve(ResultSet rs, ResultSet rsStavke) {
        return false;
    }

    public String vratiKoloneZaInsert() {
        return "(sifraproizvoda,naziv,dobavljac,opis,kolicina, cena)";
    }

    public String vratiAtributPretrazivanja() {
        return "sifraProizvoda";
    }

    public String postaviVrednostiAtributaZaUpdate() {
        return "Naziv = '" + naziv + "', dobavljac = '" + dobavljac + "', opis = '" + opis + "', kolicina = " + kolicina
                + ", cena = " + cena;
    }

    public String vratiUslovZaNadjiSlog() {
        return "sifraproizvoda = " + sifraProizvoda;
    }

    @Override
    public String toString() {
        return naziv + "";
    }

    public String vratiUslovZaNadjiSlogove() {
        return "naziv LIKE '%" + naziv+"%'";
    }

    public String vratiPodatkeKaoString() {
        return sifraProizvoda + " " + naziv + " " + dobavljac + " " + opis + " " + kolicina + " " + cena;
    }

    public String vratiUslovZaObrisiSlog() {
        return "sifraproizvoda = " + sifraProizvoda;
    }
}
