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
 * @author nenad
 */
public class VratiPojedineProizvode extends OpstaSistemskaOperacija {

    private static LinkedList proizvodi;

    public static LinkedList vratiPojedineProizvode(Proizvod proizvod) {
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
