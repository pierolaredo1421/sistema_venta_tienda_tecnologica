package org.laredo.sistematienda.view;

import org.laredo.sistematienda.controller.SistemaController;
import org.laredo.sistematienda.repository.FacturaRepository;
import org.laredo.sistematienda.repository.ProductoRepository;
import org.laredo.sistematienda.repository.UsuarioRepository;
import org.laredo.sistematienda.service.FacturaService;
import org.laredo.sistematienda.service.ProductoService;
import org.laredo.sistematienda.service.UsuarioService;

public class Main {
    public static void main(String[] args) {
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        ProductoRepository productoRepo = new ProductoRepository();
        FacturaRepository facturaRepo = new FacturaRepository();

        UsuarioService usuarioService = new UsuarioService(usuarioRepo);
        ProductoService productoService = new ProductoService(productoRepo);
        FacturaService facturaService = new FacturaService(facturaRepo);

        SistemaController controller = new SistemaController(
                usuarioService, productoService, facturaService
        );

        try {
            productoService.agregarProducto("iPhone 15", "Apple", 1000);
            productoService.agregarProducto("Kumara K552", "Redragon", 100);
            productoService.agregarProducto(org.laredo.sistematienda.model.Productos.LOGITECH);
            productoService.agregarProducto(org.laredo.sistematienda.model.Productos.JBL);
            productoService.agregarProducto(org.laredo.sistematienda.model.Productos.SONY);
            productoService.agregarProducto(org.laredo.sistematienda.model.Productos.REDRAGON);
        } catch (Exception ignored) {}

        SistemaGestationView view = new SistemaGestationView(controller);
        view.mostrarMenuPrincipal();
    }
}