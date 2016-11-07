/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.klasaZaKomunikaciju;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cometoin.domenskeKlase.Narudzbenica;
import com.cometoin.domenskeKlase.Proizvod;
import com.cometoin.domenskeKlase.Racun;
import com.cometoin.domenskeKlase.TransferKlasa;

/**
 * 
 * @author vojkan
 */
public class KlasaZaKomunikacijuKlijent {

	ObjectOutputStream oos;
	ObjectInputStream ois;
	public static boolean povezan = false;

	private KlasaZaKomunikacijuKlijent() {
		povezan = poveziSeSaServerom();
	}

	public boolean jePovezan() {
		return povezan;
	}

	static KlasaZaKomunikacijuKlijent instance;

	public static KlasaZaKomunikacijuKlijent getInstance() {

		if (instance == null) {
			instance = new KlasaZaKomunikacijuKlijent();
		}
		return instance;
	}

	public boolean poveziSeSaServerom() {
		try {
			Socket s = new Socket("localhost", 12345);

			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			return true;
		} catch (UnknownHostException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
		}

		return false;

	}

	public int zapamtiProizvod(Proizvod proizvod) {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.ZAPAMTI_PROIZVOD);
			tk.setO(proizvod);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			return tk.getSignal();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 100;
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 11;
		}
	}

	public int obrisiProizvod(Proizvod proizvod) {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.OBRISI_PROIZVOD);
			tk.setO(proizvod);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			return tk.getSignal();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 100;
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 11;
		}
	}

	public int kreirajNoviProizvod(Proizvod noviProizvod) {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.KREIRAJ_NOVI_PROIZVOD);
			tk.setO(noviProizvod);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			return tk.getSignal();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 100;
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 11;
		}
	}

	public int kreirajNarudzbenicu(Narudzbenica narudzbenica) {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.KREIRAJ_NARUDZBENICU);
			tk.setO(narudzbenica);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			return tk.getSignal();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 100;
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 11;
		}
	}

	public int zapamtiNarudzbenicu(Narudzbenica narudzbenica) {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.ZAPAMTI_NARUDZBENICU);
			tk.setO(narudzbenica);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			return tk.getSignal();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 100;
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 11;
		}
	}

	public List vratiProizvode(Proizvod proizvod) {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.VRATI_PROIZVODE);
			tk.setO(proizvod);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			return tk.getLista();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return new LinkedList();
	}

	public List vratiNarudzbenice(Narudzbenica narudzbenica) {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.VRATI_SVE_NARUDZBENICE);
			tk.setO(narudzbenica);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			return tk.getLista();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return new LinkedList();
	}

	public int obrisiNarudzbenicu(Narudzbenica narudzbenica) {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.OBRISI_NARUDZBENICU);
			tk.setO(narudzbenica);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			return tk.getSignal();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 100;
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 11;
		}
	}

	public List vratiRacune(Racun racun) {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.VRATI_SVE_RACUNE);
			tk.setO(racun);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			return tk.getLista();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return new LinkedList();
	}

	public int kreirajRacun(Racun noviRacun) {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.KREIRAJ_RACUN);
			tk.setO(noviRacun);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			noviRacun.setSifra(((Racun) tk.getO()).getSifra());
			// noviRacun = (Racun)tk.getO();
			return tk.getSignal();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 100;
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 11;
		}
	}

	public int zapamtiRacun(Racun racun) {
		try {
			Racun racunZP = new Racun();
			racunZP.setSifra(racun.getSifra());
			racunZP.setDatum(racun.getDatum());
			racunZP.setNazivPartnera(racun.getNazivPartnera());
			racunZP.setObradjen(racun.isObradjen());
			racunZP.setUkupnaVrednost(racun.getUkupnaVrednost());
			racunZP.setStavkeRacuna(racun.getStavkeRacuna());

			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.ZAPAMTI_RACUN);
			tk.setO(racunZP);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			return tk.getSignal();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 100;
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 11;
		}
	}

	public int obrisiRacun(Racun racun) {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.OBRISI_RACUN);
			tk.setO(racun);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			return tk.getSignal();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 100;
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 11;
		}
	}

	public int obradiRacun(Racun racun) {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.OBRADI_RACUN);
			tk.setO(racun);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			return tk.getSignal();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 100;
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 11;
		}
	}

	public static void main(String[] args) {
		KlasaZaKomunikacijuKlijent k = new KlasaZaKomunikacijuKlijent();
		System.out.println("povezivanje: " + k.poveziSeSaServerom());
	}

	public List vratiPojedineProizvode(Proizvod proizvod) {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.VRATI_POJEDINE_PROIZVODE);
			tk.setO(proizvod);
			oos.writeObject(tk);
			tk = (TransferKlasa) ois.readObject();
			return tk.getLista();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return new LinkedList();
	}

	public int raskaciSeSaServera() {
		try {
			TransferKlasa tk = new TransferKlasa();
			tk.setOperacija(TransferKlasa.KRAJ_RADA);
			oos.writeObject(tk);
			return 0;
		} catch (IOException ex) {
			System.out.println("Neuspesno povezivanje sa serverom! ");
			Logger.getLogger(KlasaZaKomunikacijuKlijent.class.getName()).log(
					Level.SEVERE, null, ex);
			return 11;
		}
	}
}
