package org.laredo.sistematienda.view;

import org.laredo.sistematienda.controller.SistemaController;
import org.laredo.sistematienda.exeptions.FacturaExceptions;
import org.laredo.sistematienda.exeptions.ProductoException;
import org.laredo.sistematienda.exeptions.UsuarioException;
import org.laredo.sistematienda.model.Factura;
import org.laredo.sistematienda.model.Producto;
import org.laredo.sistematienda.model.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class SistemaGestationView {
    private final SistemaController controller;
    private final Scanner scanner = new Scanner(System.in);

    public SistemaGestationView(SistemaController controller) {
        this.controller = controller;
    }


    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestionar Usuarios");
            System.out.println("2. Gestionar Productos");
            System.out.println("3. Crear Factura");
            System.out.println("4. Listar Facturas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> menuUsuarios();
                case 2 -> menuProductos();
                case 3 -> crearFactura();
                case 4 -> listarFacturas();
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void menuUsuarios() {
        int opcion;
        do {
            System.out.println("\n-- Gestión de Usuarios --");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Buscar usuario por DNI");
            System.out.println("4. Eliminar usuario");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> buscarUsuario();
                case 0 -> {}
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void menuProductos() {
        int opcion;
        do {
            System.out.println("\n-- Gestión de Productos --");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Eliminar producto");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> listarProductos();
                case 3 -> buscarProducto();
                case 4 -> eliminarProducto();
                case 0 -> {}
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrarUsuario() {
        try {
            System.out.print("DNI usuario: ");
            int dni = leerEntero();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            controller.registrarUsuario(new Usuario(dni, nombre));
            System.out.println("Usuario registrado correctamente.");
        } catch (UsuarioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void buscarUsuario() {
        try {
            System.out.print("DNI usuario a buscar: ");
            int dni = leerEntero();
            Usuario usuario = controller.encontrarUsuario(dni);
            System.out.println(usuario);
        } catch (UsuarioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void agregarProducto() {
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Precio: ");
            double precio = leerDouble();
            controller.registrarProducto(nombre, marca, precio);
            System.out.println("Producto agregado correctamente.");
        } catch (ProductoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarProductos() {
        try {
            Collection<Producto> productos = controller.productosRegistrados();
            System.out.println("Productos registrados:");
            productos.forEach(System.out::println);
        } catch (ProductoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void buscarProducto() {
        try {
            System.out.print("ID producto a buscar: ");
            String id = scanner.nextLine();
            Producto producto = controller.encontrarProducto(id);
            System.out.println(producto);
        } catch (ProductoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarProducto() {
        try {
            System.out.print("ID producto a eliminar: ");
            String id = scanner.nextLine();
            controller.eliminarProducto(id);
            System.out.println("Producto eliminado.");
        } catch (ProductoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void crearFactura() {
        try {
            System.out.print("DNI del usuario: ");
            int dni = leerEntero();
            Usuario usuario = controller.encontrarUsuario(dni);

            List<Producto> productosCompra = new ArrayList<>();
            System.out.println("Ingrese los IDs de los productos (0 para terminar):");
            while (true) {
                listarProductos();
                System.out.print("ID producto: ");
                String id = scanner.nextLine();
                if ("0".equals(id)) break;
                try {
                    Producto producto = controller.encontrarProducto(id);
                    productosCompra.add(producto);
                } catch (ProductoException e) {
                    System.out.println("Producto no encontrado. Intente de nuevo.");
                }
            }

            if (productosCompra.isEmpty()) {
                System.out.println("Debe ingresar al menos un producto.");
                return;
            }

            Factura factura = controller.registrarFactura(usuario, productosCompra);
            System.out.println("Factura generada correctamente:");
            System.out.println(factura);
        } catch (UsuarioException | FacturaExceptions e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarFacturas() {
        try {
            Collection<Factura> facturas = controller.facturasRegistrados();
            System.out.println("Facturas registradas:");
            facturas.forEach(System.out::println);
        } catch (FacturaExceptions e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- Métodos utilitarios para leer datos ---
    private int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }

    private double leerDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Ingrese un número válido: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }
}