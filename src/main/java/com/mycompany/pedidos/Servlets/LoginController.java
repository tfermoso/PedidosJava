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
import jakarta.servlet.http.Cookie;
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

        if (request.getParameter("logout") != null) {
            request.getSession().invalidate();
            Cookie ck = new Cookie("JSESSIONID", "");//deleting value of cookie  
            ck.setMaxAge(0);//changing the maximum age to 0 seconds  
            response.addCookie(ck);//adding cookie in the response 
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            //UsuarioService usuarioService = new UsuarioService();
            //usuarioService.registrar("Pedro", "A23454666", "986767676", "juan@gmail.com", "pedro", "Fter.45!34F");
            request.getServletContext().setAttribute("error", "");
            RequestDispatcher dispatcher = null;

            if (request.getParameter("registrarse") != null) {
                dispatcher = request.getRequestDispatcher("Views/register.jsp");
                dispatcher.forward(request, response);
            } else {
                Usuario usuario = (Usuario) request.getSession().getAttribute("userSession");
                if (usuario != null) {
                    response.sendRedirect(request.getContextPath() + "/gestion");
                } else {
                    String username = "";
                    if (request.getCookies() != null) {
                        for (int i = 0; i < request.getCookies().length; i++) {
                            Cookie cooky = request.getCookies()[i];
                            if (cooky.getName().equals("username")) {
                                username = cooky.getValue();
                            }
                        }
                    }
                    request.setAttribute("username", username);
                    dispatcher = request.getRequestDispatcher("Views/login.jsp");
                    dispatcher.forward(request, response);
                }
            }
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
        String accion = request.getParameter("accion");
        UsuarioService userS = new UsuarioService();
        if ("Sign in".equals(accion)) {
            String username = request.getParameter("username");
            String pass = request.getParameter("password");
            if (request.getParameter("remember") == null) {
                Cookie ck = new Cookie("username", "");//deleting value of cookie  
                ck.setMaxAge(0);//changing the maximum age to 0 seconds  
                response.addCookie(ck);//adding cookie in the response 
            } else {
                Cookie ck = new Cookie("username", username);//creating cookie object 
                ck.setMaxAge(60 * 5);
                response.addCookie(ck);
            }
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
            String nombre = request.getParameter("nombre");
            String cif = request.getParameter("cif");
            String telefono = request.getParameter("telefono");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String pass = request.getParameter("password");
            if (userS.registrar(nombre, cif, telefono, email, username, pass)) {
                userS = new UsuarioService();
                Usuario user = userS.login(username, pass);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("userSession", user);
                    response.sendRedirect(request.getContextPath() + "/gestion");
                } else {
                    request.getServletContext().setAttribute("error", "Error en el login");
                }
            } else {
                request.getServletContext().setAttribute("error", "Error al crear el cliente, contacte con el administrador");
                RequestDispatcher dispatcher = request.getRequestDispatcher("Views/register.jsp");
                dispatcher.forward(request, response);
            }

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
