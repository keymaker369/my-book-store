/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cometoin.dbbr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.cometoin.domenskeKlase.Narudzbenica;
import com.cometoin.domenskeKlase.OpstiDomenskiObjekat;
import com.cometoin.domenskeKlase.Racun;
import com.cometoin.domenskeKlase.StavkaNarudzbenice;
import com.cometoin.domenskeKlase.StavkaRacuna;

import oracle.jdbc.OracleTypes;

/**
 *
 * @author user
 */
public class DatabaseBroker {

    private static DatabaseBroker instance;
    private Connection con;
    private Statement st;
    
    private static SessionFactory sessionFactory;
    Session session;

    
    static {
    	//http://docs.jboss.org/hibernate/orm/5.2/topical/html_single/bootstrap/NativeBootstrapping.html
    	try {        
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
            	.configure( "hibernate.cfg.xml" )
            	.build();

    		Metadata metadata = new MetadataSources(standardRegistry)
    		        .addAnnotatedClass(Narudzbenica.class)
    		        .addAnnotatedClass(StavkaNarudzbenice.class)
    		        .addAnnotatedClass(Racun.class)
    		        .addAnnotatedClass(StavkaRacuna.class)
    		        //.addAnnotatedClassName( "org.hibernate.example.Customer" )
//        		        .addResource( "org/hibernate/example/Order.hbm.xml" )
//        		        .addResource( "org/hibernate/example/Product.orm.xml" )
    		        .getMetadataBuilder()
    		        .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
    		        .build();
	
    		sessionFactory = metadata.getSessionFactoryBuilder()
//        		        .applyBeanManager( getBeanManagerFromSomewhere() )
    		        .build();
        
        } catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("Neuspesno povezivanje sa bazom.");
        }
    }
    
    public static DatabaseBroker getInstance() {

        if (instance == null) {
            instance = new DatabaseBroker();
        }

        return instance;
    }

    private DatabaseBroker() {
    }

