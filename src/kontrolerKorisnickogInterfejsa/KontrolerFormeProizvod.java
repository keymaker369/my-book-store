/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKorisnickogInterfejsa;

import domenskeKlase.Proizvod;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import klasaZaKomunikaciju.KlasaZaKomunikacijuKlijent;
import provere.Provera;

/**
 *
 * @author user
 */
public class KontrolerFormeProizvod {

    int signal;

    public void sacuvajProizvod(JTextField jtfNaziKnjige, JTextField jtfDobavljac, JTextField jtfOpis, JTextField jtfKolicina, JTextField jtfCena, Proizvod izmenjeniProizvod, int mod, JDialog dialog) {
        if (!proveriPolja(jtfNaziKnjige, jtfDobavljac, jtfOpis, jtfKolicina, jtfCena)) {
            return;
        }
        Proizvod noviProizvod = konvertujPodatkeIzKomponentiUObjekat(jtfNaziKnjige, jtfDobavljac, jtfOpis, jtfKolicina, jtfCena);
        noviProizvod.setSifraProizvoda(izmenjeniProizvod.getSifraProizvoda());
        System.out.println(izmenjeniProizvod.toString());
        switch (mod) {
            case 1:
                signal = KlasaZaKomunikacijuKlijent.getInstance().kreirajNoviProizvod(noviProizvod);
                Provera.proveriSignal(jtfCena, signal);
                break;
            case 2:
                signal = KlasaZaKomunikacijuKlijent.getInstance().zapamtiProizvod(noviProizvod);
                Provera.proveriSignal(jtfCena, signal);
                break;
        }
        dialog.dispose();
    }

    private Proizvod konvertujPodatkeIzKomponentiUObjekat(JTextField jtfNaziKnjige, JTextField jtfDobavljac, JTextField jtfOpis, JTextField jtfKolicina, JTextField jtfCena) {
        Proizvod proizvod = new Proizvod();
        proizvod.setNaziv(jtfNaziKnjige.getText());
        proizvod.setDobavljac(jtfDobavljac.getText());
        proizvod.setOpis(jtfOpis.getText());
        proizvod.setKolicina(Integer.parseInt(jtfKolicina.getText()));
        proizvod.setCena(Double.parseDouble(jtfCena.getText()));
        return proizvod;
    }

    private boolean proveriPolja(JTextField jtfNaziKnjige, JTextField jtfDobavljac, JTextField jtfOpis, JTextField jtfKolicina, JTextField jtfCena) {
        if (jtfNaziKnjige.getText().isEmpty()) {
            JOptionPane.showMessageDialog(jtfNaziKnjige, "Neophodno je uneti naziv proizvoda!");
            return false;
        }
        if (jtfKolicina.getText().isEmpty()) {
            JOptionPane.showMessageDialog(jtfKolicina, "Neophodno je uneti kolicinu proizvoda!");
            return false;
        }
        if (jtfCena.getText().isEmpty()) {
            JOptionPane.showMessageDialog(jtfCena, "Neophodno je uneti cenu proizvoda!");
            return false;
        }
        try {
            Integer.parseInt(jtfKolicina.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(jtfKolicina, "Neophodno je uneti kolicinu kao celobrojunu vrednost!");
            return false;
        }
        try {
            Double.parseDouble(jtfCena.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(jtfCena, "Neophodno je uneti cenu kao brojunu vrednost!");
            return false;
        }

        return true;
    }
}
