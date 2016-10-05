/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.provere;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Provera {

//    private static final int NEUSPESNO_CITANJE_IZ_BAZE = 1;
//    private static final int NEUSPESNO_BRISANJE_IZ_BAZE = 2;
//    private static final int GRESKA_PRILIKOM_PAMCENJA_SLOGA_U_BAZI = 3;
//    private static final int GRESKA_KOD_CITANJA_ZADNJEG_SLOGA_U_BAZI = 4;
//    private static final int GRESKA_PRILIKOM_UPDEJTA_SLOGA_U_BAZI = 5;
//    private static final int SLOG_NE_POSTOJI_U_BAZI = 6;
//    private static final int NIJE_USPESNO_PRETRAZENA_BAZA = 7;
//    private static final int GRESKA_PRILIKOM_ISCITAVANJA_STAVKI_NARUDZBENICE = 8;
//    private static final int GRESKA_PRILIKOM_ISCITAVANJA_STAVKI_RACUNA = 9;
//    private static final int GRESKA_PRILIKOM_ISCITAVANJA_POJEDINIH_PROIZVODA = 10;
//    private static final int NEUSPESNO_POVEZIVANJE_NA_SERVER = 11;
    public static void proveriSignal(Component komponenta, int signal) {
        switch (signal) {

            case 0:
                JOptionPane.showMessageDialog(komponenta, "OPERACIJA USPESNO IZVRSENA!");
                break;
            case 3:
                JOptionPane.showMessageDialog(komponenta, "GRESKA_PRILIKOM_PAMCENJA_SLOGA_U_BAZI!");
                break;
            case 4:
                JOptionPane.showMessageDialog(komponenta, "GRESKA_KOD_CITANJA_ZADNJEG_SLOGA_U_BAZI!");
                break;
            case 5:
                JOptionPane.showMessageDialog(komponenta, "GRESKA_PRILIKOM_UPDEJTA_SLOGA_U_BAZI!");
                break;
            case 6:
                JOptionPane.showMessageDialog(komponenta, "SLOG_NE_POSTOJI_U_BAZI!");
                break;
            case 7:
                JOptionPane.showMessageDialog(komponenta, "NIJE_USPESNO_PRETRAZENA_BAZA!");
                break;
            case 8:
                JOptionPane.showMessageDialog(komponenta, "GRESKA_PRILIKOM_ISCITAVANJA_STAVKI_NARUDZBENICE!");
                break;
            case 9:
                JOptionPane.showMessageDialog(komponenta, "GRESKA_PRILIKOM_ISCITAVANJA_STAVKI_RACUNA!");
                break;
            case 10:
                JOptionPane.showMessageDialog(komponenta, "GRESKA_PRILIKOM_ISCITAVANJA_POJEDINIH_PROIZVODA!");
                break;
            case 11:
                JOptionPane.showMessageDialog(komponenta, "NEUSPESNO_POVEZIVANJE_NA_SERVER!");
                break;
        }
    }
}
