package org.laredo.sistematienda.service;

import org.laredo.sistematienda.exeptions.ProductoException;
import org.laredo.sistematienda.factory.ProductoFactory;
import org.laredo.sistematienda.model.Producto;
import org.laredo.sistematienda.model.Productos;
import org.laredo.sistematienda.repository.ProductoRepository;

import java.util.Collection;

public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public void agregarProducto(String nombre, String marca, double precioSinIgv) throws ProductoException {
        double precioConIgv = precioSinIgv * 1.18;
        Producto producto = ProductoFactory.createProducto(nombre, marca, precioConIgv);
        productoRepository.registrarProducto(producto);
    }

    public void agregarProducto(Productos productoEnum) throws ProductoException {
        double precioConIgv = productoEnum.getPrecio() * 1.18;
        Producto producto = ProductoFactory.createProducto(
                new Productos(productoEnum.getNombre(), productoEnum.getMarca(), precioConIgv));
        productoRepository.registrarProducto(producto);
    }

    public Producto buscarProducto(String id) throws ProductoException {
        return productoRepository.buscarProducto(id)
                .orElseThrow(() -> new ProductoException("No existe el producto con id: " + id));
    }

    public void eliminarProducto(String id) throws ProductoException {
        if (!productoRepository.eliminarProducto(id)) {
            throw new ProductoException("No existe el producto con id: " + id);
        }
    }

    public Collection<Producto> listarProductos() throws ProductoException {
        return productoRepository.listarProductos();
    }
}