package org.laredo.sistematienda.model;

import java.util.Objects;

public class Producto {
    private String idProducto;
    private String nombre;
    private String marca;
    private double precio;
    private Productos productosRegistrados;

    public Producto(String idProducto, String nombre, String marca, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
    }

    public Producto(String idProducto, Productos productosRegistrados) {
        this.idProducto = idProducto;
        this.productosRegistrados = productosRegistrados;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Productos getProductosRegistrados() {
        return productosRegistrados;
    }

    public void setProductosRegistrados(Productos productosRegistrados) {
        this.productosRegistrados = productosRegistrados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(idProducto, producto.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idProducto);
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre='" + nombre + '\'' + ", marca='" + marca + '\'' + ", precio=" + precio + '}';
    }
}