//    public int poveziSeSaBazom() {
//        try {
//            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//            String url = "jdbc:odbc:Seminarski";
//            con = DriverManager.getConnection(url);
//            con.setAutoCommit(false);
//            return 0;
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 1;
//    }
    public int poveziSeSaBazom() {
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
//            con.setAutoCommit(false);
//            System.out.println("uspesna konekcija");
//            return 0;
//        } catch (SQLException ex) {
//            //Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
//            System.err.println("Greska prilikom ucitavanja driver-a... -> " + ex);
//        } catch (ClassNotFoundException ex) {
//            //Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
//            System.err.println("Greska prilikom otvaranja konekcije sa bazom... -> " + ex);
//        }
        
    	if(session == null || !session.isOpen())
    		session = sessionFactory.openSession();
    	
        return 0;
    }

	public void startTranskacije() {
		session.beginTransaction();		
	}
    
    public int commitTransakcije() {
        try {
            session.getTransaction().commit();
            return 0;
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public int rollbackTransakcije() {
        try {
//            con.rollback();
        	session.getTransaction().rollback();
            return 0;
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public int zatvoriBazu() {
        try {
            session.close();
            return 0;
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;

    }

    public int vratiSlog(OpstiDomenskiObjekat odo) {
        try {
            String upit = "FROM " + odo.vratiImeTabele()
                    + " WHERE " + odo.vratiUslovZaNadjiSlog();
            System.out.println(upit);
            Query query = session.createQuery(upit);
            OpstiDomenskiObjekat odoIzBaze = (OpstiDomenskiObjekat) query.getSingleResult();
//            st = con.createStatement();
//            ResultSet rs = st.executeQuery(upit);
            odo.napuniSve(odoIzBaze);
//            rs.close();
//            st.close();
        } catch (Exception ex) {
            System.out.println("Neuspesno prilikom citanja iz baze.");
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
        return 0;
    }

    public int obrisiSlog(OpstiDomenskiObjekat odo) {
        try {
            String upit = "DELETE FROM " + odo.vratiImeTabele()
                    + " WHERE " + odo.vratiUslovZaObrisiSlog();
            System.out.println(upit);
            Query query = session.createQuery(upit);
            int countOfDeletedEntities = query.executeUpdate();
            System.out.println("Obrisano entiteta: " + countOfDeletedEntities);
//            st = con.createStatement();
//            st.executeUpdate(upit);
//            st.close();
        } catch (Exception ex) {
            System.out.println("Neuspesno prilikom brisanja iz baze.");
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            return 2;
        }
        return 0;
    }

    public int pamtiSlog(OpstiDomenskiObjekat odo) {
        String upit;
        try {
//            st = con.createStatement();
            upit = "INSERT INTO " + odo.vratiImeTabele()
                    + odo.vratiKoloneZaInsert()
                    + " VALUES (" + odo.vratiVrednostiZaInsert() + ")";
            System.out.println("Upis unosa stavke pre izvesenja:" + upit);
            Object o = session.save(odo);
//            st.executeUpdate(upit);
//            st.close();
        } catch (Exception esql) {
            System.out.println("Greska prilikom pamcenja sloga u bazi: " + esql);
            return 3;
        }
        return 0;
    }

    public int vratiZadnjiSlog(OpstiDomenskiObjekat odo) {
        String upit;
        ResultSet rs;
        try {
//            st = con.createStatement();
            System.out.println(odo.vratiAtributPretrazivanja());
            upit = "SELECT Max(" + odo.vratiAtributPretrazivanja() + " ) as Max FROM " + odo.vratiImeTabele();
            System.out.println(upit);
            Query query = session.createQuery(upit);
            Integer izBaze = (Integer) query.uniqueResult();
//            rs = st.executeQuery(upit);
//            if (izBaze!= null && !odo.napuniSve(izBaze)) {
//            if (!odo.napuniSve(null)) {
//                return 1;
//            }

//            rs.close();
//            st.close();

        } catch (Exception e) {
            System.out.println("Greska kod citanja zadnjeg unetog sloga u bazu" + e);
            return 4;
        }
        return 0;

    }

    public int updejtujSlog(OpstiDomenskiObjekat odo) {
        String upit;

        try {

//            st = con.createStatement();
            upit = "UPDATE " + odo.vratiImeTabele()
                    + " SET " + odo.postaviVrednostiAtributaZaUpdate()
                    + " WHERE " + odo.vratiUslovZaNadjiSlog();
            System.out.println("PROMENI SLOG - upit:" + upit);
            Query query = session.createQuery(upit);
            int countOfUpdatedEntities = query.executeUpdate();
            System.out.println("Updejtovano entiteta: " + countOfUpdatedEntities);
//            st.executeUpdate(upit);
//            st.close();
        } catch (Exception esql) {
            esql.printStackTrace();
            return 5;
        }
        return 0;
    }

    public int daLiPostojiSlog(OpstiDomenskiObjekat odo) {
        String upit;
        ResultSet rs;

        try {

//            st = con.createStatement();
            upit = " FROM " + odo.vratiImeTabele()
                    + " WHERE " + odo.vratiUslovZaNadjiSlog();
//            rs = st.executeQuery(upit);
            System.out.println(upit);
            Query query = session.createQuery(upit);
            List result = query.list();
//            rs.close();
//            st.close();

            if (result.isEmpty()) {
                return 6; // Slog ne postoji u bazi.
            }
        } catch (Exception esql) {
            System.out.println("Nije uspesno pretrazena baza: " + esql);
            return 7; // Neuspesno pretrazivanje baze
        }
        return 0; // Slog postoji u bazi.
    }

    public List vratiListuSvihSlogova(OpstiDomenskiObjekat odo) {
        String upit;
        ResultSet rs;
        LinkedList listaSlogova;
        try {
            upit = "FROM " + odo.vratiImeTabele() + " ORDER BY " + odo.vratiAtributPretrazivanja() + " ASC";
            System.out.println(upit);
            Query query = session.createQuery(upit);
            return query.list();
//            st = con.createStatement();
//            rs = st.executeQuery(upit);
//            listaSlogova = odo.vratiSveOvogTipa(rs);
        } catch (Exception esql) {
            System.out.println("Nije uspesno promenjen slog u bazu: " + esql);
            return null;
        }
//        return listaSlogova;
    }

    public List vratiListuPojedinihSlogova(OpstiDomenskiObjekat odo) {
        String upit;
        ResultSet rs;
        LinkedList listaSlogova;
        try {
            upit =  " FROM " + odo.vratiImeTabele()
                    + " WHERE " + odo.vratiUslovZaNadjiSlogove();
            System.out.println(upit);
            Query query = session.createQuery(upit);
            return query.list();
//            st = con.createStatement();
            //PreparedStatement ps = con.prepareStatement("SELECT * FROM Proizvod WHERE (((Proizvod.[naziv]) Like \"*\" & 'Rat' & \"*\"));");
            //System.out.println(ps.toString());
            //ps.setString(1, "Rat");
            //rs = ps.executeQuery();
//            rs = st.executeQuery(upit);
            //rs = st.executeQuery("SELECT * FROM Proizvod WHERE (((Proizvod.[naziv]) Like \"*\" & 'a' & \"*\"));");
            //rs = st.executeQuery("SELECT * FROM Proizvod where naziv = 'Rat i Mir'");
//            listaSlogova = odo.vratiSveOvogTipa(rs);
        } catch (Exception esql) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, esql);
            return null;
        }
//        return listaSlogova;
    }

    public int napuniNarudzbenicu(OpstiDomenskiObjekat odo) {
        Narudzbenica n = (Narudzbenica) odo;

        String upit;
        ResultSet rs;
//        LinkedList listaStavki = new LinkedList();
        try {
            upit = "FROM StavkaNarudzbenice where " + odo.vratiUslovZaNadjiSlog();
            System.out.println(upit);
//            st = con.createStatement();
//            rs = st.executeQuery(upit);
            Query query = session.createQuery(upit);
            List<StavkaNarudzbenice> listaStavki = query.list();
//            listaStavki = (new StavkaNarudzbenice()).vratiSveOvogTipa(rs);
            n.setStavkeNarudzbenice(listaStavki);
        } catch (Exception esql) {
            System.out.println("Greska prilikom iscitavanja stavki narudzbenice: "+esql);
            return 8;
        }
        return 0;
    }

    public int napuniRacun(OpstiDomenskiObjekat odo) {
        Racun r = (Racun) odo;

        String upit;
        ResultSet rs;
        LinkedList listaStavki = new LinkedList();
        try {
            upit = "SELECT * FROM stavkaRacuna where " + odo.vratiUslovZaNadjiSlog();
            System.out.println(upit);
            st = con.createStatement();
            rs = st.executeQuery(upit);
            listaStavki = (new StavkaRacuna()).vratiSveOvogTipa(rs);
            r.setStavkeRacuna(listaStavki);
        } catch (SQLException esql) {
            System.out.println("Greska prilikom iscitavanja stavki racuna: "+esql);
            return 9;
        }
        return 0;
    }
    
    public static void main(String[] args){
    	DatabaseBroker.getInstance().poveziSeSaBazom();
    }


}
