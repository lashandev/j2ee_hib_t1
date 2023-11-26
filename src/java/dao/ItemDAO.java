/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.Item;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Lashan
 */
public class ItemDAO {
    
    public void save(Item item) {
        Session session = null;
        Transaction tr = null;
        try {
            session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();
            session.saveOrUpdate(item);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }

    }
    
    public List<Item> searchAll() {
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Item.class);
        List<Item> list = criteria.list();
        return list;
    }
    
    public List<Item> searchActiveItems(int first) {
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Item.class);
        criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
        criteria.addOrder(Order.asc("sortkey"));
        criteria.setFirstResult(first);
        criteria.setMaxResults(6);
        List<Item> list = criteria.list();
        return list;
    }
    
    public int searchActiveCount() {
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Item.class);
        criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
        List<Item> list = criteria.list();
        return list.size();
    }
    
    public Item search(String id) {
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Item.class);
        criteria.add(Restrictions.eq("id", id));        
        return (Item) criteria.uniqueResult();
    }
    
}
