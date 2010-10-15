/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemseOperacije;

import domenskeKlase.OpstiDomenskiObjekat;
import domenskeKlase.Racun;
import domenskeKlase.StavkaRacuna;
import dbbr.DatabaseBroker;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class KreirajRacun extends OpstaSistemskaOperacija {

    public static int kreirajRacun(Racun racun) {
        KreirajRacun kn = new KreirajRacun();
        return kn.opsteIzvrsenjeSO(racun);
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {

        signal = DatabaseBroker.getInstance().vratiZadnjiSlog(odo);
        if (!proveriUspesnost()) {
            return signal;
        }
        ((Racun) odo).setSifra(((Racun) odo).getSifra() + 1);


        signal = DatabaseBroker.getInstance().pamtiSlog(odo);
        if (!proveriUspesnost()) {
            return signal;
        }

        if (!kreirajStavkeRacuna(odo)) {
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

    private boolean kreirajStavkeRacuna(OpstiDomenskiObjekat odo) {
        LinkedList stavke = ((Racun) odo).getStavkeRacuna();
        if (stavke == null) {
            return proveriUspesnost();
        }
        
        Iterator it = stavke.iterator();
        while (it.hasNext()) {
            StavkaRacuna sr = (StavkaRacuna) it.next();
            sr.setSifra(((Racun) odo).getSifra());
            signal = DatabaseBroker.getInstance().pamtiSlog(sr);
        }
        return proveriUspesnost();
    }
}
