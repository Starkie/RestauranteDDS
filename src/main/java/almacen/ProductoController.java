package almacen;


import Main.AlimentoController;
import almacen.model.Producto;
import almacen.persistance.ProductoService;
import model.Alimento;
import persistance.AppContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProductoController {
    private static ProductoController productoController;
    private static ProductoService productoService;

    private ProductoController() {
        this.productoController = this;
        this.productoService = (ProductoService) AppContext.getBean("productoService");
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
}
