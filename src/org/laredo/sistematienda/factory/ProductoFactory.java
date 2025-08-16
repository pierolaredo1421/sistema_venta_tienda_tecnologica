package org.laredo.sistematienda.factory;

import org.laredo.sistematienda.model.Producto;
import org.laredo.sistematienda.model.Productos;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductoFactory {
    private static final AtomicInteger SECUENCIAL = new AtomicInteger(20065);
    private static final Random RANDOM = new Random();

    private ProductoFactory() {}

    public static Producto createProducto(String nombre, String marca, double precio) {
        String id = generarId();
        return new Producto(id, nombre, marca, precio);
    }

    public static Producto createProducto(Productos productoEnum) {
        String id = generarId();
        return new Producto(id, productoEnum);
    }

    private static String generarId() {
        int parteSecuencial = SECUENCIAL.getAndIncrement();
        int parteAleatoria = RANDOM.nextInt(9000) + 1000;
        return parteSecuencial + "" + parteAleatoria;
    }
}