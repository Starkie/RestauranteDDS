/*package restaurante.pedido;

import Main.MainApplication;
import almacen.domain.Producto;
import almacen.domain.ProductoAlmacen;
import almacen.domain.UnidadesCantidad;
import almacen.pedidos.domain.AlmacenException;
import almacen.persistance.ProductoAlmacenService;
import almacen.persistance.ProductoService;
import domain.Alimento;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import persistance.AlimentoService;
import persistance.AppContext;
import restaurante.business.modelo.Patron_Decorador.BaseArroz;
import restaurante.business.modelo.Patron_Estado.EstadoCocinandose;
import restaurante.domain.PedidoRestaurante;
import restaurante.domain.Plato;
import restaurante.domain.Reclamacion;
import restaurante.domain.Usuario;
import restaurante.persistance.PlatoService;

import java.util.Calendar;

public class TestPedido {

    private static AlimentoService alimentoService;
    private static PlatoService platoService;

    @Before
    public void prepararAlmacen(){
        SpringApplication.run(MainApplication.class);
        platoService = (PlatoService) AppContext.getBean("platoService");
        alimentoService = (AlimentoService) AppContext.getBean("alimentoService");
        ProductoService productoService = (ProductoService) AppContext.getBean("productoService");
        ProductoAlmacenService productoAlmacenService = (ProductoAlmacenService) AppContext.getBean("productoAlmacenService");
        String[] alimentos = new String[]{"Arroz","Tallarines","Pollo","Ternera","Gambas","Cacahuetes","Ostras"};
        for(int i=0; i<alimentos.length;i++){
            Alimento a = new Alimento(alimentos[i]);
            alimentoService.add(a);
            Producto p = new Producto(alimentos[i],a,5,6, UnidadesCantidad.Unidades);
            productoService.add(p);
            ProductoAlmacen prodAlm = new ProductoAlmacen();
            productoAlmacenService.add(prodAlm);
            prodAlm.setProducto(p);
            prodAlm.setSock(20);
            productoAlmacenService.update(prodAlm);
        }
    }

    @Test
    public void testConfirmarPedidoNuevo() throws Exception {
        PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco",232,"Hola","12"));
        Plato p = crearPlato();
        pedido.addPlatoPedido(p);
        pedido.confirmarPedido();
        Assert.assertEquals("Pendiente de Cocina",pedido.getEstado().getDescripcion());
    }

    @Test
    public void testCancelarPedido() throws Exception {
        PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco",232,"Hola","12"));
        pedido.addPlatoPedido(new BaseArroz());
        pedido.cancelarPedido();
        Assert.assertEquals("Pedido cancelado por el usuario.",pedido.getEstado().getDescripcion());
    }

    @Test
    public void testReclamarPedidoExcepcion(){
        try {
            PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco", 232, "Hola", "12"));
            Plato p = crearPlato();
            pedido.addPlatoPedido(p);
            pedido.confirmarPedido();
            pedido.reclamarRetraso();
        } catch(Exception e){Assert.assertEquals("No puede reclamar hasta que no pasen 30 minutos desde la confirmación de su pedido.",e.getMessage());}
    }

    private Plato crearPlato() {
        Plato p = new BaseArroz();
        platoService.add(p);
        p.setAlimento(alimentoService.findByName("Arroz"));
        return p;
    }

    @Test
    public void testReclamarPedidoNoExcepcion() throws Exception {
            PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco", 232, "Hola", "12"));
            Plato p = crearPlato();
            pedido.addPlatoPedido(p);
            pedido.confirmarPedido();
            Calendar calendar = Calendar.getInstance();
            calendar.set(1996,12,11);
            pedido.setHoraConfirmacion(calendar.getTime());
            Reclamacion r = pedido.reclamarRetraso();
            Assert.assertNotNull(r);
    }

    @Test
    public void testCancelarException(){
        try {
            PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco", 232, "Hola", "12"));
            Plato p = crearPlato();
            pedido.addPlatoPedido(p);
            pedido.confirmarPedido();
            pedido.setEstado(new EstadoCocinandose());
            pedido.cancelarPedido();
        } catch(Exception e){Assert.assertEquals("El pedido ya está cocinándose y no se puede cancelar.",e.getMessage());}
    }

    @Test
    public void testCofirmarPedidoExcepcion() throws Exception{
        try {
            PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco", 232, "Hola", "12"));
            Plato p = crearPlato();
            pedido.addPlatoPedido(p);
            pedido.confirmarPedido();
            pedido.confirmarPedido();
        } catch(Exception e){Assert.assertEquals("El pedido ya ha sido confirmado.",e.getMessage());}
    }

    public void testCofirmarPedidoExcepcion1Plato(){
        try {
            PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco", 232, "Hola", "12"));
            pedido.confirmarPedido();
        } catch(Exception e){Assert.assertEquals("Un pedido debe contener almenos 1 plato",e.getMessage());}
    }
}
*/