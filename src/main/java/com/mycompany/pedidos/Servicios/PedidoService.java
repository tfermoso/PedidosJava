/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pedidos.Servicios;

import com.mycompany.pedidos.Config.Conexion;
import com.mycompany.pedidos.Models.DetallePedido;
import com.mycompany.pedidos.Models.Pedido;
import com.mysql.cj.protocol.Resultset;
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
public class PedidoService {

    Connection conn = null;

    public PedidoService() {
        Conexion conexion = new Conexion();
        conn = conexion.getConnection();
    }

    public boolean crearPedido(Pedido pedido) {
        String sqlPedido = "insert into pedido (idcliente,fecha_entrega) values (?,?)";
        String sqlDetalle = "insert into detalle_pedido (idpedido,idproducto,cantidad,precio) values (?,?,?,?)";
        PreparedStatement stm,stmDetalle;
        int idpedido;
        try {
            // commit all or rollback all, if any errors
            conn.setAutoCommit(false); // default true
            stm = conn.prepareStatement(sqlPedido, PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, pedido.getIdCliente());
            stm.setDate(2, pedido.getFecha_entrega());
            int filas=stm.executeUpdate();
            if(filas>0){
                ResultSet idGenerado=stm.getGeneratedKeys();
                if(idGenerado.next()){
                    idpedido=idGenerado.getInt(1);
                    stmDetalle=conn.prepareStatement(sqlDetalle);
                    for (int i = 0; i < pedido.detalle.size(); i++) {
                        DetallePedido detalle = pedido.detalle.get(i);                        
                        stmDetalle.setInt(1, idpedido);
                        stmDetalle.setInt(2, detalle.getIdproducto());
                        stmDetalle.setInt(3, detalle.getCantidad());
                        stmDetalle.setDouble(4, detalle.getPrecio());
                        stmDetalle.addBatch();
                    }
                    int[] rows=stmDetalle.executeBatch();
                                   
                }
                
                   
            }
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
