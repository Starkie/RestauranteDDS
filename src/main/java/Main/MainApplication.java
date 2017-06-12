package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import persistance.AppContext;
import persistance.PersonaService;
import restaurante.business.modelo.Patron_Comando.Cocinero;
import restaurante.business.modelo.Patron_Comando.EmisorOrdenes;
import restaurante.business.modelo.Patron_Comando.Repartidor;
import restaurante.domain.Usuario;
import restaurante.view.view_controller.ControladorVistaLogin;

@SpringBootApplication
@ComponentScan({"persistance", "almacen.persistance", "restaurante.persistance"})
@EntityScan({"domain", "almacen.domain", "almacen.pedidos.domain", "restaurante.domain", "restaurante.business"})
@EnableJpaRepositories({"persistance", "almacen.persistance", "restaurante.persistance"})
public class MainApplication extends Application{

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApplication.class, args);

        PersonaService personaService = (PersonaService) AppContext.getBean("personaService");

        //Usuario
        personaService.add(new Usuario("Paco",26755185,"Direccion","123"));

        //Cocinero
        personaService.add(new Cocinero("Pedro",23232112,"123"));

        //Repartidor
        personaService.add(new Repartidor("Manu",22344545,"123"));

/*            AlimentoService alimentoService = (AlimentoService) AppContext.getBean("alimentoService");
            alimentoService.add(new Alimento("manzana"));
            alimentoService.add(new Alimento("tallarines"));

        //Alimento

        //Categoria
//        CategoriaService categoriaService = (CategoriaService) AppContext.getBean("categoriaService");
//        alimentoService.update(a2);
//        Categoria cat = categoriaService.findByName("categoria");
//        categoriaService.update(cat);

        //Usuario
        PersonaService personaService = (PersonaService) AppContext.getBean("personaService");
        personaService.add(new Usuario("Paco",26755185,"Direccion","dd"));
        Iterable<Persona> listaPersonas = personaService.findAll();
        Persona p = listaPersonas.iterator().next();

        //Pedido Restaurante
        PedidoRestauranteService pedidoRestauranteService = (PedidoRestauranteService) AppContext.getBean("pedidoRestauranteService");
        pedidoRestauranteService.add(new PedidoRestaurante((Usuario) p));
        Iterable<PedidoRestaurante> listaPedidos = pedidoRestauranteService.findAll();
        PedidoRestaurante pedido = listaPedidos.iterator().next();

        //Plato
        PlatoService platoService = (PlatoService) AppContext.getBean("platoService");
        Plato plato = new ComplementoPollo( new BaseTallarines());
        platoService.add(plato);
        Iterable<Plato> listaPlatos = platoService.findAll();
        Plato plato2 = listaPlatos.iterator().next();
        plato.getDescripcion();
        plato2.getDescripcion();

        pedido.addPlatoPedido(plato);
        plato.setPedidoRestaurante(pedido);
        platoService.update(plato);
        pedido.confirmarPedido();
        pedidoRestauranteService.update(pedido);

        //Cocinero
        personaService.add(new Cocinero("Pedro",23232112,"ssdsd"));

        //Repartidor
        personaService.add(new Repartidor("Repartidor",22344545,"sdsdsd"));

        personaService.findAll();

        //Reclamacion
        Reclamacion reclamacion = new Reclamacion(new Date(),pedido);
        ReclamacionService reclamacionService = (ReclamacionService) AppContext.getBean("reclamacionService");
        reclamacionService.add(reclamacion);
        Reclamacion reclamacion1 = reclamacionService.findAll().iterator().next();

        PedidoRestauranteService pedidoRestauranteService = (PedidoRestauranteService) AppContext.getBean("pedidoRestauranteService");
        PedidoRestaurante pedidoRestaurante2 = new PedidoRestaurante(new Usuario("Pepe",232,"Calle falsa","1212"));
        pedidoRestauranteService.add(pedidoRestaurante2);
        pedidoRestaurante2.setEstado(new EstadoCocinado());
        pedidoRestaurante2.addPlatoPedido(new BaseArroz());
        pedidoRestauranteService.update(pedidoRestaurante2);

        PedidoRestaurante pedidoRestaurante3 = new PedidoRestaurante(new Usuario("Manolo",2323,"Calle de Manolo","232"));
        pedidoRestauranteService.add(pedidoRestaurante3);
        pedidoRestaurante3.setEstado(new EstadoCocinado());
        pedidoRestaurante3.addPlatoPedido(new ComplementoGamba(new BaseArroz()));
        pedidoRestauranteService.update(pedidoRestaurante3);

        PedidoRestaurante pedidoRestaurante4 = new PedidoRestaurante(new Usuario("Paquito",231223,"Calle de AAAA","232"));
        pedidoRestauranteService.add(pedidoRestaurante4);
        pedidoRestaurante4.setEstado(new EstadoPendiente());
        pedidoRestaurante4.addPlatoPedido(new ComplementoGamba(new BaseArroz()));
        pedidoRestauranteService.update(pedidoRestaurante4);

        PedidoRestaurante pedidoRestaurante5 = new PedidoRestaurante(new Usuario("Manoli",232233,"Calle de QQQQ","232"));
        pedidoRestauranteService.add(pedidoRestaurante5);
        pedidoRestaurante5.setEstado(new EstadoPendiente());
        pedidoRestaurante5.addPlatoPedido(new ComplementoGamba(new BaseArroz()));
        pedidoRestauranteService.update(pedidoRestaurante5);

        PersonaService personaService = (PersonaService) AppContext.getBean("personaService");
        personaService.add(new Cocinero("Pedro",221212,"hola"));
        personaService.add(new Repartidor("Manu",221,"adios"));
        PedidoRestaurante p = new PedidoRestaurante(new Usuario("Paco",2675585,"Direccion","dd"));
        p.addPlatoPedido(new BaseArroz());
        p.addPlatoPedido(new SalsaCacahuetes(new BaseTallarines()));
        p.addPlatoPedido(new SalsaOstras(new BaseTallarines()));
        Calendar calendar = Calendar.getInstance();
        calendar.set(1996,12,11);
        p.setHoraConfirmacion(calendar.getTime());
        p.setEstado(new EstadoEnCamino());
        //p.setHoraRecibido(new Date());
        pedidoRestauranteService.add(p);
        Alimento a3 = new Alimento("Tallarines");
        alimentoService.add(a3);

        List<Producto> lista = new ArrayList<>();
        Producto p1 = new Producto("Producto1", a2,500, 2, UnidadesCantidad.KG);
        Producto p2 = new Producto("Tallarines Gallo", a3 , 20, 1, UnidadesCantidad.KG);
        ProductoAlmacen pr1 = new ProductoAlmacen(p1, 2);
        ProductoAlmacen pr2 = new ProductoAlmacen(p2, 3);
        ProductoAlmacenService productoService = (ProductoAlmacenService) AppContext.getBean("productoAlmacenService");
        productoService.add(pr1);
        productoService.add(pr2);

        lista.add(p1);
        lista.add(p2);

        GestorPedidos gestorPedidos = GestorPedidos.getInstance();

        Pedido p = gestorPedidos.crearPedido(lista);*/

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/restaurante/view/view_files/LoginView.fxml"));
        Parent root = loader.load();

        ControladorVistaLogin controladorVistaLogin = loader.getController();
        controladorVistaLogin.initStage(primaryStage);

        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //BorderPane root = FXMLLoader.load(getClass().getResource("/almacen/view/AlmacenMain.fxml"));
//        primaryStage.setTitle("Almacen");
//        primaryStage.setScene(new Scene(root, 600, 400));
//        primaryStage.show();
        EmisorOrdenes.getEmisorOrdenes().getThreadDisponibilidad().interrupt();
    }
}
