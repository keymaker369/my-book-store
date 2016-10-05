/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.sistemseOperacije;

import com.cometoin.dbbr.DatabaseBroker;
import com.cometoin.domenskeKlase.OpstiDomenskiObjekat;
import com.cometoin.domenskeKlase.Racun;

/**
 *
 * @author user
 */
public class ObradiRacun extends OpstaSistemskaOperacija {

    public static int obradiRacun(Racun racun) {
        ObradiRacun or = new ObradiRacun();
        return or.opsteIzvrsenjeSO(racun);
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {

        if (!proveriPreduslov(odo)) {
            return signal;
        }

        Racun r = (Racun) odo;
        r.setObradjen(true);
        signal = DatabaseBroker.getInstance().updejtujSlog(r);
        if (!proveriUspesnost()) {
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

    private boolean proveriPreduslov(OpstiDomenskiObjekat odo) {

        Racun stariRacun = new Racun();
        stariRacun.setSifra(((Racun) odo).getSifra());
        signal = DatabaseBroker.getInstance().vratiSlog(stariRacun);
        if (!proveriUspesnost()) {
            return false;
        }

        if (stariRacun.isObradjen()) {
            return false;
        }

        return true;
    }
}
