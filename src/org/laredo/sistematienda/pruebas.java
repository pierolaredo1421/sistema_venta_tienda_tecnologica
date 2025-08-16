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

public class pruebas {
    public static void main(String[] args) throws ProductoException, UsuarioException, FacturaExceptions {
        Scanner sc = new Scanner(System.in);
        UsuarioRepository ur = new UsuarioRepository();
        UsuarioService us = new UsuarioService(ur);
        ProductoRepository pr = new ProductoRepository();
        ProductoService ps = new ProductoService(pr);
        FacturaRepository fr = new FacturaRepository();
        FacturaService fs = new FacturaService(fr);

        Usuario u = new Usuario(73090125, "piero");
        us.crearUsuario(u);
        ps.agregarProducto("iphone 15", "apple", 1000);
        ps.agregarProducto("kumara k552", "redragon", 100);
        ps.agregarProducto(Productos.Logitech);

        String id;
        List<Producto> lp = new ArrayList<Producto>();
        try {
            do {
                System.out.print("ingrese el id del producto: ");
                id = sc.nextLine();
                Producto p = ps.buscarProducto(id);
                lp.add(p);
            } while (!id.equals("0"));
            fs.facturar(u, lp);
            Factura f1 = fs.buscarFactura(26457);
            System.out.println(f1);

            System.out.println("---------------------------");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
