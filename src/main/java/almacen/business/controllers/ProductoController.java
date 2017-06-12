package almacen.business.controllers;


import almacen.domain.Producto;
import almacen.persistance.ProductoService;
import persistance.AppContext;
import persistance.ServiceLocator;

import java.util.ArrayList;
import java.util.List;

public class ProductoController {
    private static ProductoController productoController;
    private static ProductoService productoService;

    private ProductoController(ProductoService productoService) {
        this.productoController = this;
        this.productoService = productoService;
    }

    public static ProductoController getInstance() {
        if(productoController == null) {
            ProductoService productoService = ServiceLocator.getProductoService();
            productoController = new ProductoController(productoService);
        }
        return productoController;
    }

    public List<Producto> getAllProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        productoService.findAll().forEach(p -> productos.add(p));
        return productos;
    }
}
