/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CartDao;
import dao.CartItemDao;
import dao.ItemDAO;
import dao.UserDAO;
import dto.SessionCart;
import dto.SessionCartItem;
import dto.UserData;
import java.util.Date;
import java.util.List;
import model.Cart;
import model.Cartitem;
import model.Cartstatus;
import model.Employee;
import model.Item;

/**
 *
 * @author Lashan
 */
public class SessionCartToDB {

    public static void convert(SessionCart sescart, UserData userdata) {
        UserDAO userDAO = new UserDAO();
        Employee employeeByUsername = userDAO
                .getEmployeeByUsername(userdata.getUsername());

        CartDao cartDao = new CartDao();
        Cart cart = cartDao.getActiveCartByEmployee(employeeByUsername);

        if (cart == null) {
            cart = new Cart();
            cart.setCartstatus(new Cartstatus(1));
            cart.setCreatedatetime(new Date());
            cart.setIsActive(Boolean.TRUE);
            cart.setCreateduser(userdata.getUsername());
            cart.setEmployee(employeeByUsername);
        }

        cart.setLastupdatedatetime(new Date());
        cart.setLastupdateuser(userdata.getUsername());
        cartDao.save(cart);

        List<SessionCartItem> list = sescart.getItemList();
        for (SessionCartItem sessionCartItem : list) {

            ItemDAO itemDAO = new ItemDAO();
            Item item = itemDAO.search(sessionCartItem.getItem().getId());

            CartItemDao cartItemDao = new CartItemDao();
            Cartitem cartitem =cartItemDao.get(cart.getId(), item.getId());
            if (cartitem == null) {
                 cartitem = new Cartitem();
                cartitem.setCart(cart);
                cartitem.setItem(sessionCartItem.getItem());
                cartitem.setQty(sessionCartItem.getQty());
                cartitem.setUnitprice(sessionCartItem.getUnitprice());
                cartitem.setTotal(sessionCartItem.getTotal());
                
            } else {
                cartitem.setQty(cartitem.getQty()+sessionCartItem.getQty());
                cartitem.setTotal(cartitem.getTotal()+sessionCartItem.getTotal());
            }
            cartItemDao.save(cartitem);
        }
    }

}
