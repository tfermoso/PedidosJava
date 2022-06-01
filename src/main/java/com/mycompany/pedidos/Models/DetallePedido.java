/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pedidos.Models;

/**
 *
 * @author usuario
 */
public class DetallePedido {
    private int iddetalle_pedido;
    private int idpedido;
    private int idproducto;
    private int cantidad;
    private double precio;

    public DetallePedido() {
    }

    public DetallePedido(int iddetalle_pedido, int idpedido, int idproducto, int cantidad, double precio) {
        this.iddetalle_pedido = iddetalle_pedido;
        this.idpedido = idpedido;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetallePedido(int idproducto, int cantidad, double precio) {
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    

    public int getIddetalle_pedido() {
        return iddetalle_pedido;
    }

    public void setIddetalle_pedido(int iddetalle_pedido) {
        this.iddetalle_pedido = iddetalle_pedido;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}
