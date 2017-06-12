package almacen.business.controllers;


import almacen.domain.Producto;
import almacen.persistance.ProductoService;
import domain.Alimento;
import persistance.AppContext;

import java.util.ArrayList;
import java.util.List;

public class ProductoController {
    private static ProductoController productoController;
    private static ProductoService productoService;

    private ProductoController() {
        productoController = this;
        productoService = (ProductoService) AppContext.getBean("productoService");
    }

    public static ProductoController getInstance() {
        if(productoController == null) {
            productoController = new ProductoController();
        }
        return productoController;
    }

    public List<Producto> getAllProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        productoService.findAll().forEach(p -> productos.add(p));
        return productos;
    }

    public List<Producto> getProductosDeAlimento(Alimento alimento){
        ArrayList<Producto> productos = new ArrayList<>();
        productoService.findAllByAlimento(alimento).forEach(p-> productos.add(p));
        return  productos;
    }
}
