package almacen;

import almacen.model.Producto;
import almacen.persistance.ProductoService;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import persistance.AppContext;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AlmacenMain implements Initializable {


    @FXML
    private TableView tablaProductos;
    @FXML
    private TableColumn<Producto, String> nombreColumn;
    @FXML
    private TableColumn<Producto, String> alimentoColumn;
    @FXML
    private TableColumn<Producto, Integer> stockColumn;

    @FXML
    private Button nuevoProductoButton;
    @FXML
    private Button editarProductoButton;
    @FXML
    private Button borrarProductoButton;


    private ObservableList<Producto> productos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProductoService productoService = (ProductoService) AppContext.getBean("productoService");
        ArrayList<Producto> listaProductos = new ArrayList<>();
        productoService.findAll().forEach(p -> listaProductos.add(p));

        productos = FXCollections.observableArrayList(listaProductos);

        nombreColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getNombre()));

        alimentoColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAlimento().getNombre()));

        tablaProductos.setItems(productos);

    }
}
