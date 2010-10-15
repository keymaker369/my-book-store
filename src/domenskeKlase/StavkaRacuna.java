/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domenskeKlase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class StavkaRacuna implements OpstiDomenskiObjekat,Serializable {

    private int sifra;
    private int redniBroj;
    private int sifraProizvoda;
    private int kolicina;
    private String nazivProizvoda;
    private double cenaProizvoda;

    public StavkaRacuna() {
    }

    public StavkaRacuna(int sifra, int redniBroj, int sifraProizvoda, int kolicina) {
        this.sifra = sifra;
        this.redniBroj = redniBroj;
        this.sifraProizvoda = sifraProizvoda;
        this.kolicina = kolicina;
    }

    public double getCenaProizvoda() {
        return cenaProizvoda;
    }

    public void setCenaProizvoda(double cenaProizvoda) {
        this.cenaProizvoda = cenaProizvoda;
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

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String vratiImeTabele() {
        return "StavkaRacuna";
    }

    public String vratiVrednostiZaInsert() {
        return sifra + "," + redniBroj + "," + sifraProizvoda + "," + kolicina;
    }

    public String vratiKoloneZaInsert() {
        return "(sifra,redniBroj, sifraproizvoda, kolicina)";
    }

    public boolean napuniSve(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean napuniSve(ResultSet rs, ResultSet rsStavke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public LinkedList vratiSveOvogTipa(ResultSet rs) {
        LinkedList lista = new LinkedList();
        try {
            while (rs.next()) {
                StavkaRacuna sr = new StavkaRacuna();
                sr.setSifra(rs.getInt("sifra"));
                sr.setRedniBroj(rs.getInt("RedniBroj"));
                sr.setSifraProizvoda(rs.getInt("sifraproizvoda"));
                sr.setKolicina(rs.getInt("Kolicina"));
                lista.add(sr);
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public String vratiAtributPretrazivanja() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String postaviVrednostiAtributaZaUpdate() {
        return "sifra = " + sifra + ",redniBroj = " + redniBroj
                + ",sifraProizvoda = '" + sifraProizvoda + "', kolicina = " + kolicina;
    }

    public String vratiUslovZaNadjiSlog() {
        return "sifra = " + sifra + " and rednibroj = " + redniBroj;
    }

    public String vratiUslovZaObrisiSlog() {
        //brisem sve stavke a ne samo jednu
        return "sifra = " + sifra;
    }

    public String vratiUslovZaNadjiSlogove() {
        //brisem sve stavke a ne samo jednu
        return "sifra = " + sifra;
    }
}