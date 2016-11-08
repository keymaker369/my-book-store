/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.sistemseOperacije;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.cometoin.dbbr.DatabaseBroker;
import com.cometoin.domenskeKlase.Narudzbenica;
import com.cometoin.domenskeKlase.OpstiDomenskiObjekat;
import com.cometoin.domenskeKlase.StavkaNarudzbenice;

/**
 *
 * @author user
 */
public class KreirajNarudzbenicu extends OpstaSistemskaOperacija {

    public static int kreirajNarudzbenicu(Narudzbenica narudzbenica) {
        KreirajNarudzbenicu zn = new KreirajNarudzbenicu();
        return zn.opsteIzvrsenjeSO(narudzbenica);
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {

        ((Narudzbenica) odo).setSifraNarudzbenice(((Narudzbenica) odo).getSifraNarudzbenice() + 1);

        signal = DatabaseBroker.getInstance().pamtiSlog(odo);
        if (!proveriUspesnost()) {
            return signal;
        }

        if(!kreirajStavkeNarudzbenice(odo)){
            return signal;
        }

        return 0;
    }

    @Override
    public boolean proveriUspesnost() {
        switch (signal) {
            case 0:
                return true;
            default:
                return false;

        }
    }

    private boolean kreirajStavkeNarudzbenice(OpstiDomenskiObjekat odo) {
        List stavke = ((Narudzbenica) odo).getStavkeNarudzbenice();
        Iterator it = stavke.iterator();
        while (it.hasNext()) {
            StavkaNarudzbenice sn = (StavkaNarudzbenice) it.next();
            sn.setSifraNarudzbenice(((Narudzbenica)odo).getSifraNarudzbenice());
            signal = DatabaseBroker.getInstance().pamtiSlog(sn);
        }
        return proveriUspesnost();
    }
}
