package Main;

import almacen.model.Producto;
import almacen.model.ProductoAlmacen;
import almacen.model.UnidadesCantidad;
import almacen.pedidos.controllers.GestorPedidos;
import almacen.pedidos.model.Pedido;
import almacen.persistance.ProductoAlmacenService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Alimento;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import persistance.AlimentoService;
import persistance.AppContext;

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
        alimentoService.add(new Alimento("manzana"));
        Alimento a2 = alimentoService.findByName("manzana");
        alimentoService.update(a2);
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

        Pedido p = gestorPedidos.crearPedido(lista);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("/almacen/MainScreen/AlmacenMain.fxml"));
        primaryStage.setTitle("Almacen");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
