package almacen.persistance;

import almacen.domain.Producto;
import almacen.domain.ProductoAlmacen;

import java.util.ArrayList;
import java.util.List;

public class ProductoAlmacenServiceMock extends ProductoAlmacenService {
    List<ProductoAlmacen> productos = new ArrayList<>();
    @Override
    public void update(ProductoAlmacen productoAlmacen) {
        productos.add(productoAlmacen);
    }
    @Override
    public ProductoAlmacen findByProducto(Producto producto) {
        return productos.stream().filter(f -> f.getProducto().equals(producto)).findFirst().get();
    }

}
