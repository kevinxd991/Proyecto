package com.example.proyecto;

public class Usuario {
  private String Nombre;
  private String contraseña;
  private String Apellido;

    public Usuario(String nombre, String contraseña, String apellido) {
        Nombre = nombre;
        this.contraseña = contraseña;
        Apellido = apellido;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }
}
