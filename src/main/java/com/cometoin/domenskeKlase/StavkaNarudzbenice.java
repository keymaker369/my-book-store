package com.cometoin.domenskeKlase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@IdClass(StavkaNarudzbenice.StavkaNarudzbenicePK.class)
public class StavkaNarudzbenice implements OpstiDomenskiObjekat,Serializable {

	@Id
    private int sifraNarudzbenice;
	@Id
	private int redniBroj;
    private int sifraProizvoda;
    @Transient
    private String nazivProizvoda;
    @Transient
    private double cenaProizvoda;
    private int kolicina;
    
    @ManyToOne
    @JoinColumn(name = "sifraNarudzbenice", insertable = false, updatable = false)
    private Narudzbenica narudzbenica;

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
	
	public static class StavkaNarudzbenicePK implements Serializable {
		
		private static final long serialVersionUID = 814676763682928214L;
		
		private int sifraNarudzbenice;
	    private int redniBroj;
	    
	    public StavkaNarudzbenicePK(){
	    }
	    
	    public StavkaNarudzbenicePK(int sifraNarudzbenice, int redniBroj) {
			this.sifraNarudzbenice = sifraNarudzbenice;
			this.redniBroj = redniBroj;
		}

		public int getRedniBroj() {
			return redniBroj;
		}
	    
	    public void setRedniBroj(int redniBroj) {
			this.redniBroj = redniBroj;
		}
	    
	    public int getSifraNarudzbenice() {
			return sifraNarudzbenice;
		}
	    
	    public void setSifraNarudzbenice(int sifraNarudzbenice) {
			this.sifraNarudzbenice = sifraNarudzbenice;
		}
	}
}
