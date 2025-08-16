package org.laredo.sistematienda.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Factura {
    private final int idFactura;
    private final List<Producto> productos;
    private final Usuario usuario;
    private final double total;

    public Factura(int idFactura, Usuario usuario, List<Producto> productos) {
        this.idFactura = idFactura;
        this.usuario = usuario;
        this.productos = List.copyOf(productos);
        this.total = calcularTotal();
    }

    private double calcularTotal() {
        double descuento = 0;
        int cantidad = productos.size();
        if (cantidad < 2) descuento = 0;
        else if (cantidad <= 4) descuento = 0.05;
        else if (cantidad <= 9) descuento = 0.085;
        else descuento = 0.12;

        double finalDescuento = descuento;
        return productos.stream()
                .mapToDouble(p -> p.getPrecio() - (p.getPrecio() * finalDescuento))
                .sum();
    }

    public int getIdFactura() { return idFactura; }
    public List<Producto> getProductos() { return productos; }
    public Usuario getUsuario() { return usuario; }
    public double getTotal() { return total; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Factura factura)) return false;
        return idFactura == factura.idFactura;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFactura);
    }

    // ... resto de la clase Factura

    private String resumenAdaptadoresBluetooth() {
        // Contar cantidad por marca
        Map<String, Integer> conteo = new HashMap<>();
        for (Producto p : productos) {
            conteo.put(p.getMarca().toLowerCase(), conteo.getOrDefault(p.getMarca().toLowerCase(), 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Adaptadores Bluetooth de regalo:\n");
        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            String marca = entry.getKey();
            int cantidad = entry.getValue();
            int adaptadores = 0;
            switch (marca) {
                case "sony" -> adaptadores = cantidad / 2;
                case "jbl" -> adaptadores = (cantidad / 3) * 2;
                case "logitech" -> adaptadores = cantidad;
                case "redragon" -> adaptadores = cantidad * 2;
            }
            if (adaptadores > 0) {
                sb.append(" - ").append(capitalize(marca)).append(": ").append(adaptadores).append("\n");
            }
        }
        return sb.toString();
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Factura N°" + idFactura).append("\n");
        sb.append("Usuario: ").append(usuario.getNombre()).append("\n");
        sb.append("Productos: \n");
        for (Producto producto : productos) {
            sb.append(producto.getMarca()).append(" ")
                    .append(producto.getNombre()).append(" - ")
                    .append(producto.getPrecio()).append("\n");
        }
        sb.append(resumenAdaptadoresBluetooth());
        sb.append("IGV 18%").append("\n")
                .append("Total: ").append(this.total).append("\n");
        return sb.toString();
    }
}