package org.laredo.sistematienda.factory;

import org.laredo.sistematienda.model.Producto;
import org.laredo.sistematienda.model.Productos;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductoFactory {
    private static final AtomicInteger secuencial = new AtomicInteger(20065);
    private static final Random aleatoria = new Random();

    public static Producto createProducto(String nombre, String marca, double precio) {
        int parteSecuencial = secuencial.getAndIncrement();
        int parteAleatoria = aleatoria.nextInt(9000) + 1000;
        String id = parteSecuencial + "" + parteAleatoria;
        return new Producto(id, nombre, marca, precio);
    }

    public static Producto createProducto(Productos producto) {
        int parteSecuencial = secuencial.getAndIncrement();
        int parteAleatoria = aleatoria.nextInt(9000) + 1000;
        String id = parteSecuencial + "" + parteAleatoria;
        return new Producto(id, producto);
    }
}
