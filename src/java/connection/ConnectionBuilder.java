/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Lashan
 */

public class ConnectionBuilder {
    private static Session session;
    
    public static Session hibSession(){
        try {
            session = connection.NewHibernateUtil.getSessionFactory().getCurrentSession();
        } catch (HibernateException ex) {
            session = connection.NewHibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }
    
    
}
