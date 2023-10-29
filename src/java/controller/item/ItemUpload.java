/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.item;

import dao.ItemDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Item;

/**
 *
 * @author Lashan
 */
@WebServlet(name = "ItemUpload", urlPatterns = {"/ItemUpload"})
public class ItemUpload extends HttpServlet {

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
        try (PrintWriter outx = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String name = request.getParameter("itemname");
            String status = request.getParameter("itemstatus");
            
            ItemDAO itemDAO = new ItemDAO();
            List<Item> searchAll = itemDAO.searchAll();
            String id = "IT"+ (searchAll.size()+1);

            Item item = new Item();
            item.setId(id);
            item.setName(name);
            item.setIsactive((status.trim().equals("on")?Boolean.TRUE:Boolean.FALSE));
            item.setSortkey((searchAll.size()+1));
            
            String uploadPath = "H:\\Project_Dir\\jiat_app\\ItemSet";
            File file = new File(uploadPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            Part part = request.getPart("file");
            String orginal = getFileName(part);

// Extract the extension from the file name
            String extension = ".jpg";
            int index = orginal.lastIndexOf('.');
            if (index > 0) {
                extension = orginal.substring(index + 1);
            }
            String fileName = "ITEM" + id + "." + extension;
            String filepath = uploadPath + File.separator + fileName;
            try (OutputStream out = new FileOutputStream(new File(filepath));
                    InputStream in = part.getInputStream()) {
                int read;
                final byte[] bytes = new byte[1024];
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                item.setImgurl(filepath);
                
                itemDAO.save(item);
            } catch (Exception e) {
                e.printStackTrace();
            }

            response.sendRedirect("ItemUploader.jsp");
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

    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName
                        .substring(fileName.lastIndexOf('/') + 1)
                        .substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
