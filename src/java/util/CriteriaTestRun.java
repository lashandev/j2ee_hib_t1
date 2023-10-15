/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import connection.ConnectionBuilder;
import java.util.List;
import model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Lashan
 */
public class CriteriaTestRun {
    public static void main(String[] args) {
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Employee.class);
        List<Employee> list = criteria.list();
        
        for (Employee employee : list) {
            System.out.println(employee.toString());
        }
    }
}
