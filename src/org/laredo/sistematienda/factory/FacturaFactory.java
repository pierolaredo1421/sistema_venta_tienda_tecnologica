package org.laredo.sistematienda.factory;

import org.laredo.sistematienda.model.Factura;
import org.laredo.sistematienda.model.Producto;
import org.laredo.sistematienda.model.Usuario;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FacturaFactory {
    private static final AtomicInteger generadorId = new AtomicInteger(26457);

    public static Factura facturacion(Usuario usuario, List<Producto> productos) {
        int id = generadorId.getAndIncrement();
        return new Factura(id, usuario, productos);
    }
 }
