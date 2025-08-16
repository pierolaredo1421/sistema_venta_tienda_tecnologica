package org.laredo.sistematienda.controller;

import org.laredo.sistematienda.exeptions.FacturaExceptions;
import org.laredo.sistematienda.exeptions.ProductoException;
import org.laredo.sistematienda.exeptions.UsuarioException;
import org.laredo.sistematienda.model.Factura;
import org.laredo.sistematienda.model.Producto;
import org.laredo.sistematienda.model.Usuario;
import org.laredo.sistematienda.service.FacturaService;
import org.laredo.sistematienda.service.ProductoService;
import org.laredo.sistematienda.service.UsuarioService;

import java.util.Collection;
import java.util.List;

public class SistemaController {
    private final UsuarioService usuarioService;
    private final ProductoService productoService;
    private final FacturaService facturaService;

    public SistemaController(UsuarioService usuarioService, ProductoService productoService, FacturaService facturaService) {
        this.usuarioService = usuarioService;
        this.productoService = productoService;
        this.facturaService = facturaService;
    }

    //USUARIO
    public void registrarUsuario(Usuario usuario) throws UsuarioException {
        usuarioService.crearUsuario(usuario);
    }

    public Usuario encontrarUsuario(int dni) throws UsuarioException {
        return usuarioService.buscarUsuario(dni);
    }

    public Collection<Usuario> usuariosRegistrados() throws UsuarioException {
        return usuarioService.listarUsuarios();
    }

    //PRODUCTO
    public void registrarProducto(String nombre, String marca, double precioSinIgv) throws ProductoException{
        productoService.agregarProducto(nombre, marca, precioSinIgv);
    }

    public Producto encontrarProducto(String nombre) throws ProductoException {
        return productoService.buscarProducto(nombre);
    }

    public void eliminarProducto(String idProducto) throws ProductoException {
        productoService.eliminarProducto(idProducto);
    }

    public Collection<Producto> productosRegistrados() throws ProductoException {
        return productoService.listarProductos();
    }

    //FACTURA
    public void registrarFactura(Usuario usuario, List<Producto> productos) throws FacturaExceptions {
        facturaService.registrarFactura(usuario, productos);
    }

    public Factura encontrarFactura(int idFactura) throws FacturaExceptions {
        return facturaService.buscarFactura(idFactura);
    }

    public Collection<Factura> facturasRegistrados() throws FacturaExceptions {
        return facturaService.listarFacturas();
    }
}






