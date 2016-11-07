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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Proizvod implements OpstiDomenskiObjekat,Serializable{

	private static final long serialVersionUID = 8089912468763886454L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIFRAPROIZVODA_SEKVENCA")
	@SequenceGenerator(name = "SIFRAPROIZVODA_SEKVENCA", sequenceName = "SIFRAPROIZVODA_SEKVENCA", allocationSize = 1, initialValue = 1)
	@Column(name= "SIFRAPROIZVODA")
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

	public boolean napuniSve(OpstiDomenskiObjekat odo) {
		Proizvod proizvod = (Proizvod) odo;
		sifraProizvoda = proizvod.getSifraProizvoda();
		naziv = proizvod.getNaziv();
		dobavljac = proizvod.getDobavljac();
		opis = proizvod.getOpis();
		kolicina = proizvod.getKolicina();
		cena = proizvod.getCena();
		return true;
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
