package Main;

import almacen.model.Producto;
import almacen.model.UnidadesCantidad;
import almacen.pedidos.model.GestorPedidos;
import almacen.pedidos.model.Pedido;
import almacen.persistance.ProductoService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Alimento;
import model.Categoria;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import persistance.AlimentoService;
import persistance.AppContext;
import persistance.CategoriaService;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan({"persistance", "almacen.persistance"})
@EntityScan({"model", "almacen.model", "almacen.pedidos.model"})
@EnableJpaRepositories({"persistance", "almacen.persistance"})
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
        Alimento a3 = new Alimento("Tallarines", "Pasta");
        alimentoService.add(a3);

        List<Producto> lista = new ArrayList<>();
        Producto p1 = new Producto("Producto1", "Producto1", a2,500, 2, UnidadesCantidad.KG);
        Producto p2 = new Producto("Tallarines Gallo", "Tallarines de la marca gallo", a3 , 20, 1, UnidadesCantidad.KG);
        ProductoService productoService = (ProductoService) AppContext.getBean("productoService");
        productoService.add(p1);
        productoService.add(p2);
        Iterable<Producto> productos = productoService.findAll();

        lista.add(p1);

        GestorPedidos gestorPedidos = GestorPedidos.getInstance();

        Pedido p = gestorPedidos.crearPedido(lista);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("/almacen/AlmacenMain.fxml"));
        primaryStage.setTitle("Almacen");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
