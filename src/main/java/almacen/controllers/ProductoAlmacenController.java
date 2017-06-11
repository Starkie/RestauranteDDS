package almacen.controllers;

import almacen.model.ProductoAlmacen;
import almacen.persistance.ProductoAlmacenService;
import persistance.AppContext;

import java.util.ArrayList;

public class ProductoAlmacenController {
    private static ProductoAlmacenController productosController;
    private static ProductoAlmacenService productoAlmacenService;

    private ProductoAlmacenController() {
        this.productosController = this;
        this.productoAlmacenService = (ProductoAlmacenService) AppContext.getBean("productoAlmacenService");
    }

    public static ProductoAlmacenController getInstance() {
        if(productosController == null)
            productosController = new ProductoAlmacenController();
        return productosController;
    }

    public void guardarProducto(ProductoAlmacen productoAlmacen) {
        productoAlmacenService.update(productoAlmacen);
    }

    public ArrayList<ProductoAlmacen> getAllProductos() {
        ArrayList<ProductoAlmacen> lista = new ArrayList<>();
        productoAlmacenService.findAll().forEach(p -> lista.add(p));
        return lista;
    }

    public void eliminarProducto(ProductoAlmacen productoAlmacen) {
        productoAlmacenService.remove(productoAlmacen);
    }
}
