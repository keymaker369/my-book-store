/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKorisnickogInterfejsa;

import domenskeKlase.Narudzbenica;
import domenskeKlase.Proizvod;
import forme.komponente.StavkaTableModel;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import domenskeKlase.StavkaNarudzbenice;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import klasaZaKomunikaciju.KlasaZaKomunikacijuKlijent;
import provere.Provera;

/**
 *
 * @author user
 */
public class KontrolerFormeNarudzbenica {

    private int rbStavke = 1;
    int signal;

    public KontrolerFormeNarudzbenica() {
    }

    public KontrolerFormeNarudzbenica(Narudzbenica narudzbenica) {
        rbStavke = narudzbenica.getStavkeNarudzbenice().size() + 1;
    }

    public void dodajStavku(JTable jtStavkeNarudzbenice, JComboBox jcbProizvodi) {
        Vector v = new Vector();
        v.add(rbStavke);
        v.add("");
        v.add("");
        v.add(0);
        v.add("0");
        v.add(0);
        ((StavkaTableModel) jtStavkeNarudzbenice.getModel()).addRow(v);

        //int red = jtStavkeNarudzbenice.getSelectedRow();
        TableColumn tc = jtStavkeNarudzbenice.getColumnModel().getColumn(1);
        tc.setCellEditor(new DefaultCellEditor(jcbProizvodi));

        rbStavke++;

    }

    public void zapamtiNarudzbenicu(JTextField jtfSifraNarudzbeniceKupca, JTextField jtfDatum, JTextField jtfKupac, JTable jtStavkeNarudzbenice, Narudzbenica narudzbenica, int mod, JDialog dialog) {
        int uspesnost = konvertujPodatkeIzKomponentiUObjekat(jtfSifraNarudzbeniceKupca, jtfDatum, jtfKupac, jtStavkeNarudzbenice, narudzbenica);
        switch (uspesnost) {
            case 1:
                JOptionPane.showMessageDialog(jtStavkeNarudzbenice, "Neophodno je izabrati proizvod!");
                return;
            case 2:
                JOptionPane.showMessageDialog(jtStavkeNarudzbenice, "Neophodno je uneti sifru narudzbenice kupca!");
                return;
            case 3:
                JOptionPane.showMessageDialog(jtStavkeNarudzbenice, "Neispravan datum! dd-mm-gggg");
                return;
        }
        switch (mod) {
            case 1:
                signal = KlasaZaKomunikacijuKlijent.getInstance().kreirajNarudzbenicu(narudzbenica);
                Provera.proveriSignal(dialog, signal);
                break;
            case 2:
                signal = KlasaZaKomunikacijuKlijent.getInstance().zapamtiNarudzbenicu(narudzbenica);
                Provera.proveriSignal(dialog, signal);
                break;
        }
        dialog.dispose();
    }

    public void obrisiStavku(JTable jtStavkeNarudzbenice) {
        if (rbStavke > 1) {
            ((StavkaTableModel) jtStavkeNarudzbenice.getModel()).removeRow(rbStavke - 2);
            rbStavke--;
        }
    }

    public LinkedList vratiSveProizvode() {
        return KlasaZaKomunikacijuKlijent.getInstance().vratiProizvode(new Proizvod());
    }

    private int vratiStavkeIzTabele(LinkedList stavkeNarudzbenice, JTable tabelaStavki, int sifraNarudzbenice) {
        int uspesnost = 0;
        for (int i = 1; i <= tabelaStavki.getRowCount(); i++) {
            StavkaNarudzbenice sn = new StavkaNarudzbenice();
            sn.setSifraNarudzbenice(sifraNarudzbenice);
            int sifraProizvoda = Integer.valueOf(((StavkaTableModel) tabelaStavki.getModel()).getValueAt(i - 1, 0).toString());
            System.out.println(sifraProizvoda);

            sn.setRedniBroj(Integer.valueOf(((StavkaTableModel) tabelaStavki.getModel()).getValueAt(i - 1, 0).toString()));
            sn.setNazivProizvoda(((StavkaTableModel) tabelaStavki.getModel()).getValueAt(i - 1, 1).toString());
            if (sn.getNazivProizvoda().equals("")) {
                //0 - sve je ok; 1 - nije setovan naziv proizvoda
                return 1;
            }
            sn.setSifraProizvoda(Integer.parseInt(((StavkaTableModel) tabelaStavki.getModel()).getValueAt(i - 1, 2).toString()));
            sn.setCenaProizvoda(Double.valueOf(((StavkaTableModel) tabelaStavki.getModel()).getValueAt(i - 1, 3).toString()));
            sn.setKolicina(Integer.valueOf(((StavkaTableModel) tabelaStavki.getModel()).getValueAt(i - 1, 4).toString()));
            stavkeNarudzbenice.add(sn);
        }
        return uspesnost;
    }

    private void izracunajUkupnuVrednost(Narudzbenica narudzbenica) {
        double ukupnaVrednost = 0;
        LinkedList lista = narudzbenica.getStavkeNarudzbenice();
        Iterator it = lista.iterator();
        while (it.hasNext()) {
            StavkaNarudzbenice sn = (StavkaNarudzbenice) it.next();
            ukupnaVrednost += sn.getCenaProizvoda() * sn.getKolicina();
        }
        narudzbenica.setUkupnaVrednost(ukupnaVrednost);
    }

    private int konvertujPodatkeIzKomponentiUObjekat(JTextField jtfSifraNarudzbeniceKupca, JTextField jtfDatum, JTextField jtfKupac, JTable jtStavkeNarudzbenice, Narudzbenica narudzbenica) {
        String sifraNarudzbeniceKupca = jtfSifraNarudzbeniceKupca.getText();
        if (sifraNarudzbeniceKupca.equals("")) {
            //0 - sve je ok; 2 - nije setovana sifra narudzbenice kupca
            return 2;
        }

        String datum = jtfDatum.getText();
        if (!proveriDatum(datum)) {
            //3 - datum nije dobar
            return 3;
        }

        String kupac = jtfKupac.getText();
        LinkedList stavkeNarudzbenice = new LinkedList();
        int uspesnost = vratiStavkeIzTabele(stavkeNarudzbenice, jtStavkeNarudzbenice, narudzbenica.getSifraNarudzbenice());
        narudzbenica.setSifraNarudzbeniceKup(sifraNarudzbeniceKupca);
        narudzbenica.setDatum(datum);
        narudzbenica.setKupac(kupac);
        narudzbenica.setStavkeNarudzbenice(stavkeNarudzbenice);
        izracunajUkupnuVrednost(narudzbenica);
        return uspesnost;
    }

    private boolean proveriDatum(String datum) {
        try {
            String dan = datum.substring(0, 2);
            String mesec = datum.substring(3, 5);
            String godina = datum.substring(6, datum.length());

            int d = Integer.parseInt(dan);
            int m = Integer.parseInt(mesec);
            int g = Integer.parseInt(godina);

            if (d > 31 || d < 1) {
                return false;
            }
            if (m < 1 || m > 12) {
                return false;
            }
            if (g < 1900 || g > 9999) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
