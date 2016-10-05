/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.forme.komponente;

import java.util.LinkedList;

import javax.swing.table.DefaultTableModel;

import com.cometoin.domenskeKlase.Proizvod;

/**
 *
 * @author user
 */
public class KatalogTableModel extends DefaultTableModel {

    LinkedList proizvodi;
    String[] naziviKolona = new String[]{ "Sifra proizvoda", "Naziv", "Dobavljac", "Opis", "Cena", "Kolicina"};

    public KatalogTableModel(LinkedList proizvodi) {
        this.proizvodi = proizvodi;
    }

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
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Double.class;
            case 5:
                return Integer.class;
        }
        return null;
    }


    @Override
    public Object getValueAt(int row, int column) {

        Proizvod p = (Proizvod)proizvodi.get(row);

        switch (column) {
            case 0:
                return p.getSifraProizvoda();
            case 1:
                return p.getNaziv();
            case 2:
                return p.getDobavljac();
            case 3:
                return p.getOpis();
            case 4:
                return p.getCena();
            case 5:
                return p.getKolicina();
        }
        return null;
    }

    @Override
    public int getRowCount() {
        if (proizvodi == null) {
            return 0;
        }
        return proizvodi.size();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Proizvod vratiProizvod(int red){
        //if (red == -1){
            return (Proizvod)proizvodi.get(red);
//        }
//        fireTableDataChanged();
//        return null;
    }


}