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
public class StavkaNarudzbenice implements OpstiDomenskiObjekat,Serializable {

    private int sifraNarudzbenice;
    private int redniBroj;
    private int sifraProizvoda;
    private String nazivProizvoda;
    private double cenaProizvoda;
    private int kolicina;

    public int getSifraNarudzbenice() {
        return sifraNarudzbenice;
    }

    public void setSifraNarudzbenice(int sifraNarudzbenice) {
        this.sifraNarudzbenice = sifraNarudzbenice;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public int getSifraProizvoda() {
        return sifraProizvoda;
    }

    public void setSifraProizvoda(int sifraProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
    }

    public double getCenaProizvoda() {
        return cenaProizvoda;
    }

    public void setCenaProizvoda(double cenaProizvoda) {
        this.cenaProizvoda = cenaProizvoda;
    }

    public String vratiImeTabele() {
        return "StavkaNarudzbenice";
    }

    public String vratiVrednostiZaInsert() {
        return sifraNarudzbenice + "," + redniBroj + "," + sifraProizvoda + "," + kolicina;
    }

    public String vratiKoloneZaInsert() {
        return "(sifranarudzbenice,rednibroj, sifraproizvoda, kolicina)";
    }

    public boolean napuniSve(ResultSet rs) {
        try {
            redniBroj = rs.getInt("RedniBroj");
            sifraProizvoda = rs.getInt("Naziv");
            nazivProizvoda = rs.getString("nazivProizvoda");
            cenaProizvoda = rs.getDouble("cenaProizvoda");
            kolicina = rs.getInt("Kolicina");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Proizvod.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public LinkedList vratiSveOvogTipa(ResultSet rs) {
        LinkedList lista = new LinkedList();
        try {
            while (rs.next()) {
                StavkaNarudzbenice sn = new StavkaNarudzbenice();
                sn.setSifraNarudzbenice(rs.getInt("sifranarudzbenice"));
                sn.setRedniBroj(rs.getInt("RedniBroj"));
                sn.setSifraProizvoda(rs.getInt("sifraproizvoda"));
                sn.setKolicina(rs.getInt("Kolicina"));
                lista.add(sn);
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public String vratiAtributPretrazivanja() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String postaviVrednostiAtributaZaUpdate() {
//        return "sifraNarudzbenice = " + sifraProizvoda + ",redniBroj = " + redniBroj
//                + ",sifraProizvoda = '" + sifraProizvoda + "', nazivProizvoda= '" + nazivProizvoda
//                + "', cenaProizvoda=" + cenaProizvoda + ", kolicina = " + kolicina;
        return "sifraNarudzbenice = " + sifraNarudzbenice + ",redniBroj = " + redniBroj
                + ",sifraProizvoda = '" + sifraProizvoda + "', kolicina = " + kolicina;
    }

    public String vratiUslovZaNadjiSlog() {
        return "sifraNarudzbenice = " + sifraNarudzbenice + " and rednibroj = " + redniBroj;
    }

    public boolean napuniSve(ResultSet rs, ResultSet rsStavke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String vratiUslovZaNadjiSlogove() {
        return "sifraNarudzbenice = " + sifraNarudzbenice;
    }

    public String vratiPodatkeKaoString() {
        return sifraNarudzbenice + " " + redniBroj + " " + sifraProizvoda + " " + nazivProizvoda + " " + cenaProizvoda + " " + kolicina;
    }

    public String vratiUslovZaObrisiSlog() {
        //brisem sve stavke a ne samo jednu
        return "sifraNarudzbenice = " + sifraNarudzbenice;
    }
    
	@Override
	public boolean napuniSve(OpstiDomenskiObjekat odoIzBaze) {
		throw new RuntimeException("nije implementirano");
	}
}
