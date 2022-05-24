/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.pedidos.Servlets;

import com.mycompany.pedidos.Models.Usuario;
import com.mycompany.pedidos.Servicios.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author usuario
 */
public class LoginController extends HttpServlet {

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

        UsuarioService usuarioService = new UsuarioService();
        usuarioService.registrar("Pedro", "A23454666", "986767676", "juan@gmail.com", "pedro", "Fter.45!34F");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Views/login.jsp");
        dispatcher.forward(request, response);
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
        String accion = request.getParameter("accion");
        if ("Sign in".equals(accion)) {
            String username = request.getParameter("username");
            String pass = request.getParameter("password");
            UsuarioService userS = new UsuarioService();
            Usuario user = userS.login(username, pass);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userSession", user);
                response.sendRedirect(request.getContextPath() + "/gestion");
            } else {
                request.getServletContext().setAttribute("error", "Usuario o contraseña incorrecta");
                RequestDispatcher dispatcher = request.getRequestDispatcher("Views/login.jsp");
                dispatcher.forward(request, response);
            }
        } else {

        }
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
