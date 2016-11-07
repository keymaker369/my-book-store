/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.domenskeKlase;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author user
 */
public class TransferKlasa implements Serializable {

    public static final int KRAJ_RADA = 0;
    public static final int ZAPAMTI_PROIZVOD = 1;
    public static final int OBRISI_PROIZVOD = 2;
    public static final int KREIRAJ_NOVI_PROIZVOD = 3;
    public static final int KREIRAJ_NARUDZBENICU = 4;
    public static final int ZAPAMTI_NARUDZBENICU = 5;
    public static final int VRATI_PROIZVODE = 6;
    public static final int VRATI_SVE_NARUDZBENICE = 7;
    public static final int OBRISI_NARUDZBENICU = 8;
    public static final int VRATI_SVE_RACUNE = 9;
    public static final int KREIRAJ_RACUN = 10;
    public static final int ZAPAMTI_RACUN = 11;
    public static final int OBRISI_RACUN = 12;
    public static final int OBRADI_RACUN = 13;
    public static final int VRATI_POJEDINE_PROIZVODE = 14;
    private Object o;
    private int operacija;
    private List lista;
    private int signal;

    /**
     * @return the o
     */
    public Object getO() {
        return o;
    }

    /**
     * @param o the o to set
     */
    public void setO(Object o) {
        this.o = o;
    }

    /**
     * @return the operacija
     */
    public int getOperacija() {
        return operacija;
    }

    /**
     * @param operacija the operacija to set
     */
    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    /**
     * @return the lista
     */
    public List getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List lista) {
        this.lista = lista;
    }

    /**
     * @return the signal
     */
    public int getSignal() {
        return signal;
    }

    /**
     * @param signal the signal to set
     */
    public void setSignal(int signal) {
        this.signal = signal;
    }
}
