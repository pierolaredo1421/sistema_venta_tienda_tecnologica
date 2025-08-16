package org.laredo.sistematienda.model;

import java.util.List;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Factura N°").append(idFactura).append("\n")
                .append("Usuario: ").append(usuario.getNombre()).append("\n")
                .append("Productos:\n");
        for (Producto producto : productos) {
            sb.append(producto.getMarca()).append(" ")
                    .append(producto.getNombre()).append(" - ")
                    .append(producto.getPrecio()).append("\n");
        }
        sb.append("IGV 18%\n")
                .append("Total: ").append(total).append("\n");
        return sb.toString();
    }
}