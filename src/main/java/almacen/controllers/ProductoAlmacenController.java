package almacen.controllers;

import almacen.model.Producto;
import almacen.model.ProductoAlmacen;
import almacen.persistance.ProductoAlmacenService;
import persistance.AppContext;

import java.util.ArrayList;

public class ProductoAlmacenController {
    protected static ProductoAlmacenController productosController;
    protected static ProductoAlmacenService productoAlmacenService;

    protected ProductoAlmacenController(ProductoAlmacenService productoAlmacenService) {
        this.productosController = this;
        this.productoAlmacenService = productoAlmacenService;
    }

    public static ProductoAlmacenController getInstance() {
        if(productosController == null) {
            ProductoAlmacenService productoAlmacenService = (ProductoAlmacenService) AppContext.getBean("productoAlmacenService");
            productosController = new ProductoAlmacenController(productoAlmacenService);
        }
        return productosController;
    }

    public void guardarProducto(ProductoAlmacen productoAlmacen) {
        productoAlmacenService.update(productoAlmacen);
    }

    public ProductoAlmacen buscarPorProducto(Producto producto) {
        return productoAlmacenService.findByProducto(producto);
    }

    public void actualizarStock(Producto producto, int unidades) {
        ProductoAlmacen productoAlmacen = buscarPorProducto(producto);
        if(productoAlmacen != null) {
            productoAlmacen.setSock(productoAlmacen.getStock() + unidades);
            guardarProducto(productoAlmacen);
        }
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
