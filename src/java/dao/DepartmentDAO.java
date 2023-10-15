/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import model.Department;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lashan
 */

public class DepartmentDAO {

    public Department searchById(String id) {
        Department department = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            department = (Department) session.load(Department.class, id);
            System.out.println("Department Found :"+department.getName());
        } catch (org.hibernate.ObjectNotFoundException e) {
            department = null;
        }
        return department;
    }

    public void save(Department department) {
        Session session = null;
        Transaction tr = null;
        try {
            session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();
            session.save(department);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }

    }

    public List<Department> search() {
        Session session = ConnectionBuilder.hibSession();
        Query query = session.createQuery("from Department");
        query.setFirstResult(3);
        query.setMaxResults(5);
        return query.list();
    }

    public void delete(String code) {
        Session session = null;
        Transaction tr = null;
        try {
            session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();
            Department department = (Department) session.load(Department.class, code);
            if (department != null) {
                Query query = session.createQuery("delete from Department where code=:c");
                query.setParameter("c", code);
                int executeUpdate = query.executeUpdate();
                System.out.println(executeUpdate);
                tr.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
