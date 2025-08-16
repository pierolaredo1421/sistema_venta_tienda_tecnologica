package org.laredo.sistematienda.repository;

import org.laredo.sistematienda.model.Producto;
import org.laredo.sistematienda.model.Productos;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductoRepository {
    private Map<String, Producto> productos = new HashMap<>();

    public void registrarProducto(Producto producto) {
        productos.put(producto.getIdProducto(), producto);
    }

    public Optional<Producto> buscarProducto(String idProducto) {
        return Optional.ofNullable(productos.get(idProducto));
    }

    public boolean eliminarProducto(String idProducto) {
        return productos.remove(idProducto) != null;
    }

    public Collection<Producto> listarProductos() {
        return productos.values();
    }
}
