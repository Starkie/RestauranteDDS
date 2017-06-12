package almacen.business.controllers;

import almacen.domain.Producto;
import almacen.domain.ProductoAlmacen;
import almacen.pedidos.domain.AlmacenException;
import domain.Alimento;
import persistance.AlimentoService;
import persistance.AppContext;

import java.util.ArrayList;
import java.util.List;

public class AlimentoController {
    private static AlimentoController alimentoController;
    private static AlimentoService alimentoService;

    private AlimentoController(AlimentoService alimentoService) {
        alimentoController = this;
        AlimentoController.alimentoService = alimentoService;
    }

    public static AlimentoController getInstance() {
        if(alimentoController == null) {
            AlimentoService alimentoService = (AlimentoService) AppContext.getBean("alimentoService");
            alimentoController = new AlimentoController(alimentoService);
        }
        return alimentoController;
    }

    public Alimento crearAlimento(String nombre) {
        Alimento alimento = new Alimento(nombre);
        guardarAlimento(alimento);
        return alimento;
    }

    public void guardarAlimento(Alimento alimento) {
        alimentoService.add(alimento);
    }

    public ArrayList<Alimento> getAllAlimentos() {
        ArrayList<Alimento> alimentos = new ArrayList<>();
        alimentoService.findAll().forEach(a -> alimentos.add(a));
        return alimentos;
    }

    public void consumirAlimento(Alimento alimento, int unidades) throws AlmacenException{
        ProductoController productoController = ProductoController.getInstance();
        ProductoAlmacenController productoAlmacenController = ProductoAlmacenController.getInstance();
        List<Producto> productosAlimento = productoController.getProductosDeAlimento(alimento);

        for (Producto producto: productosAlimento) {
            ProductoAlmacen prodAlmacen = productoAlmacenController.buscarPorProducto(producto);
            if(prodAlmacen != null){
                if(prodAlmacen.getStock()>unidades) {
                    productoAlmacenController.actualizarStock(producto, unidades);
                    return;
                }
            }
        }
        throw new AlmacenException("No hay suficiente stock para cocinar el pedido");
    }

    public void consumirSiSePuedeAlimentos(List<Alimento> alimentosDelPedido) throws AlmacenException {
        comprobarDisponibilidad(alimentosDelPedido);
        for (Alimento alimentoPedido:alimentosDelPedido ) {
            consumirAlimento(alimentoPedido,-1);
        }
    }

    private void comprobarDisponibilidad(List<Alimento> alimentosDelPedido) throws AlmacenException {
        for (Alimento a: alimentosDelPedido) {
            int ocurrenciasDelAlimento = 0;
            for (Alimento aux: alimentosDelPedido) {
                if(a.getNombre().equals(aux.getNombre())) ocurrenciasDelAlimento++;
            }
            comprobarStockAlimento(a,ocurrenciasDelAlimento);
        }
    }

    private void comprobarStockAlimento(Alimento alimento, int unidades) throws AlmacenException {
        ProductoController productoController = ProductoController.getInstance();
        ProductoAlmacenController productoAlmacenController = ProductoAlmacenController.getInstance();
        List<Producto> productosAlimento = productoController.getProductosDeAlimento(alimento);

        for (Producto producto : productosAlimento) {
            ProductoAlmacen prodAlmacen = productoAlmacenController.buscarPorProducto(producto);
            if (prodAlmacen != null) {
                if (prodAlmacen.getStock() > unidades) return;
            }
        }
        throw new AlmacenException("No hay suficiente stock para cocinar el pedido");
    }
}
