/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.forme.komponente;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class StavkaTableModel extends DefaultTableModel {

    // private LinkedList listaStavkiNarudzbenice;
    String[] naziviKolona = new String[]{"BR.", "Naziv", "Sifra", "Cena", "Kolicina", "Suma"};

    public StavkaTableModel() {
        super();
        //listaStavkiNarudzbenice = lista;

//        StavkaNarudzbenice st = new StavkaNarudzbenice();
//        st.setRedniBroj(1);
//        st.setNazivProizvoda("grg2g");
//        st.setSifraProizvoda("woigwiorg");
//        st.setCenaProizvoda(8165);
//        st.setKolicina(3333);
//        lista.add(st);
    }

//    @Override
//    public int getRowCount() {
//        return 4;
//    }
//    @Override
//    public Object getValueAt(int row, int column) {
//
//        StavkaNarudzbenice sn = (StavkaNarudzbenice) listaStavkiNarudzbenice.get(row);
//
//        switch (column) {
//            case 0:
//                return sn.getRedniBroj();
//            case 1:
//                return sn.getNazivProizvoda();
//            case 2:
//                return sn.getSifraProizvoda();
//            case 3:
//                return sn.getCenaProizvoda();
//            case 4:
//                return sn.getKolicina();
//            case 5:
//                return sn.getCenaProizvoda() * sn.getKolicina();
//        }
//        return null;
//    }
//
//    @Override
//    public int getRowCount() {
//        if (listaStavkiNarudzbenice == null) {
//            return 0;
//        }
//        return listaStavkiNarudzbenice.size();
//    }
    @Override
    public int getColumnCount() {
        return 6;
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
                return Integer.class;
            case 3:
                return Double.class;
            case 4:
                return Integer.class;
            case 5:
                return Double.class;
        }
        return null;
    }
//    public void dodajStavku(StavkaNarudzbenice sn) {
//        listaStavkiNarudzbenice.add(sn);
//        fireTableDataChanged();
//    }
//
//    public void obrisiStavku(int red) {
//        listaStavkiNarudzbenice.remove(red);
//        fireTableDataChanged();
//    }
//
//    @Override
//    public void setValueAt(Object aValue, int row, int column) {
//        super.setValueAt(aValue, row, column);
//    }

    @Override
    public boolean isCellEditable(int row, int column) {
        switch (column){
            case 0: return false;
            case 1: return true;
            case 2: return false;
            case 3: return false;
            case 4: return true;
            case 5: return false;
            default: return false;
        }
    }

}
