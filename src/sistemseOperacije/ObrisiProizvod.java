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
public class ObrisiProizvod extends OpstaSistemskaOperacija {

    public static int obrisiProizvod(Proizvod p) {
        ObrisiProizvod op = new ObrisiProizvod();
        return op.opsteIzvrsenjeSO(p);
    }

    @Override
    public int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo) {
        if (!proveriPreduslov(odo)) {
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
        Proizvod proizvodZaIzmenu = new Proizvod();
        proizvodZaIzmenu.setSifraProizvoda(((Proizvod) odo).getSifraProizvoda());
        signal = DatabaseBroker.getInstance().vratiSlog(proizvodZaIzmenu);
        if (proizvodZaIzmenu.getSifraProizvoda() == 0) {
            return false;
        }
        return true;
    }
}
