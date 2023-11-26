/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.item;

import dao.ItemDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Item;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Lashan
 */
@WebServlet(name = "ItemLoader", urlPatterns = {"/ItemLoader"})
public class ItemLoader extends HttpServlet {

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
            String fr = request.getParameter("fr");
            int first_result = 0;
            if (fr != null && !fr.trim().equals("")) {
                first_result = Integer.parseInt(fr);
                first_result *= 6;
            }
            ItemDAO itemDAO = new ItemDAO();
            List<Item> searchActiveItems = itemDAO.searchActiveItems(first_result);
            String content = "<section style=\"background-color: #eee;\">\n"
                    + "            <div class=\"text-center container py-5\">";

            int rows = 0;

            for (Item item : searchActiveItems) {
                ++rows;
                String path = item.getImgurl().replace("\\", "/");
                byte[] fileContent = FileUtils.readFileToByteArray(new File(path));
                String encodedString = Base64.getEncoder().encodeToString(fileContent);
                String imageBase64 = "data:image/jpeg;base64," + encodedString;
                if (rows % 3 == 1) {
                    content += "<div class=\"row\">\n";
                }
                content += "<div class=\"col-sm-4\">\n"
                        + "                        <div class=\"card\">\n"
                        + "                            <div class=\"bg-image hover-zoom ripple ripple-surface ripple-surface-light\"\n"
                        + "                                 data-mdb-ripple-color=\"light\">\n"
                        + "                                <img src=\"" + imageBase64 + "\"\n"
                        + "                                     class=\"w-100\" />\n"
                        + "                                <a href=\"#!\">\n"
                        + "                                    <div class=\"mask\">\n"
                        + "                                        <div class=\"d-flex justify-content-start align-items-end h-100\">\n"
                        + "                                            <h5><span class=\"badge bg-primary ms-2\">New</span></h5>\n"
                        + "                                        </div>\n"
                        + "                                    </div>\n"
                        + "                                    <div class=\"hover-overlay\">\n"
                        + "                                        <div class=\"mask\" style=\"background-color: rgba(251, 251, 251, 0.15);\"></div>\n"
                        + "                                    </div>\n"
                        + "                                </a>\n"
                        + "                            </div>\n"
                        + "                            <div class=\"card-body\">\n"
                        + "                                    <h5 class=\"card-title mb-3\">" + item.getName() + "</h5>\n"
                        + "                                <a href=\"AddToCart?item=" + item.getId() + "\" class=\"text-reset\"><button class=\"btn btn-danger\">\n"
                        + "                                    Add to Cart\n"
                        + "                                </button></a>\n"
                        + "                                <h6 class=\"mb-3\">LKR " + item.getPrice() + "</h6>\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                    </div>";
                if (rows % 3 == 0) {
                    content += "</div>\n";
                }
            }

            if (rows % 3 == 1 || rows % 3 == 2) {
                content += "</div>\n";
            }

            content += "</div>\n"
                    + "        </section> ";
            out.print(content);
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
