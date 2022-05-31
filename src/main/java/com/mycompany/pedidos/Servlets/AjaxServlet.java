/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.pedidos.Servlets;

import com.mycompany.pedidos.Servicios.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author usuario
 */
public class AjaxServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if ("comprobarcif".equals(accion)) {
            String cif = request.getParameter("valor");
            UsuarioService userS = new UsuarioService();
            boolean resultado = userS.comprobar(cif, "cif");
            String json = "{\"response\":" + resultado + "}";
            out.print(json);
            out.flush();
        } else if ("comprobarusername".equals(accion)) {
            String username = request.getParameter("valor");
            UsuarioService userS = new UsuarioService();
            boolean resultado = userS.comprobar(username, "username");
            String json = "{\"response\":" + resultado + "}";
            out.print(json);
            out.flush();
        } else if ("crearpedido".equals(accion)) {
            String pedido = request.getParameter("valor");
            String json = "{\"response\":" + pedido + "}";
            out.print(json);
            out.flush();
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

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            /*report an error*/
        }

        try {
            JSONObject jsonObject = HTTP.toJSONObject(jb.toString());
        } catch (JSONException e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = "{\"response\":" + "Pedido creado" + "}";
        out.print(json);
        out.flush();
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
