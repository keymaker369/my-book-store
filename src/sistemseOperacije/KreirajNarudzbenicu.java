/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemseOperacije;

import domenskeKlase.Narudzbenica;
import domenskeKlase.OpstiDomenskiObjekat;
import domenskeKlase.StavkaNarudzbenice;
import dbbr.DatabaseBroker;
import java.util.Iterator;
import java.util.LinkedList;

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

        signal = DatabaseBroker.getInstance().vratiZadnjiSlog(odo);
        if (!proveriUspesnost()) {
            return signal;
        }
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
        LinkedList stavke = ((Narudzbenica) odo).getStavkeNarudzbenice();
        Iterator it = stavke.iterator();
        while (it.hasNext()) {
            StavkaNarudzbenice sn = (StavkaNarudzbenice) it.next();
            sn.setSifraNarudzbenice(((Narudzbenica)odo).getSifraNarudzbenice());
            signal = DatabaseBroker.getInstance().pamtiSlog(sn);
        }
        return proveriUspesnost();
    }
}
