package org.laredo.sistematienda;

import org.laredo.sistematienda.exeptions.*;
import org.laredo.sistematienda.model.Factura;
import org.laredo.sistematienda.model.Producto;
import org.laredo.sistematienda.model.Productos;
import org.laredo.sistematienda.model.Usuario;
import org.laredo.sistematienda.repository.FacturaRepository;
import org.laredo.sistematienda.repository.ProductoRepository;
import org.laredo.sistematienda.repository.UsuarioRepository;
import org.laredo.sistematienda.service.FacturaService;
import org.laredo.sistematienda.service.ProductoService;
import org.laredo.sistematienda.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pruebas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepo);
        ProductoRepository productoRepo = new ProductoRepository();
        ProductoService productoService = new ProductoService(productoRepo);
        FacturaRepository facturaRepo = new FacturaRepository();
        FacturaService facturaService = new FacturaService(facturaRepo);

        try {
            Usuario usuario = new Usuario(73090125, "Piero");
            usuarioService.crearUsuario(usuario);

            productoService.agregarProducto("iPhone 15", "Apple", 1000);
            productoService.agregarProducto("Kumara K552", "Redragon", 100);
            productoService.agregarProducto(Productos.LOGITECH);
            productoService.agregarProducto(Productos.JBL);
            productoService.agregarProducto(Productos.SONY);
            productoService.agregarProducto(Productos.REDRAGON);

            List<Producto> listaProductos = new ArrayList<>();
            String id;
            do {
                System.out.println(productoService.listarProductos());
                System.out.print("Ingrese el id del producto (0 para terminar): ");
                id = sc.nextLine();
                if (!id.equals("0")) {
                    Producto producto = productoService.buscarProducto(id);
                    listaProductos.add(producto);
                }
            } while (!id.equals("0"));

            if (!listaProductos.isEmpty()) {
                facturaService.facturar(usuario, listaProductos);
                // Aquí deberías buscar él id real de la factura creada; 26457 es ejemplo.
                Factura factura = facturaService.buscarFactura(26457);
                System.out.println(factura);
            } else {
                System.out.println("No se ingresaron productos para facturar.");
            }

            System.out.println("---------------------------");
        } catch (ProductoException | UsuarioException | FacturaExceptions e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}