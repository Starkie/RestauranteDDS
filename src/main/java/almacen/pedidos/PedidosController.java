package almacen.pedidos;


import almacen.pedidos.model.AlmacenException;
import almacen.pedidos.model.Pedido;
import javafx.beans.binding.Bindings;
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
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class PedidosController implements Initializable {

    @FXML
    private TableView  tablaPedidos;

    @FXML
    private TableColumn<Pedido, String> fechaColumn;

    @FXML
    private TableColumn<Pedido, String> estadoColumn;

    @FXML
    private TableColumn<Pedido, Number> precioColumn;

    @FXML
    private Button nuevoPedidoButton;

    @FXML
    private Button editarPedidoButton;

    @FXML
    private Button cancelarPedidoButton;


    private GestorPedidos gestorPedidos;

    private ObservableList<Pedido> listaPedidos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gestorPedidos = GestorPedidos.getInstance();

        listaPedidos = FXCollections.observableArrayList(gestorPedidos.getAllPedidos());

        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

        fechaColumn.setCellValueFactory(p -> new SimpleStringProperty(dt.format(p.getValue().getFecha())));
        estadoColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getEstado().toString()));
        precioColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getPrecio()));

        editarPedidoButton.disableProperty().bind(Bindings.isEmpty(tablaPedidos.getSelectionModel().getSelectedItems()));
        cancelarPedidoButton.disableProperty().bind(Bindings.isEmpty(tablaPedidos.getSelectionModel().getSelectedItems()));

        tablaPedidos.setItems(listaPedidos);

    }

    @FXML
    private void OnNuevoPedidoClick() {

    }

    @FXML
    private void OnEditarPedidoClick() {

    }

    @FXML
    public void OnCancelarPedidoClick() {

    }
}
