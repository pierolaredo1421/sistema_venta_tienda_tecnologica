package org.laredo.sistematienda.model;

import java.util.List;
import java.util.Objects;

public class Factura {
    private int idFactura;
    private List<Producto> productos;
    private Usuario usuario;
    private double total;

    public Factura(int idFactura, Usuario usuario, List<Producto> productos) {
        this.idFactura = idFactura;
        this.usuario = usuario;
        this.productos = productos;
        calcularTotal(productos);
    }

    public void calcularTotal(List<Producto> productos) {
        double descuento = productos.size() < 2 ? 0 :
                productos.size() <= 4 ? 0.05 :
                        productos.size() <= 9 ? 0.085 : 0.12;

        this.total = productos.stream()
                .mapToDouble(a -> a.getPrecio() - (a.getPrecio() * descuento))
                .sum();
    }

    public int getIdFactura() {
        return idFactura;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return idFactura == factura.idFactura;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idFactura);
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
        sb.append("IGV 18%").append("\n")
                .append("Total: ").append(this.total).append("\n");
        return sb.toString();
    }
}
