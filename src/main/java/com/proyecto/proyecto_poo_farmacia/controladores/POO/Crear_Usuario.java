package com.proyecto.proyecto_poo_farmacia.controladores.POO;

public class Crear_Usuario {
    private int id_usuario;
    private String Nombre;
    private String Contrasena;
    private String Tipo_usuario;

    public Crear_Usuario(int id_usuario, String nombre, String contrasena, String tipo_usuario) {
        this.id_usuario = id_usuario;
        Nombre = nombre;
        Contrasena = contrasena;
        Tipo_usuario = tipo_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public String getTipo_usuario() {
        return Tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        Tipo_usuario = tipo_usuario;
    }
}
