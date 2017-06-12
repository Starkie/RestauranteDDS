package almacen.controllers;

import almacen.model.Producto;
import almacen.model.ProductoAlmacen;
import almacen.persistance.ProductoAlmacenServiceMock;
import almacen.persistance.ProductoAlmacenService;

public class ProductoAlmacenControllerMock extends ProductoAlmacenController {

    protected ProductoAlmacenControllerMock(ProductoAlmacenService productoAlmacenService) {
        super(productoAlmacenService);
    }

    public static ProductoAlmacenController getInstance() {
        if(productosController == null) {
            ProductoAlmacenService productoAlmacenService = new ProductoAlmacenServiceMock();
            productosController = new ProductoAlmacenControllerMock(productoAlmacenService);
        }
        return productosController;
    }

    @Override
    public ProductoAlmacen buscarPorProducto(Producto producto) {
        return productoAlmacenService.findByProducto(producto);
    }
}
