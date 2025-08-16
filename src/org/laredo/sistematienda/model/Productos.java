package org.laredo.sistematienda.model;

public enum Productos {
    Sony (120),
    JBL(135.5),
    Logitech(98.75),
    Redragon(110);

    double precio;

    Productos(double precio){
        this.precio = precio;
    }

    public double getPrecio(){
        return precio;
    }
}
