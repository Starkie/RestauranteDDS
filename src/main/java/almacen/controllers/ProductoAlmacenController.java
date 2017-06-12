package almacen.controllers;

import almacen.model.Producto;
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

    private ProductoAlmacen buscarPorProducto(Producto producto) {
        return productoAlmacenService.findByProducto(producto);
    }

    public void actualizarStock(Producto producto, int unidades) {
        ProductoAlmacen productoAlmacen = buscarPorProducto(producto);
        productoAlmacen.setSock(productoAlmacen.getStock() + unidades);
        guardarProducto(productoAlmacen);
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
