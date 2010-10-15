/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domenskeKlase;

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
public class Narudzbenica implements OpstiDomenskiObjekat,Serializable {

	private int sifraNarudzbenice;
	private String sifraNarudzbeniceKup;
	private String datum;
	private double ukupnaVrednost;
	private String kupac;
	private LinkedList stavkeNarudzbenice;

	public void setSifraNarudzbenice(int sifraNarudzbenice) {
		this.sifraNarudzbenice = sifraNarudzbenice;
	}

	public String getSifraNarudzbeniceKup() {
		return sifraNarudzbeniceKup;
	}

	public void setSifraNarudzbeniceKup(String sifraNarudzbeniceKup) {
		this.sifraNarudzbeniceKup = sifraNarudzbeniceKup;
	}

	public LinkedList getStavkeNarudzbenice() {
		return stavkeNarudzbenice;
	}

	public void setStavkeNarudzbenice(LinkedList stavkeNarudzbenice) {
		this.stavkeNarudzbenice = stavkeNarudzbenice;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getKupac() {
		return kupac;
	}

	public void setKupac(String kupac) {
		this.kupac = kupac;
	}

	public int getSifraNarudzbenice() {
		return sifraNarudzbenice;
	}

	public double getUkupnaVrednost() {
		return ukupnaVrednost;
	}

	public void setUkupnaVrednost(double ukupnaVrednost) {
		this.ukupnaVrednost = ukupnaVrednost;
	}

	public String vratiImeTabele() {
		return "Narudzbenica";
	}

	public String vratiVrednostiZaInsert() {
		return "'" + sifraNarudzbenice + "','" + sifraNarudzbeniceKup + "','"
				+ datum + "','" + kupac + "'," + ukupnaVrednost;
	}

	public String vratiKoloneZaInsert() {
		return "(sifraNarudzbenice,sifraNarudzbeniceKup, datum, kupac, ukupnavrednost)";
	}

	public boolean napuniSve(ResultSet podaciONarudzbenici,
			ResultSet stavkeNarudzbenice) {

		try {
			if (podaciONarudzbenici.next() == false) {
				return false;
			} else {
				podaciONarudzbenici.previous();
				sifraNarudzbenice = podaciONarudzbenici
						.getInt("sifraNarudzbenice");
				sifraNarudzbeniceKup = podaciONarudzbenici
						.getString("sifraNarudzbeniceKup");
				datum = podaciONarudzbenici.getString("sifraNarudzbenice");
				kupac = podaciONarudzbenici.getString("kupac");

				while (stavkeNarudzbenice.next()) {
					StavkaNarudzbenice sn = new StavkaNarudzbenice();
					sn.setRedniBroj(stavkeNarudzbenice.getInt("ReadniBroj"));
					sn.setSifraProizvoda(stavkeNarudzbenice
							.getInt("SifraProizvoda"));
					sn.setNazivProizvoda(stavkeNarudzbenice
							.getString("NazivProizvoda"));
					sn.setNazivProizvoda(stavkeNarudzbenice.getString("Naziv"));
					sn.setKolicina(stavkeNarudzbenice.getInt("kolicina"));
					this.stavkeNarudzbenice.add(sn);
				}
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(Narudzbenica.class.getName()).log(Level.SEVERE,
					null, ex);
			return false;
		}
	}

	public boolean napuniSve(ResultSet rs) {
		try {
			try {
				rs.next();
				sifraNarudzbenice = rs.getInt("Max");
				System.out.println(sifraNarudzbenice);
				return true;
			} catch (SQLException sqe) {
			}
			sifraNarudzbenice = rs.getInt("sifraNarudzbenice");
			System.out.println(sifraNarudzbenice);
			sifraNarudzbeniceKup = rs.getString("SifraNarudzbeniceKup");
			datum = rs.getString("datum");
			kupac = rs.getString("kupac");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(Proizvod.class.getName()).log(Level.SEVERE, null,
					ex);
			return false;
		}

	}

	public LinkedList vratiSveOvogTipa(ResultSet rs) {
		LinkedList listaNarudzbenica = new LinkedList();
		try {
			while (rs.next()) {
				Narudzbenica n = new Narudzbenica();
				n.setSifraNarudzbenice(rs.getInt("SifraNarudzbenice"));
				n.setDatum(rs.getString("Datum"));
				n.setSifraNarudzbeniceKup(rs.getString("SifraNarudzbeniceKup"));
				n.setKupac(rs.getString("Kupac"));
				n.setUkupnaVrednost(rs.getDouble("ukupnaVrednost"));
				listaNarudzbenica.add(n);
			}

		} catch (SQLException ex) {
			Logger.getLogger(Proizvod.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return listaNarudzbenica;
	}

	public String vratiAtributPretrazivanja() {
		return "sifraNarudzbenice";
	}

	public String postaviVrednostiAtributaZaUpdate() {
		return "sifraNarudzbeniceKup = '" + sifraNarudzbeniceKup
				+ "', datum = '" + datum + "', ukupnaVrednost  = "
				+ ukupnaVrednost + ", kupac = '" + kupac + "'";
	}

	public String vratiUslovZaNadjiSlog() {
		return "sifraNarudzbenice =" + sifraNarudzbenice;
	}

	public String vratiUslovZaObrisiSlog() {
		return "sifraNarudzbenice =" + sifraNarudzbenice;
	}

	public String vratiUslovZaNadjiSlogove() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public String vratiPodatkeKaoString() {
		return sifraNarudzbenice + " " + sifraNarudzbeniceKup + " " + datum
				+ " " + kupac;
	}
}
