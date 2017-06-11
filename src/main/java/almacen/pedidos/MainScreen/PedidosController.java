package almacen.pedidos.MainScreen;


import almacen.pedidos.MainScreen.CrearPedidos.CrearPedidosController;
import almacen.pedidos.MainScreen.InfoPedidos.ResumenPedidoController;
import almacen.pedidos.controllers.GestorPedidos;
import almacen.pedidos.model.AlmacenException;
import almacen.pedidos.model.Pedido;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

public class PedidosController implements Initializable {

    @FXML
    private TableView  tablaPedidos;

    @FXML
    private TableColumn<Pedido, Number> idColumn;
    @FXML
    private TableColumn<Pedido, String> fechaColumn;

    @FXML
    private TableColumn<Pedido, String> estadoColumn;

    @FXML
    private TableColumn<Pedido, Number> precioColumn;

    @FXML
    private Button nuevoPedidoButton;

    @FXML
    private Button abrirPedidoButton;

    @FXML
    private Button editarPedidoButton;

    @FXML
    private Button cancelarPedidoButton;


    private GestorPedidos gestorPedidos;

    private ObservableList<Pedido> listaPedidos;

    private SimpleDateFormat dt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gestorPedidos = GestorPedidos.getInstance();

        dt = new SimpleDateFormat("dd/MM/yyyy");

        idColumn.setCellValueFactory(p -> new SimpleLongProperty(p.getValue().getId()));
        fechaColumn.setCellValueFactory(p -> new SimpleStringProperty(dt.format(p.getValue().getFecha())));
        estadoColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getEstado().toString()));
        precioColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getPrecio()));

        abrirPedidoButton.disableProperty().bind(Bindings.isEmpty(tablaPedidos.getSelectionModel().getSelectedItems()));
        editarPedidoButton.disableProperty().bind(Bindings.isEmpty(tablaPedidos.getSelectionModel().getSelectedItems()));
        cancelarPedidoButton.disableProperty().bind(Bindings.isEmpty(tablaPedidos.getSelectionModel().getSelectedItems()));

        refreshTable();

    }

    @FXML
    private void OnNuevoPedidoClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CrearPedidos/CrearPedidosView.fxml"));
        BorderPane root = fxmlLoader.load();
        CrearPedidosController crearPedidosController = fxmlLoader.getController();
        Stage stage = (Stage) nuevoPedidoButton.getScene().getWindow();
        stage.setTitle("Creacion de Pedido");
        stage.setScene(new Scene(root, 600, 400));
        gestorPedidos = GestorPedidos.getInstance();
        Pedido p = gestorPedidos.crearPedido();
        crearPedidosController.setPedido(p);
        stage.show();
    }

    @FXML
    private void OnAbrirPedidoClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InfoPedidos/ResumenPedidoView.fxml"));
        BorderPane root = fxmlLoader.load();
        ResumenPedidoController resumenPedidoController = fxmlLoader.getController();
        Stage stage = (Stage) nuevoPedidoButton.getScene().getWindow();
        stage.setTitle("Resumen de Pedido");
        stage.setScene(new Scene(root, 600, 400));
        gestorPedidos = GestorPedidos.getInstance();
        Pedido p = (Pedido) tablaPedidos.getSelectionModel().getSelectedItem();
        resumenPedidoController.setPedido(p);
        stage.show();
    }

    @FXML
    private void OnEditarPedidoClick() {

    }

    @FXML
    public void OnCancelarPedidoClick() {
        Pedido pedido = (Pedido) tablaPedidos.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancelar Pedido");
        alert.setHeaderText("Cancelar Pedido: " + dt.format(pedido.getFecha()));
        alert.setContentText("¿Desea cancelar el pedido?");
        Optional<ButtonType> resultado = alert.showAndWait();

        if(resultado.get() == ButtonType.OK) {
            try {
                gestorPedidos.cancelarPedido(pedido);

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Pedido Cancelado");
                alert2.setHeaderText("Pedido Cancelado Correctamente");
                alert2.showAndWait();

                refreshTable();
            } catch (AlmacenException e) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Error al Cancelar Pedido");
                alert2.setHeaderText("Error cancelando el pedido");
                alert2.setContentText(e.getMessage());
                alert2.showAndWait();
            }
        }
    }

    public void refreshTable() {
        listaPedidos = FXCollections.observableArrayList(gestorPedidos.getAllPedidos());
        tablaPedidos.setItems(listaPedidos);
    }
}
