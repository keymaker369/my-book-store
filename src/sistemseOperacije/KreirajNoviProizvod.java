/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemseOperacije;

import domenskeKlase.OpstiDomenskiObjekat;
import domenskeKlase.Proizvod;
import dbbr.DatabaseBroker;

/**
 *
 * @author user
 */
public class KreirajNoviProizvod extends OpstaSistemskaOperacija {

    public static int kreirajNoviProizvod(Proizvod p) {
        KreirajNoviProizvod knp = new KreirajNoviProizvod();
        return knp.opsteIzvrsenjeSO(p);
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {

        signal = DatabaseBroker.getInstance().pamtiSlog(odo);
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
}
