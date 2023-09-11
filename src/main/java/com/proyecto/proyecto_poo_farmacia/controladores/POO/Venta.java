package com.proyecto.proyecto_poo_farmacia.controladores.POO;

public class Venta {
    private Integer ventaID;
    private String fechaVenta;
    private String cajero;
    private String producto;
    private Integer cantidad;
    private Double subtotal;

    public Venta(Integer ventaID, String fechaVenta, String cajero, String producto, Integer cantidad, Double subtotal) {
        this.ventaID = ventaID;
        this.fechaVenta = fechaVenta;
        this.cajero = cajero;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public Integer getVentaID() {
        return ventaID;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public String getCajero() {
        return cajero;
    }

    public String getProducto() {
        return producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }
}
