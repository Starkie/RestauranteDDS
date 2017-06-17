/*package union_modulos;

import Main.MainApplication;
import almacen.business.controllers.ProductoAlmacenController;
import almacen.domain.Producto;
import almacen.domain.ProductoAlmacen;
import almacen.domain.UnidadesCantidad;
import almacen.persistance.ProductoAlmacenService;
import almacen.persistance.ProductoService;
import domain.Alimento;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import persistance.AlimentoService;
import persistance.AppContext;
import restaurante.business.modelo.Patron_Decorador.BaseArroz;
import restaurante.business.modelo.Patron_Decorador.ComplementoPollo;
import restaurante.business.modelo.Patron_Decorador.ComplementoTernera;
import restaurante.business.modelo.Patron_Decorador.DecoradorComplemento;
import restaurante.domain.PedidoRestaurante;
import restaurante.domain.Plato;
import restaurante.domain.Usuario;
import restaurante.persistance.PedidoRestauranteService;
import restaurante.persistance.PlatoService;

import java.util.ArrayList;
import java.util.List;

public class TestConsumirAlmacen {

    private static AlimentoService alimentoService;
    private static PlatoService platoService;
    private static PedidoRestauranteService pedidoRestauranteService;
    private static ProductoAlmacenService productoAlmacenService;
    private static List<Producto> listaProductos;

    @BeforeClass
    public static void setUp() {
        SpringApplication.run(MainApplication.class);
        platoService = (PlatoService) AppContext.getBean("platoService");
        alimentoService = (AlimentoService) AppContext.getBean("alimentoService");
        pedidoRestauranteService = (PedidoRestauranteService) AppContext.getBean("pedidoRestauranteService");
        ProductoService productoService = (ProductoService) AppContext.getBean("productoService");
        productoAlmacenService = (ProductoAlmacenService) AppContext.getBean("productoAlmacenService");
        String[] alimentos = new String[]{"Arroz", "Tallarines", "Pollo", "Ternera", "Gambas", "Cacahuetes", "Ostras"};
        listaProductos = new ArrayList<Producto>();

        for (int i = 0; i < alimentos.length; i++) {
            Alimento a = new Alimento(alimentos[i]);
            alimentoService.add(a);
            Producto p = new Producto(alimentos[i], a, 5, 6, UnidadesCantidad.Unidades);
            productoService.add(p);
            listaProductos.add(p);
            ProductoAlmacen prodAlm = new ProductoAlmacen();
            productoAlmacenService.add(prodAlm);
            prodAlm.setProducto(p);
            prodAlm.setSock(20);
            productoAlmacenService.update(prodAlm);
        }
    }

    @Test
    public void consumirAlmacen() throws Exception {
        //Se consume del almacén una vez se confirma un pedido
        PedidoRestaurante elPedido = new PedidoRestaurante(new Usuario("Víctor",23232,"Hola","123"));
        Plato p = crearPlato();
        p = decorarPlato(p);
        elPedido.addPlatoPedido(p);
        elPedido.confirmarPedido();

        ProductoAlmacenController productoAlmacenController = ProductoAlmacenController.getInstance();

        for (Producto producto: listaProductos) {
            ProductoAlmacen prodAlm = productoAlmacenController.buscarPorProducto(producto);
            if(producto.getNombre().equals("Ternera") || producto.getNombre().equals("Pollo")
                    || producto.getNombre().equals("Arroz")){
                Assert.assertEquals(19,prodAlm.getStock());
            }
            else{
                Assert.assertEquals(20,prodAlm.getStock());
            }
        }
    }

    private Plato decorarPlato(Plato plato) {
       return anyadirPollo(anyadirTernera(plato));
    }


    private Plato crearPlato() {
        Plato p = new BaseArroz();
        platoService.add(p);
        p.setAlimento(alimentoService.findByName("Arroz"));
        return p;
    }

    public Plato anyadirPollo(Plato seleccionado) {
        Plato nuevo = new ComplementoPollo();
        platoService.add(nuevo);
        nuevo.setAlimento(alimentoService.findByName("Pollo"));
        ((DecoradorComplemento) nuevo).setPlato(seleccionado);
        return nuevo;
    }

    public Plato anyadirTernera(Plato seleccionado) {
        Plato nuevo = new ComplementoTernera();
        platoService.add(nuevo);
        nuevo.setAlimento(alimentoService.findByName("Ternera"));
        ((DecoradorComplemento) nuevo).setPlato(seleccionado);
        return nuevo;
    }

}
*/