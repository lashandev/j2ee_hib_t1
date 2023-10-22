/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import model.Employee;
import model.Login;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.MD5Utils;

/**
 *
 * @author Lashan
 */
public class UserDAO {

    public void save(String username, String password, Employee employee) {
       Session session = null;
        Transaction tr = null;
        try {
            Login login = new Login();
            login.setEmployee(employee);
            
           String passwordEncrypt = MD5Utils.passwordEncrypt(password);
            
            login.setUsername(username);
            login.setPassword(passwordEncrypt);
            login.setIsactive(Boolean.TRUE);
            session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();
            session.saveOrUpdate(login);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }
    
    public Login login(String username, String password){
        
        
        Session session = ConnectionBuilder.hibSession();
        Criteria cr = session.createCriteria(Login.class);
        cr.add(Restrictions.eq("username", username));
        String passwordEncrypt = MD5Utils.passwordEncrypt(password);
        cr.add(Restrictions.eq("password", passwordEncrypt));
        
        Login login = (Login) cr.uniqueResult();
        
        return login;
    }
    
}
