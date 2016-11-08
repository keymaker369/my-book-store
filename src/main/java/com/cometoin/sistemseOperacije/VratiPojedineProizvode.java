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
 * @author nenad
 */
public class VratiPojedineProizvode extends OpstaSistemskaOperacija {

    private static List proizvodi;

    public static List vratiPojedineProizvode(Proizvod proizvod) {
        VratiPojedineProizvode vsr = new VratiPojedineProizvode();
        vsr.opsteIzvrsenjeSO(proizvod);
        return proizvodi;
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {
        proizvodi = DatabaseBroker.getInstance().vratiListuPojedinihSlogova(odo);
        if (proizvodi == null) {
            return 10;
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
}
