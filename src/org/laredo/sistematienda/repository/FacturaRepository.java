package org.laredo.sistematienda.repository;

import org.laredo.sistematienda.model.Factura;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FacturaRepository {
    private Map<Integer, Factura> facturas = new HashMap<>();

    public void factura (Factura factura) {
        facturas.put(factura.getIdFactura(), factura);
    }

    public Optional<Factura> buscarFactura (int idFactura) {
        return Optional.ofNullable(facturas.get(idFactura));
    }

    public Collection<Factura> listarFacturas() {
        return facturas.values();
    }
}
