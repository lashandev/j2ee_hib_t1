/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Department;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lashan
 */
public class DepartmentDAO {
    
    public Department searchById(String id){
        Department department = null;
        
        Session session = connection.NewHibernateUtil.getSessionFactory().openSession();
        department = (Department) session.load(Department.class, id);
        
        return department;
    }

    public void save(Department department) {
        Session session = null;
        Transaction tr = null;
        try {
            session = connection.NewHibernateUtil.getSessionFactory().openSession();
            tr = session.beginTransaction();
            session.save(department);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    
    
    }

    public List<Department> search() {
        Session session = connection.NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Department");
        return query.list();
    }
    
}
