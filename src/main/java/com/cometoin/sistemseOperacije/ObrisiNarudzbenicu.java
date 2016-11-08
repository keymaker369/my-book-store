/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.sistemseOperacije;

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
public class ObrisiNarudzbenicu extends OpstaSistemskaOperacija {

    public static int obrisiNarudzbenicu(Narudzbenica narudzbenica) {
        ObrisiNarudzbenicu on = new ObrisiNarudzbenicu();
        return on.opsteIzvrsenjeSO(narudzbenica);
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {
        if (!proveriPreduslov(odo)) {
            return signal;
        }

        if (!obrisiStavke(odo)) {
            return signal;
        }

        signal = DatabaseBroker.getInstance().obrisiSlog(odo);
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

    public boolean proveriPreduslov(OpstiDomenskiObjekat odo) {
        int rezultatPretrage = DatabaseBroker.getInstance().daLiPostojiSlog(odo);
        if (rezultatPretrage == 0) {
            return true;
        }
        return false;
    }

    private boolean obrisiStavke(OpstiDomenskiObjekat odo) {
        List stavke = ((Narudzbenica) odo).getStavkeNarudzbenice();
        if (stavke != null) {
            if (stavke.size() != 0) {//sa ovim se brisu sve stavke posto je tako namesten uslov u stavki narudzbenice (vraca samo sifru narudzbenice)
                signal = DatabaseBroker.getInstance().obrisiSlog((StavkaNarudzbenice) stavke.get(0));
            }
        }
        return proveriUspesnost();
    }
}
