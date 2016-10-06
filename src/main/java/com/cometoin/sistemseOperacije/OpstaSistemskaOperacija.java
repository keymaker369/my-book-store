/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.sistemseOperacije;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.cometoin.dbbr.DatabaseBroker;
import com.cometoin.domenskeKlase.OpstiDomenskiObjekat;
import com.cometoin.domenskeKlase.Proizvod;

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
        
        //http://docs.jboss.org/hibernate/orm/5.2/topical/html_single/bootstrap/NativeBootstrapping.html
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
        	.configure( "org/hibernate/example/MyCfg.xml" )
        	.build();

		Metadata metadata = new MetadataSources( standardRegistry )
		        .addAnnotatedClass( Proizvod.class )
		        .addAnnotatedClassName( "org.hibernate.example.Customer" )
		        .addResource( "org/hibernate/example/Order.hbm.xml" )
		        .addResource( "org/hibernate/example/Product.orm.xml" )
		        .getMetadataBuilder()
		        .applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
		        .build();
		
//		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
//		        .applyBeanManager( getBeanManagerFromSomewhere() )
//		        .build();
        
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
