/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pedidos.Servicios;

import com.mycompany.pedidos.Config.Conexion;
import com.mycompany.pedidos.Models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class UsuarioService {
    Connection conn=null;

    public UsuarioService() {
        Conexion conexion=new Conexion();
        conn=conexion.getConnection();    
    }
    
    public Usuario login(String username,String pass)
   {
        String sql="select * from cliente where username=? and password=?";
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            stm=conn.prepareStatement(sql);
            stm.setString(1,username);
            stm.setString(2,pass);
            rs=stm.executeQuery();
            if(rs.next()){
                Usuario usuario=new Usuario();
                usuario.setIdcliente(rs.getInt("idcliente"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCif(rs.getString("cif"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefono(rs.getString("telefono"));
                return usuario;
            }else{
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
          if(stm!=null){
              try {
                  stm.close();
              } catch (SQLException ex) {
                  Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
              }  
          } 
          if(conn!=null){
              try {
                  conn.close();
              } catch (SQLException ex) {
                  Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }
    }
    
    
    public Boolean registrar(String nombre,String cif,String telefono,String email,String username,String password){
        String sql="insert into cliente (nombre,cif,telefono,email,username,password) value (?,?,?,?,?,?)";
        PreparedStatement stm=null;
        try {
            stm=conn.prepareStatement(sql);
            stm.setString(1,nombre);
            stm.setString(2,cif);
            stm.setString(3,telefono);
            stm.setString(4,email);
            stm.setString(5,username);
           
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return true;
    }
    
    
}
