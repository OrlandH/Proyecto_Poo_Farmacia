package com.proyecto.proyecto_poo_farmacia.controladores.POO;

public class Login_class_user {
    private String Nombre;
    private String Contrasena;
    private String tipo;

    public Login_class_user(String nombre, String contrasena, String tipo) {
        Nombre = nombre;
        Contrasena = contrasena;
        this.tipo = tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public String setNombre(String nombre) {
        Nombre = nombre;
        return nombre;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public String setContrasena(String contrasena) {
        Contrasena = contrasena;
        return contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public String setTipo(String tipo) {
        this.tipo = tipo;
        return tipo;
    }
}
