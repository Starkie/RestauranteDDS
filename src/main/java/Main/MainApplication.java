package Main;

import almacen.domain.Producto;
import almacen.domain.ProductoAlmacen;
import almacen.domain.UnidadesCantidad;
import almacen.persistance.ProductoAlmacenService;
import almacen.persistance.ProductoService;
import domain.Alimento;
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
import persistance.AlimentoService;
import persistance.AppContext;
import persistance.PersonaService;
import persistance.ServiceLocator;
import restaurante.business.modelo.Patron_Comando.Cocinero;
import restaurante.business.modelo.Patron_Comando.EmisorOrdenes;
import restaurante.business.modelo.Patron_Comando.Repartidor;
import restaurante.business.modelo.Patron_Decorador.BaseArroz;
import restaurante.business.modelo.Patron_Decorador.ComplementoGamba;
import restaurante.business.modelo.Patron_Estado.EstadoCocinado;
import restaurante.business.modelo.Patron_Estado.EstadoPendiente;
import restaurante.domain.PedidoRestaurante;
import restaurante.domain.Usuario;
import restaurante.persistance.PedidoRestauranteService;
import restaurante.view.view_controller.ControladorVistaLogin;

@SpringBootApplication
@ComponentScan({"persistance", "almacen.persistance", "restaurante.persistance"})
@EntityScan({"domain", "almacen.domain", "almacen.pedidos.domain", "restaurante.domain", "restaurante.business"})
@EnableJpaRepositories({"persistance", "almacen.persistance", "restaurante.persistance"})
public class MainApplication extends Application{

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApplication.class, args);
        prepararBD();
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
        primaryStage.setResizable(true);
        primaryStage.show();

        EmisorOrdenes.getEmisorOrdenes().setContinuarThread(false);
    }


    public static void prepararBD() {
        PersonaService personaService = (PersonaService) AppContext.getBean("personaService");
        AlimentoService alimentoService = (AlimentoService) AppContext.getBean("alimentoService");
        ProductoService productoService = (ProductoService) AppContext.getBean("productoService");
        ProductoAlmacenService productoAlmacenService = (ProductoAlmacenService) AppContext.getBean("productoAlmacenService");

        //Usuario
        personaService.add(new Usuario("Paco",26755185,"Direccion","123"));

        //Cocinero
        personaService.add(new Cocinero("Pedro",23232112,"123"));

        //Repartidor
        personaService.add(new Repartidor("Manu",22344545,"123"));

        //Alimentos
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
}
