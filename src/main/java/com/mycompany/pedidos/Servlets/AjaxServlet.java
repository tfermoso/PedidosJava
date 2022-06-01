/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.pedidos.Servlets;


import com.mycompany.pedidos.Models.DetallePedido;
import com.mycompany.pedidos.Models.Pedido;
import com.mycompany.pedidos.Models.Usuario;
import com.mycompany.pedidos.Servicios.PedidoService;
import com.mycompany.pedidos.Servicios.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
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
        
        try {
            //Gson gson=new Gson();
            BufferedReader rd=request.getReader();
            StringBuffer sb=new StringBuffer();
            String line=null;
            while((line=rd.readLine())!=null){
                sb.append(line);
            }
            //JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
            JSONObject jsonRequest=new JSONObject(sb.toString());
            Usuario user = (Usuario) request.getSession().getAttribute("userSession");

            Pedido pedido=convertirPedido(jsonRequest,user);
            PedidoService pedidoService=new PedidoService();
            String resp="";
            if(pedidoService.crearPedido(pedido)){
                resp="Pedido creado correctamente";
            }else{
                resp="Error al crear el pedido";
            }
            PrintWriter out = response.getWriter();
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = "{\"response\":\"" + resp + "\"}";
            out.print(json);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
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

    private Pedido convertirPedido(JSONObject objPedido,Usuario user){
        
        Pedido pedido=new Pedido();
        pedido.setIdCliente(user.getIdcliente());
        Date fecha_entrega = Date.valueOf(objPedido.getString("fecha"));
        pedido.setFecha_entrega(fecha_entrega);
        JSONArray lineas= objPedido.getJSONArray("lineasPedido");
        for (int i = 0; i < lineas.length(); i++) {
            JSONObject detalle = lineas.getJSONObject(i);
            DetallePedido detallePedido=new DetallePedido();
            detallePedido.setIdproducto(detalle.getInt("idArticulo"));
            detallePedido.setCantidad(detalle.getInt("cantidad"));
            detallePedido.setPrecio(detalle.getDouble("precio"));
            pedido.detalle.add(detallePedido);
        }
       
        return pedido;
    }
}
