/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.forme.komponente;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.cometoin.domenskeKlase.Racun;

/**
 *
 * @author user
 */
public class RacuniTableModel extends DefaultTableModel {

    List racuni;
    String[] naziviKolona = new String[]{"Sifra racuna", "Datum", "Naziv partnera", "Obradjen", "Ukupna vrednost"};

    public RacuniTableModel(List racuni) {
        this.racuni = racuni;
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
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Boolean.class;
            case 4:
                return Double.class;
        }
        return null;
    }

    @Override
    public Object getValueAt(int row, int column) {

        Racun r = (Racun) racuni.get(row);

        switch (column) {
            case 0:
                return r.getSifra();
            case 1:
                return r.getDatum();
            case 2:
                return r.getNazivPartnera();
            case 3:
                return r.isObradjen();
            case 4:
                return r.getUkupnaVrednost();
        }
        return null;
    }

    @Override
    public int getRowCount() {
        if (racuni == null) {
            return 0;
        }
        return racuni.size();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Racun vratiRacun(int red) {
        //if (red == -1){
        return (Racun) racuni.get(red);
//        }
//        fireTableDataChanged();
//        return null;
    }
}
