/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemseOperacije;

import domenskeKlase.OpstiDomenskiObjekat;
import dbbr.DatabaseBroker;

/**
 *
 * @author user
 */
public abstract class OpstaSistemskaOperacija {

    public int signal;

    public int opsteIzvrsenjeSO(OpstiDomenskiObjekat odo) {

        otvoriBazu();

        if (!proveriUspesnostOpsta(signal)) {
            return signal;
        }

        signal = izvrsenjeSistemskeOperacije(odo);

        if (proveriUspesnost()) {
            komitujTransakciju();
        } else {
            izvrsiRollback();
        }

        zatvoriBazu();
        return signal;
    }

    private void otvoriBazu() {
        signal = DatabaseBroker.getInstance().poveziSeSaBazom();
    }

    public boolean proveriUspesnostOpsta(int sig) {

        switch (sig) {

            case 0:
                return true;
            default:
                return false;

        }
    }

    private void komitujTransakciju() {
        DatabaseBroker.getInstance().commitTransakcije();
    }

    private void izvrsiRollback() {
        DatabaseBroker.getInstance().rollbackTransakcije();
    }

    private void zatvoriBazu() {
        DatabaseBroker.getInstance().zatvoriBazu();
    }

    public abstract int izvrsenjeSistemskeOperacije(OpstiDomenskiObjekat odo);

    public abstract boolean proveriUspesnost();
}
