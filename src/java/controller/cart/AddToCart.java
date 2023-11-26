/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cart;

import dao.CartDao;
import dao.CartItemDao;
import dao.ItemDAO;
import dao.UserDAO;
import dto.SessionCart;
import dto.SessionCartItem;
import dto.UserData;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Cartitem;
import model.Cartstatus;
import model.Employee;
import model.Item;
import org.apache.commons.io.FileUtils;
import util.Base64Image;

/**
 *
 * @author Lashan
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String itemid = request.getParameter("item");
            ItemDAO itemDAO = new ItemDAO();
            Item item = itemDAO.search(itemid);
            String imageBase64 = Base64Image.convertItem(item);

            if (session.getAttribute("userdata") == null) {
                SessionCart sessionCart = null;
                if (session.getAttribute("cart") == null) {
                    sessionCart = new SessionCart();
                } else {
                    sessionCart = (SessionCart) session.getAttribute("cart");
                }

                List<SessionCartItem> itemList = sessionCart.getItemList();

                SessionCartItem cartItem = new SessionCartItem();
                cartItem.setItem(item);
                cartItem.setQty(1);
                cartItem.setUnitprice(item.getPrice());
                cartItem.setTotal(item.getPrice() * 1);

                cartItem.setImage(imageBase64);
                itemList.add(cartItem);

                session.setAttribute("cart", sessionCart);

            } else {

                UserData userdata = (UserData) session.getAttribute("userdata");

                UserDAO userDAO = new UserDAO();
                Employee employeeByUsername = userDAO
                        .getEmployeeByUsername(userdata.getUsername());
                System.out.println("Employee Found " 
                        + employeeByUsername.getId());

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

                Cartitem cartitem = new Cartitem();
                cartitem.setCart(cart);
                cartitem.setItem(item);
                cartitem.setQty(1);
                cartitem.setUnitprice(item.getPrice());
                cartitem.setTotal(item.getPrice() * 1);
                CartItemDao cartItemDao = new CartItemDao();
                cartItemDao.save(cartitem);

            }
            response.sendRedirect("Home.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
