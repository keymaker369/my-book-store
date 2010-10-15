/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ksks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class KlasaZaKS {

    public void pokreniServer() {
        try {
            ServerSocket ss = new ServerSocket(12345);
            System.out.println("Server je podignut i spreman za rad!!!");

            while (true) {
                Socket s = ss.accept();
                System.out.println("Klijent se povezao na server!!!");
                new ObradiKlijenta(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(KlasaZaKS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        System.out.println("agggggggg");
        KlasaZaKS nk = new KlasaZaKS();
        nk.pokreniServer();
    }
}
