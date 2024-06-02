package com.example.prueba.modelo;

import java.io.Serializable;

public class Inquilino implements Serializable {

    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;
    private String lugarTrabajo;
    private String nombreGarante;
    private String apellidoGarante;
    private String dniGarante;
    private String telefonoGarante;
    private String direccionGarante;

    public Inquilino() {
    }

    public Inquilino(String nombre, String apellido, String dni, String telefono, String direccion, String email, String lugarTrabajo, String nombreGarante, String apellidoGarante, String dniGarante, String telefonoGarante, String direccionGarante) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.lugarTrabajo = lugarTrabajo;
        this.nombreGarante = nombreGarante;
        this.apellidoGarante = apellidoGarante;
        this.dniGarante = dniGarante;
        this.telefonoGarante = telefonoGarante;
        this.direccionGarante = direccionGarante;
    }

    public Inquilino(String direccion) {
        this.direccion=direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getNombreGarante() {
        return nombreGarante;
    }

    public void setNombreGarante(String nombreGarante) {
        this.nombreGarante = nombreGarante;
    }

    public String getApellidoGarante() {
        return apellidoGarante;
    }

    public void setApellidoGarante(String apellidoGarante) {
        this.apellidoGarante = apellidoGarante;
    }

    public String getDniGarante() {
        return dniGarante;
    }

    public void setDniGarante(String dniGarante) {
        this.dniGarante = dniGarante;
    }

    public String getTelefonoGarante() {
        return telefonoGarante;
    }

    public void setTelefonoGarante(String telefonoGarante) {
        this.telefonoGarante = telefonoGarante;
    }

    public String getDireccionGarante() {
        return direccionGarante;
    }

    public void setDireccionGarante(String direccionGarante) {
        this.direccionGarante = direccionGarante;
    }
}
