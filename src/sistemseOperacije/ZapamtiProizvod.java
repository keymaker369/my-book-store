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
public class ZapamtiProizvod extends OpstaSistemskaOperacija {

    public static int zapamtiProizvod(Proizvod proizvod) {
        ZapamtiProizvod zm = new ZapamtiProizvod();
        return zm.opsteIzvrsenjeSO(proizvod);
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {

        if (!proveriPreduslov(odo)) {
            return 1;
        }

        signal = DatabaseBroker.getInstance().updejtujSlog(odo);
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
        Proizvod proizvodZaIzmenu = new Proizvod();
        proizvodZaIzmenu.setSifraProizvoda(((Proizvod) odo).getSifraProizvoda());
        signal = DatabaseBroker.getInstance().vratiSlog(proizvodZaIzmenu);
        if (proizvodZaIzmenu.getSifraProizvoda() == 0) {
            return false;
        }
        return true;
    }
}
