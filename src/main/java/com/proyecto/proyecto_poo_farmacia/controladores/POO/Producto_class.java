package com.proyecto.proyecto_poo_farmacia.controladores.POO;

public class Producto_class {
    private String ID;
    private String Producto;
    private String PVP;
    private String Stock;

    public Producto_class(String ID_producto, String nombre_producto, String precio_prod, String stock_prod) {
       this.ID = ID_producto;
       Producto = nombre_producto;
        PVP = precio_prod;
        Stock = stock_prod;
      }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
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
}
