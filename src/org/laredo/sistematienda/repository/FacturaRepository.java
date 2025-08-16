package org.laredo.sistematienda.repository;

import org.laredo.sistematienda.model.Factura;

import java.util.*;

public class FacturaRepository {
    private final Map<Integer, Factura> facturas = new HashMap<>();

    public void registrarFactura(Factura factura) {
        facturas.put(factura.getIdFactura(), factura);
    }

    public Optional<Factura> buscarFactura(int idFactura) {
        return Optional.ofNullable(facturas.get(idFactura));
    }

    public Collection<Factura> listarFacturas() {
        return facturas.values();
    }
}