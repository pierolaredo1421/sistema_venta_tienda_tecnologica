package org.laredo.sistematienda.model;

public enum Productos {
    SONY("Sony", "Sony", 120),
    JBL("JBL", "JBL", 135.5),
    LOGITECH("Logitech", "Logitech", 98.75),
    REDRAGON("Redragon", "Redragon", 110);

    private final String nombre;
    private final String marca;
    private final double precio;

    Productos(String nombre, String marca, double precio){
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public String getMarca() { return marca; }
    public double getPrecio() { return precio; }
}