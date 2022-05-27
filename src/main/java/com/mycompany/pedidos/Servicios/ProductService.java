/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pedidos.Servicios;

import com.mycompany.pedidos.Config.Conexion;
import com.mycompany.pedidos.Models.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class ProductService {

    Connection conn = null;

    public ProductService() {
        Conexion conexion = new Conexion();
        conn = conexion.getConnection();
    }

    public List<Producto> listadoProductos() {
        List<Producto> productos = new ArrayList();
        Statement stm=null;
        String sql = "select * from producto";
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdproducto(rs.getInt("idproducto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                productos.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return productos;
    }
}
