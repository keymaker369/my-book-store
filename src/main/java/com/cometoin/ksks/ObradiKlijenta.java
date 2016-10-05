/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cometoin.ksks;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cometoin.domenskeKlase.Narudzbenica;
import com.cometoin.domenskeKlase.Proizvod;
import com.cometoin.domenskeKlase.Racun;
import com.cometoin.domenskeKlase.TransferKlasa;
import com.cometoin.kontrolerAl.KontrolerAL;

/**
 * 
 * @author user
 */
public class ObradiKlijenta extends Thread {
	Socket s;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	public ObradiKlijenta(Socket s) throws IOException {
		this.s = s;
		oos = new ObjectOutputStream(s.getOutputStream());
		ois = new ObjectInputStream(s.getInputStream());
		start();
	}

	@Override
	public void run() {
		int oper = -1;
		while (oper != TransferKlasa.KRAJ_RADA) {
			TransferKlasa tk;
			try {
				tk = (TransferKlasa) ois.readObject();
				oper = tk.getOperacija();
				izvrsiOperaciju(tk);
			} catch (IOException ex) {
				Logger.getLogger(ObradiKlijenta.class.getName()).log(
						Level.SEVERE, null, ex);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}

	private void izvrsiOperaciju(TransferKlasa tk) {

		switch (tk.getOperacija()) {
		case TransferKlasa.KRAJ_RADA:
			System.out.println("Korisnik se raskacio sa servera!");
			break;

		case TransferKlasa.ZAPAMTI_PROIZVOD:
			tk.setSignal(KontrolerAL.getInstance().zapamtiProizvod(
					(Proizvod) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;

		case TransferKlasa.OBRISI_PROIZVOD:
			tk.setSignal(KontrolerAL.getInstance().obrisiProizvod(
					(Proizvod) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;

		case TransferKlasa.KREIRAJ_NOVI_PROIZVOD:
			tk.setSignal(KontrolerAL.getInstance().kreirajNoviProizvod(
					(Proizvod) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;

		case TransferKlasa.KREIRAJ_NARUDZBENICU:
			tk.setSignal(KontrolerAL.getInstance().kreirajNarudzbenicu(
					(Narudzbenica) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;

		case TransferKlasa.ZAPAMTI_NARUDZBENICU:
			tk.setSignal(KontrolerAL.getInstance().zapamtiNarudzbenicu(
					(Narudzbenica) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;

		case TransferKlasa.OBRISI_NARUDZBENICU:
			tk.setSignal(KontrolerAL.getInstance().obrisiNarudzbenicu(
					(Narudzbenica) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;

		case TransferKlasa.KREIRAJ_RACUN:
			tk.setSignal(KontrolerAL.getInstance().kreirajRacun(
					(Racun) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;

		case TransferKlasa.OBRISI_RACUN:
			tk.setSignal(KontrolerAL.getInstance().obrisiRacun(
					(Racun) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;

		case TransferKlasa.ZAPAMTI_RACUN:
			tk.setSignal(KontrolerAL.getInstance().zapamtiRacun(
					(Racun) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;

		case TransferKlasa.OBRADI_RACUN:
			tk.setSignal(KontrolerAL.getInstance().obradiRacun(
					(Racun) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;

		case TransferKlasa.VRATI_PROIZVODE:
			tk.setLista(KontrolerAL.getInstance().vratiProizvode(
					(Proizvod) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;

		case TransferKlasa.VRATI_SVE_NARUDZBENICE:
			tk.setLista(KontrolerAL.getInstance().vratiNarudzbenice(
					(Narudzbenica) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;

		case TransferKlasa.VRATI_SVE_RACUNE:
			tk.setLista(KontrolerAL.getInstance()
					.vratiRacune((Racun) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;

		case TransferKlasa.VRATI_POJEDINE_PROIZVODE:
			tk.setLista(KontrolerAL.getInstance().vratiPojedineProizvode(
					(Proizvod) tk.getO()));
			try {
				oos.writeObject(tk);
			} catch (IOException ex) {
				Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			break;
		}

	}
}
