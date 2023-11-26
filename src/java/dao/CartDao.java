/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import dto.SessionCart;
import dto.SessionCartItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import model.Cart;
import model.Cartitem;
import model.Employee;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.Base64Image;

/**
 *
 * @author Lashan
 */
public class CartDao {

    List<SessionCartItem> items = null;

    public void save(Cart item) {
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

    public Cart getActiveCartByEmployee(Employee employee) {

        Session session = ConnectionBuilder.hibSession();
        String sql = "SELECT * FROM Cart WHERE employee = :employee_id AND isActive='1' AND cartstatus='1'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Cart.class);
        query.setParameter("employee_id", employee.getId());
        List list = query.list();
        return (list != null && !list.isEmpty()) ? (Cart) list.get(0) : null;
    }

    public SessionCart getCart(int employeeID) {
        SessionCart sessionCart = null;

        Session session = ConnectionBuilder.hibSession();
        Cart cart = getActiveCartByEmployee(new Employee(employeeID));
        if (cart == null) {
            return null;
        }
        sessionCart = new SessionCart();
        sessionCart.setIsActive(cart.getIsActive());
        sessionCart.setTotal(cart.getTotal());

        items = new ArrayList<>();
        Set<Cartitem> cartitems = cart.getCartitems();
        cartitems.stream().forEach(cartitem -> {
            SessionCartItem sessionCartItem = new SessionCartItem();
            sessionCartItem.setItem(cartitem.getItem());
            sessionCartItem.setQty(cartitem.getQty());
            sessionCartItem.setTotal(cartitem.getTotal());
            sessionCartItem.setUnitprice(cartitem.getUnitprice());
            try {
                String base64Image = Base64Image.convertItem(cartitem.getItem());
                sessionCartItem.setImage(base64Image);
            } catch (IOException e) {
            }
            items.add(sessionCartItem);

        });
        sessionCart.setItemList(items);
        return sessionCart;
    }
}
