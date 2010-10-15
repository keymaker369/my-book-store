/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.komponente;

import domenskeKlase.Narudzbenica;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class NarudzbeniceTableModel extends DefaultTableModel {

    LinkedList narudzbenice;
    String[] naziviKolona = new String[]{"Sifra narudzbenice", "Sifra narudzbenice kupca", "Kupac", "Datum", "Ukupna vrednost"};

    public NarudzbeniceTableModel(LinkedList narudzbenice) {
        this.narudzbenice = narudzbenice;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        return naziviKolona[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Double.class;
        }
        return null;
    }

    @Override
    public Object getValueAt(int row, int column) {

        Narudzbenica n = (Narudzbenica) narudzbenice.get(row);

        switch (column) {
            case 0:
                return n.getSifraNarudzbenice();
            case 1:
                return n.getSifraNarudzbeniceKup();
            case 2:
                return n.getKupac();
            case 3:
                return n.getDatum();
            case 4:
                return n.getUkupnaVrednost();
        }
        return null;
    }

    @Override
    public int getRowCount() {
        if (narudzbenice == null) {
            return 0;
        }
        return narudzbenice.size();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Narudzbenica vratiNarudzbenicu(int red) {
        //if (red == -1){
        return (Narudzbenica) narudzbenice.get(red);
//        }
//        fireTableDataChanged();
//        return null;
    }
}
