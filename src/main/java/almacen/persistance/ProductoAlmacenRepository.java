package almacen.persistance;

import almacen.model.Producto;
import almacen.model.ProductoAlmacen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoAlmacenRepository extends CrudRepository<ProductoAlmacen, Long> {
    ProductoAlmacen findByProducto(Producto producto);
}
