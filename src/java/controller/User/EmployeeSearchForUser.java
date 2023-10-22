/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.User;

import dao.EmployeeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;
import model.Login;

/**
 *
 * @author Lashan
 */
@WebServlet(name = "EmployeeSearchForUser", urlPatterns = {"/EmployeeSearchForUser"})
public class EmployeeSearchForUser extends HttpServlet {

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
            EmployeeDAO employeeDAO = new EmployeeDAO();
            List<Employee> employees = employeeDAO.search();
            String content = "";
            for (Employee employee : employees) {
                content += "<tr>";
                content += "<td>"+employee.getEmployeeno()+"</td>";
                content += "<td>"+employee.getFirstname()+"</td>";
                content += "<td>"+employee.getLastname()+"</td>";
                content += "<td>"+employee.getMobileno()+"</td>";
                content += "<td>"+employee.getEmail()+"</td>";
                
                Set<Login> logins = employee.getLogins();
                boolean empty = logins.isEmpty();
//                content += "<td><button class='btn btn-danger' onclick='setUserdata()' >Create User</button></td>";
                if(empty){
                    content += "<td><button class='btn btn-danger' onclick='setUserdata(`"+employee.getId()+"`,`"+employee.getFirstname()+"`,`"+employee.getLastname()+"`)' >Create User</button></td>";
                }else{
                    content += "<td><button class='btn btn-primary' onclick='setUserdata(`"+employee.getId()+"`,`"+employee.getFirstname()+"`,`"+employee.getLastname()+"`)' >Create User</button></td>";
                }
                        
                content += "</tr>";
            }
            
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
