package almacen.persistance;

import almacen.domain.Producto;
import almacen.domain.ProductoAlmacen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistance.CrudService;

@Service("productoAlmacenService")
public class ProductoAlmacenService implements CrudService<ProductoAlmacen,Long> {

    @Autowired
    private ProductoAlmacenRepository productoAlmacenRepository;


    @Override
    public void add(ProductoAlmacen entidad) {
        productoAlmacenRepository.save(entidad);
    }

    @Override
    public void update(ProductoAlmacen entidad) {
        productoAlmacenRepository.save(entidad);
    }

    @Override
    public void remove(ProductoAlmacen entidad) {
        productoAlmacenRepository.delete(entidad);
    }

    @Override
    public ProductoAlmacen findById(Long id) {
        return productoAlmacenRepository.findOne(id);
    }

    @Override
    public Iterable<ProductoAlmacen> findAll() {
        return productoAlmacenRepository.findAll();
    }

    public ProductoAlmacen findByProducto(Producto producto) {
        return productoAlmacenRepository.findByProducto(producto);
    }
}
