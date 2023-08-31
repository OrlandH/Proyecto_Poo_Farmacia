package com.proyecto.proyecto_poo_farmacia.controladores.POO;

import javafx.beans.property.SimpleStringProperty;

public class Producto_class {
    private String ID_producto;
    private String Nombre_producto;
    private String Precio_prod;
    private String Stock_prod;

  //  public Producto_class(String ID_producto, String nombre_producto, String precio_prod, String stock_prod) {
     //   this.ID_producto = ID_producto;
     //   Nombre_producto = nombre_producto;
    //    Precio_prod = precio_prod;
    //    Stock_prod = stock_prod;
//    }

    public String getID_producto() {
        return ID_producto;
    }

    public void setID_producto(String ID_producto) {
        this.ID_producto = ID_producto;
    }

    public String getNombre_producto() {
        return Nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        Nombre_producto = nombre_producto;
    }

    public String getPrecio_prod() {
        return Precio_prod;
    }

    public void setPrecio_prod(String precio_prod) {
        Precio_prod = precio_prod;
    }

    public String getStock_prod() {
        return Stock_prod;
    }

    public void setStock_prod(String stock_prod) {
        Stock_prod = stock_prod;
    }
}
