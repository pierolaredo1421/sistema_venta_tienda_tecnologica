package org.laredo.sistematienda.model;

import java.util.Objects;

public class Usuario {
    private int dni;
    private String nombre;

    public Usuario(int dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return dni == usuario.dni;
    }

    @Override
    public String toString() {
        return "Usuario{" + "dni=" + dni + ", nombre='" + nombre + '\'' + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }
}


