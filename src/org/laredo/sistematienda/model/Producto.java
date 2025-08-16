package org.laredo.sistematienda.model;

import java.util.Objects;

public class Producto {
    private final String idProducto;
    private final String nombre;
    private final String marca;
    private final double precio;
    private final Productos productoEnum;

    // Constructor para productos personalizados
    public Producto(String idProducto, String nombre, String marca, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.productoEnum = null;
    }

    // Constructor para productos registrados por enum
    public Producto(String idProducto, Productos productoEnum) {
        this.idProducto = idProducto;
        this.productoEnum = productoEnum;
        this.nombre = productoEnum.getNombre();
        this.marca = productoEnum.getMarca();
        this.precio = productoEnum.getPrecio();
    }

    public String getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
    public String getMarca() { return marca; }
    public double getPrecio() { return precio; }
    public Productos getProductoEnum() { return productoEnum; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(idProducto, producto.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }

    @Override
    public String toString() {
        return "idProducto='" + idProducto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", precio=" + precio +
                "\n";
    }
}