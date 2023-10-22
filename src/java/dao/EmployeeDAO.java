/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Lashan
 */
public class EmployeeDAO {

    public List<Employee> search() {
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Employee.class);
        List<Employee> list = criteria.list();

        return list;
    }

    public Employee search(int id) {
        Employee employee = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq("id", id));
        if (criteria.uniqueResult() != null) {
            employee = (Employee) criteria.uniqueResult();
        }
        return employee;
    }

}
