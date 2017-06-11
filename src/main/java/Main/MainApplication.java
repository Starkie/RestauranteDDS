package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import persistance.AppContext;
import persistance.PersonaService;
import restaurante.modelo.Patron_Comando.Cocinero;
import restaurante.modelo.Patron_Comando.Repartidor;
import view_controller.ControladorVistaLogin;

@SpringBootApplication
@ComponentScan({"persistance"})
@EntityScan({"model","restaurante"})
@EnableJpaRepositories({"persistance"})
public class MainApplication extends Application{

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApplication.class, args);
/*
        //Alimento
        AlimentoService alimentoService = (AlimentoService) AppContext.getBean("alimentoService");
        alimentoService.add(new Alimento("manzana", "fruta"));
        Alimento a2 = alimentoService.findByName("manzana");
        a2.addCategoria(new Categoria("categoria", "cat"));

        //Categoria
        CategoriaService categoriaService = (CategoriaService) AppContext.getBean("categoriaService");
        alimentoService.update(a2);
        Categoria cat = categoriaService.findByName("categoria");
        categoriaService.update(cat);

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
        Reclamacion reclamacion1 = reclamacionService.findAll().iterator().next();*/

        PersonaService personaService = (PersonaService) AppContext.getBean("personaService");
        personaService.add(new Usuario("Paco",26755185,"Direccion","dd"));
        personaService.add(new Cocinero("Pedro",221212,"hola"));
        personaService.add(new Repartidor("Manu",221,"adios"));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LoginView.fxml"));
        Parent root = loader.load();

        ControladorVistaLogin controladorVistaLogin = loader.getController();
        controladorVistaLogin.initStage(primaryStage);

        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
