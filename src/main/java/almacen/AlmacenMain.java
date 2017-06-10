package almacen;

import almacen.model.Producto;
import almacen.model.ProductoAlmacen;
import almacen.persistance.ProductoAlmacenService;
import almacen.persistance.ProductoService;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import persistance.AppContext;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AlmacenMain implements Initializable {


    @FXML
    private TableView tablaProductos;
    @FXML
    private TableColumn<ProductoAlmacen, String> nombreColumn;
    @FXML
    private TableColumn<ProductoAlmacen, String> alimentoColumn;
    @FXML
    private TableColumn<ProductoAlmacen, Number> cantidadColumn;
    @FXML
    private TableColumn<ProductoAlmacen, Number> stockColumn;
    @FXML
    private TableColumn<ProductoAlmacen, String> unidadColumn;
    @FXML
    private TableColumn<ProductoAlmacen, Number> precioColumn;

    @FXML
    private Button nuevoProductoButton;
    @FXML
    private Button editarProductoButton;
    @FXML
    private Button borrarProductoButton;
    @FXML
    private Button pedidosButton;


    private ObservableList<ProductoAlmacen> productos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProductoAlmacenService productoService = (ProductoAlmacenService) AppContext.getBean("productoAlmacenService");
        ArrayList<ProductoAlmacen> listaProductos = new ArrayList<>();
        productoService.findAll().forEach(p -> listaProductos.add(p));

        productos = FXCollections.observableArrayList(listaProductos);

        nombreColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getProducto().getNombre()));

        alimentoColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getProducto().getAlimento().getNombre()));

        cantidadColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getProducto().getCantidad()));

        unidadColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getProducto().getUnidades().toString()));

        stockColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getStock()));

        precioColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getProducto().getPrecio()));

        tablaProductos.setItems(productos);

        editarProductoButton.disableProperty().bind(Bindings.isEmpty(tablaProductos.getSelectionModel().getSelectedItems()));

        borrarProductoButton.disableProperty().bind(Bindings.isEmpty(tablaProductos.getSelectionModel().getSelectedItems()));
    }

    @FXML
    private void OnBorrarProductoClick() {
        ProductoAlmacen producto = (ProductoAlmacen) tablaProductos.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Producto");
        alert.setHeaderText("Eliminar Producto: " + producto.getProducto().getNombre());
        alert.setContentText("¿Desea eliminar el producto?");
        Optional<ButtonType> resultado = alert.showAndWait();
        if(resultado.get() == ButtonType.OK) {
            ProductoAlmacenService productoAlmacenService = (ProductoAlmacenService) AppContext.getBean("productoAlmacenService");
            productoAlmacenService.remove(producto);
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Producto Eliminado");
            alert2.setHeaderText("Producto Elminado Correctamente");
            alert2.showAndWait();

            ArrayList<ProductoAlmacen> listaProductos = new ArrayList<>();
            productoAlmacenService.findAll().forEach(p -> listaProductos.add(p));
            productos = FXCollections.observableArrayList(listaProductos);
            tablaProductos.setItems(productos);
        }
    }
    @FXML
    private void OnNuevoProductoClick() {

    }

    @FXML
    private void OnEditarProductoClick() {

    }

}
