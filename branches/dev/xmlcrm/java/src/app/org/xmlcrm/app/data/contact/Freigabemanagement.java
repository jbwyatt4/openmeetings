package org.xmlcrm.app.data.contact;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.xmlcrm.app.hibernate.beans.contact.contactfreigabe;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.app.remote.ResHandler;
import org.xmlcrm.utils.math.Calender;

public class Freigabemanagement {
    
    private ResHandler ResHandler;
    private Calender CalenderI;
    
    /**
     * 
     * @param handler
     */
    public Freigabemanagement(ResHandler handler) {
        super();
        // TODO Auto-generated constructor stub
        ResHandler = handler;
        CalenderI = new Calender();
    }
    
    public contactfreigabe[] getFreigabeDecr(long USER_LEVEL){
        contactfreigabe contactfreigabe[] = new contactfreigabe[1];
        if (USER_LEVEL>1){
            try {
                Session session = HibernateUtil.currentSession();
                Transaction tx = session.beginTransaction();    
                Query query = session.createQuery("from contactfreigabe");
                int count = query.list().size();
                contactfreigabe = new contactfreigabe[count];
                int k = 0;
                for (Iterator it2 = query.iterate(); it2.hasNext();) {
                    contactfreigabe[k] = (contactfreigabe) it2.next();
                    k++;
                }
                tx.commit();
                HibernateUtil.closeSession();           
            } catch( HibernateException ex ) {
                contactfreigabe[0]=new contactfreigabe();
                contactfreigabe[0].setComment("Error: "+ex);   
            } catch ( Exception ex2 ){
                contactfreigabe[0]=new contactfreigabe();
                contactfreigabe[0].setComment("Error: "+ex2);  
            }
        } else {
            contactfreigabe[0]=new contactfreigabe();
            contactfreigabe[0].setComment("Error: Keine Berechtigung");    
        }
        return contactfreigabe;
    }

    
    public contactfreigabe getFreigabeDecrByID(int FREIGABE_ID){
        contactfreigabe contactfreigabe = new contactfreigabe();
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();    
            Query query = session.createQuery("select c from contactfreigabe as c where c.FREIGABE_ID = :FREIGABE_ID");
            query.setInteger("FREIGABE_ID", FREIGABE_ID);
            for (Iterator it2 = query.iterate(); it2.hasNext();) {
                contactfreigabe = (contactfreigabe) it2.next();
            }
            tx.commit();
            HibernateUtil.closeSession();           
        } catch( HibernateException ex ) {
            contactfreigabe.setComment("Error: "+ex);   
        } catch ( Exception ex2 ){
            contactfreigabe.setComment("Error: "+ex2);  
        }
        return contactfreigabe;
    }
}
