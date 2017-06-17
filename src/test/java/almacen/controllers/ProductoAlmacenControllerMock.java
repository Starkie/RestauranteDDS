package almacen.controllers;

import almacen.business.controllers.ProductoAlmacenController;
import almacen.domain.Producto;
import almacen.domain.ProductoAlmacen;
import almacen.persistance.ProductoAlmacenService;
import almacen.persistance.ProductoAlmacenServiceMock;

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
