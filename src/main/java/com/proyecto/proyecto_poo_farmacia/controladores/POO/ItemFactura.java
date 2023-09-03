package com.proyecto.proyecto_poo_farmacia.controladores.POO;

public class ItemFactura {
    private String ID;
    private String Productos;
    private String PVP;
    private String Stock;
    private int cantidad;
    private double subtotal;

    public ItemFactura(String ID, String Producto, String PVP, String Stock, int cantidad, double subtotal) {
        this.ID = ID;
        this.Productos = Producto;
        this.PVP = PVP;
        this.Stock = Stock;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProductos() {
        return Productos;
    }

    public void setProductos(String productos) {
        Productos = productos;
    }

    public String getPVP() {
        return PVP;
    }

    public void setPVP(String PVP) {
        this.PVP = PVP;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}