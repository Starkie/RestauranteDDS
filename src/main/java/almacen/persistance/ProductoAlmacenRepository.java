package almacen.persistance;

import almacen.domain.Producto;
import almacen.domain.ProductoAlmacen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoAlmacenRepository extends CrudRepository<ProductoAlmacen, Long> {
    ProductoAlmacen findByProducto(Producto producto);
}
