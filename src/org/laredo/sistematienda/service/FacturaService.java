package org.laredo.sistematienda.service;

import org.laredo.sistematienda.exeptions.FacturaExceptions;
import org.laredo.sistematienda.factory.FacturaFactory;
import org.laredo.sistematienda.model.Factura;
import org.laredo.sistematienda.model.Producto;
import org.laredo.sistematienda.model.Usuario;
import org.laredo.sistematienda.repository.FacturaRepository;

import java.util.Collection;
import java.util.List;

public class FacturaService {
    private FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public void facturar(Usuario usuario, List<Producto> productos) {
        Factura factura = FacturaFactory.facturacion(usuario, productos);
        facturaRepository.factura(factura);
    }

    public Factura buscarFactura(int idFactura) throws FacturaExceptions {
        return facturaRepository.buscarFactura(idFactura).orElseThrow(() -> new FacturaExceptions("No se encontró la factura N°" + idFactura));
    }

    public Collection<Factura> listarFacturas() {
        return facturaRepository.listarFacturas();
    }

    //MÉTODO DESCUENTO

}
