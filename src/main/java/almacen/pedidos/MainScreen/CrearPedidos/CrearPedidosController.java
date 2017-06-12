package almacen.pedidos.MainScreen.CrearPedidos;


import almacen.controllers.ProductoController;
import almacen.model.Producto;
import almacen.pedidos.model.AlmacenException;
import almacen.pedidos.util.AdaptadorListaCompra;
import almacen.pedidos.util.FilaTabla;
import almacen.pedidos.controllers.GestorPedidos;
import almacen.pedidos.model.Pedido;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.List;
import java.util.Optional;
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
    private TableView tablaPedido;
    @FXML
    private TableColumn<FilaTabla, Number> unidadesProductoColumn;
    @FXML
    private TableColumn<FilaTabla, String> nombrePedidoColumn;
    @FXML
    private TableColumn<FilaTabla, String> alimentoPedidoColumn;
    @FXML
    private TableColumn<FilaTabla, Number> cantidadPedidoColumn;
    @FXML
    private TableColumn<FilaTabla, String> unidadPedidoColumn;
    @FXML
    private TableColumn<FilaTabla, Number> precioPedidoColumn;

    @FXML
    private Button addProductoButton;
    @FXML
    private Button removePedidoButton;
    @FXML
    private Button confirmarPedidoButton;

    private ObservableList<Producto> productos;

    private ObservableList<FilaTabla> listaPedido;

    private ProductoController productoController;

    private GestorPedidos gestorPedidos;

    private Pedido pedido;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productoController = ProductoController.getInstance();

        gestorPedidos = GestorPedidos.getInstance();

        addProductoButton.disableProperty().bind(Bindings.isEmpty(tablaProductos.getSelectionModel().getSelectedItems()));

        productos = FXCollections.observableArrayList(productoController.getAllProductos());

        nombreColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getNombre()));

        alimentoColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAlimento().getNombre()));

        cantidadColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getCantidad()));

        unidadColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getUnidades().toString()));

        precioColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getPrecio()));

        tablaProductos.setItems(productos);

        unidadesProductoColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getUnidades()));

        nombrePedidoColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getProducto().getNombre()));

        alimentoPedidoColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getProducto().getAlimento().getNombre()));

        cantidadPedidoColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getProducto().getCantidad()));

        unidadPedidoColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getProducto().getUnidades().toString()));

        precioPedidoColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getProducto().getPrecio()));

    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }


    private void refreshPedidoTable() {
        List<FilaTabla> list = AdaptadorListaCompra.adaptarListaCompra(pedido.getLista());
        listaPedido = FXCollections.observableArrayList(list);
        tablaPedido.setItems(listaPedido);
    }


    @FXML
    public void OnAddProductoClick() {
        Optional<String> result = promptUnidadesProducto();
        if (result.isPresent()){
            Producto producto = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
            gestorPedidos.addProductoToPedido(pedido, producto, (int) Double.parseDouble(result.get()));
            confirmarPedidoButton.setDisable(false);
            refreshPedidoTable();
        }
    }


    public Optional<String> promptUnidadesProducto() {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Añadir Producto al Pedido");
        dialog.setHeaderText("Añadir Producto al Pedido");
        dialog.setContentText("Unidades:");
        DecimalFormat format = new DecimalFormat( "#.0" );
        dialog.getEditor().setTextFormatter( new TextFormatter<>(c ->
        {
            if ( c.getControlNewText().isEmpty() )
            {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = format.parse( c.getControlNewText(), parsePosition );

            if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
            {
                return null;
            }
            else
            {
                return c;
            }
        }));

        return dialog.showAndWait();
    }

    @FXML
    public void OnBorrarProductoClick() {

    }

    @FXML
    public void OnConfirmarPedidoClick() throws AlmacenException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Pedido");
        alert.setHeaderText("Confirmar Pedido");
        alert.setContentText("¿Desea confirmar el pedido y salir?");
        Optional<ButtonType> resultado = alert.showAndWait();

        if(resultado.get() == ButtonType.OK) {
            gestorPedidos.confirmarPedido(pedido);
            Stage stage = (Stage) confirmarPedidoButton.getScene().getWindow();
            stage.close();
        }
    }
}
