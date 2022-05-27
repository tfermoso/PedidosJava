/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.pedidos.Servlets;

import com.mycompany.pedidos.Config.Mail;
import com.mycompany.pedidos.Models.Usuario;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
@WebServlet(name = "PedidosController", urlPatterns = {"/gestion"})
public class PedidosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Mail.sendMail();
        Usuario user = (Usuario) request.getSession().getAttribute("userSession");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/");
        } else {

            request.setAttribute("user", user);
            RequestDispatcher dispatcher = null;
            if (request.getParameter("lista") != null) {
                dispatcher = request.getRequestDispatcher("Views/Gestion/listapedidos.jsp");
            } else if (request.getParameter("id") != null) {
                dispatcher = request.getRequestDispatcher("Views/Gestion/editarpedido.jsp");
            } else {
                dispatcher = request.getRequestDispatcher("Views/Gestion/index.jsp");

            }
            dispatcher.forward(request, response);
        }
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
