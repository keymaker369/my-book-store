/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemseOperacije;

import domenskeKlase.OpstiDomenskiObjekat;
import domenskeKlase.Proizvod;
import dbbr.DatabaseBroker;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class VratiSveProizvode extends OpstaSistemskaOperacija {

    private static LinkedList proizvodi;

    public static LinkedList vratiSveProizvode(Proizvod proizvod) {
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
