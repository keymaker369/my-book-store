/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemseOperacije;

import domenskeKlase.Narudzbenica;
import domenskeKlase.OpstiDomenskiObjekat;
import dbbr.DatabaseBroker;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class VratiSveNarudzbenice extends OpstaSistemskaOperacija {

    private static LinkedList narudzbenice;

    public static LinkedList vratiSveNarudzbenice(Narudzbenica narudzbenica) {
        VratiSveNarudzbenice vsn = new VratiSveNarudzbenice();
        vsn.opsteIzvrsenjeSO(narudzbenica);
        return narudzbenice;
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {
        narudzbenice = DatabaseBroker.getInstance().vratiListuSvihSlogova(odo);
        if (narudzbenice == null) {
            return signal;
        }

        if (!napuniNarudzbenice(narudzbenice)) {
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

    private boolean napuniNarudzbenice(LinkedList narudzbenice) {
        Iterator it = narudzbenice.iterator();
        while (it.hasNext()) {
            Narudzbenica n = (Narudzbenica) it.next();
            signal = DatabaseBroker.getInstance().napuniNarudzbenicu(n);
        }
        return true;
    }
}
