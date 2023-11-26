/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import dto.SessionCartItem;
import java.util.List;
import model.Cart;
import model.Cartitem;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lashan
 */
public class CartItemDao {
    public void save(Cartitem item) {
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
    
    public Cartitem get(int cartid, String itemid){
        Session session = ConnectionBuilder.hibSession();
        String sql = "SELECT * FROM CartItem WHERE cart = :cartid AND item = :itemid";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Cart.class);
        query.setParameter("cartid", cartid);
        query.setParameter("itemid", itemid);
        List list = query.list();
        return (list != null && !list.isEmpty()) ? (Cartitem) list.get(0) : null;
    }
}
