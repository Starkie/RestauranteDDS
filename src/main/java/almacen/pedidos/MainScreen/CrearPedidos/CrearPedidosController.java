package almacen.pedidos.MainScreen.CrearPedidos;


import almacen.controllers.ProductoController;
import almacen.model.Producto;
import almacen.pedidos.controllers.GestorPedidos;
import almacen.pedidos.model.Pedido;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CrearPedidosController implements Initializable{

    @FXML
    private TableView tablaProductos;
    @FXML
    private TableColumn<Producto, String> nombreColumn;
    @FXML
    private TableColumn<Producto, String> alimentoColumn;
    @FXML
    private TableColumn<Producto, Number> cantidadColumn;
    @FXML
    private TableColumn<Producto, String> unidadColumn;
    @FXML
    private TableColumn<Producto, Number> precioColumn;

    @FXML
    private Button addProductoButton;
    @FXML
    private Button borrarProductoButton;
    @FXML
    private Button confirmarPedidoButton;

    private ObservableList<Producto> productos;

    private ProductoController productoController;

    private GestorPedidos gestorPedidos;

    private Pedido pedido;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productoController = ProductoController.getInstance();

        gestorPedidos = GestorPedidos.getInstance();

        productos = FXCollections.observableArrayList(productoController.getAllProductos());

        nombreColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getNombre()));

        alimentoColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAlimento().getNombre()));

        cantidadColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getCantidad()));

        unidadColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getUnidades().toString()));

        precioColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getPrecio()));

        tablaProductos.setItems(productos);

    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @FXML
    public void OnAddProductoClick() {

    }

    @FXML
    public void OnBorrarProductoClick() {

    }

    @FXML
    public void OnConfirmarPedidoClick() {

    }
}
