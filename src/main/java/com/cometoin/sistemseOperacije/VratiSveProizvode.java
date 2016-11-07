/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.sistemseOperacije;

import java.util.LinkedList;
import java.util.List;

import com.cometoin.dbbr.DatabaseBroker;
import com.cometoin.domenskeKlase.OpstiDomenskiObjekat;
import com.cometoin.domenskeKlase.Proizvod;

/**
 *
 * @author user
 */
public class VratiSveProizvode extends OpstaSistemskaOperacija {

    private static List proizvodi;

    public static List vratiSveProizvode(Proizvod proizvod) {
        VratiSveProizvode vsp = new VratiSveProizvode();
        vsp.opsteIzvrsenjeSO(proizvod);
        return proizvodi;
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {
        proizvodi = DatabaseBroker.getInstance().vratiListuSvihSlogova(odo);
        if(proizvodi == null)
            return signal;
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
}
