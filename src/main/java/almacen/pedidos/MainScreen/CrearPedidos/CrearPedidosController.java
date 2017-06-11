package almacen.pedidos.MainScreen.CrearPedidos;


import almacen.controllers.ProductoController;
import almacen.model.Producto;
import almacen.pedidos.controllers.GestorPedidos;
import almacen.pedidos.model.Pedido;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
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

        addProductoButton.disableProperty().bind(Bindings.isEmpty(tablaProductos.getSelectionModel().getSelectedItems()));

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
        Optional<String> result = promptUnidadesProducto();
        if (result.isPresent()){
            Producto producto = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
            gestorPedidos.addProductoToPedido(pedido, producto, (int) Double.parseDouble(result.get()));
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
    public void OnConfirmarPedidoClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Pedido");
        alert.setHeaderText("Confirmar Pedido");
        alert.setContentText("¿Desea confirmar el pedido y salir?");
        Optional<ButtonType> resultado = alert.showAndWait();

        if(resultado.get() == ButtonType.OK) {
            gestorPedidos.guardarPedido(pedido);

            Stage stage = (Stage) confirmarPedidoButton.getScene().getWindow();

            stage.close();
        }
    }
}
