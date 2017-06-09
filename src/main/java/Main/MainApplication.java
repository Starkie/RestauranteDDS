package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import persistance.*;

@SpringBootApplication
@ComponentScan({"persistance"})
@EntityScan({"model"})
@EnableJpaRepositories({"persistance"})
public class MainApplication extends Application{

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        AlimentoService alimentoService = (AlimentoService) AppContext.getBean("alimentoService");
        alimentoService.add(new Alimento("manzana", "fruta"));
        Alimento a2 = alimentoService.findByName("manzana");
        a2.addCategoria(new Categoria("categoria", "cat"));
        CategoriaService categoriaService = (CategoriaService) AppContext.getBean("categoriaService");
        alimentoService.update(a2);
        Categoria cat = categoriaService.findByName("categoria");

        //Usuario
        PersonaService personaService = (PersonaService) AppContext.getBean("personaService");
        personaService.add(new Usuario("Paco",26755185,"Direccion"));
        Iterable<Persona> listaPersonas = personaService.findAll();
        Persona p = listaPersonas.iterator().next();
        //personaService.add(new Repartidor("Repartidor",22344545));

        //Pedido Restaurante
        PedidoRestauranteService pedidoRestauranteService = (PedidoRestauranteService) AppContext.getBean("pedidoRestauranteService");
        pedidoRestauranteService.add(new PedidoRestaurante((Usuario) p));
        Iterable<PedidoRestaurante> listaPedidos = pedidoRestauranteService.findAll();
        PedidoRestaurante pedido = listaPedidos.iterator().next();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
