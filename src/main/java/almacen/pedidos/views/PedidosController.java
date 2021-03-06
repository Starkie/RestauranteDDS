package almacen.pedidos.views;


import almacen.pedidos.controllers.GestorPedidos;
import almacen.pedidos.domain.AlmacenException;
import almacen.pedidos.domain.Pedido;
import almacen.pedidos.views.CrearPedidos.CrearPedidosController;
import almacen.pedidos.views.InfoPedidos.ResumenPedidoController;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    private Button recibirPedidoButton;

    @FXML
    private Button cancelarPedidoButton;

    @FXML
    private Button reutilizarPedidoButton;


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
        recibirPedidoButton.disableProperty().bind(Bindings.isEmpty(tablaPedidos.getSelectionModel().getSelectedItems()));
        cancelarPedidoButton.disableProperty().bind(Bindings.isEmpty(tablaPedidos.getSelectionModel().getSelectedItems()));
        reutilizarPedidoButton.disableProperty().bind(Bindings.isEmpty(tablaPedidos.getSelectionModel().getSelectedItems()));
        refreshTable();

    }

    @FXML
    private void OnNuevoPedidoClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CrearPedidos/CrearPedidosView.fxml"));
        BorderPane root = fxmlLoader.load();
        CrearPedidosController crearPedidosController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Creacion de Pedido");
        stage.setScene(new Scene(root, 1000, 400));
        gestorPedidos = GestorPedidos.getInstance();
        Pedido p = gestorPedidos.crearPedido();
        crearPedidosController.setPedido(p);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        refreshTable();
    }

    @FXML
    private void OnReutilizarPedidoClick() throws IOException, CloneNotSupportedException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CrearPedidos/CrearPedidosView.fxml"));
        BorderPane root = fxmlLoader.load();
        CrearPedidosController crearPedidosController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Creacion de Pedido");
        stage.setScene(new Scene(root, 1000, 400));
        gestorPedidos = GestorPedidos.getInstance();
        Pedido seleccionado = (Pedido) tablaPedidos.getSelectionModel().getSelectedItem();
        Pedido p = gestorPedidos.crearPedido(seleccionado.getLista());
        crearPedidosController.setPedido(p);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        refreshTable();
    }

    @FXML
    private void OnAbrirPedidoClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InfoPedidos/ResumenPedidoView.fxml"));
        BorderPane root = fxmlLoader.load();
        ResumenPedidoController resumenPedidoController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Resumen de Pedido");
        stage.setScene(new Scene(root, 600, 400));
        gestorPedidos = GestorPedidos.getInstance();
        Pedido p = (Pedido) tablaPedidos.getSelectionModel().getSelectedItem();
        resumenPedidoController.setPedido(p);
        stage.show();
    }

    @FXML
    private void OnRecibirPedidoClick() {
        Pedido p = (Pedido) tablaPedidos.getSelectionModel().getSelectedItem();
        try {
            gestorPedidos.recibirPedido(p);

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Pedido Recibido");
            alert2.setHeaderText("Pedido Recibido");
            alert2.setContentText("Se ha actualizado el stock del almacén");
            alert2.showAndWait();

            refreshTable();
        } catch (AlmacenException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Recibir pedido");
            alert.setHeaderText("No se pudo recibir el pedido");
            Text text = new Text(e.getMessage());
            text.setWrappingWidth(300);
            alert.getDialogPane().setContent(text);
            alert.showAndWait();
        }
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
                Text text = new Text(e.getMessage());
                text.setWrappingWidth(300);
                alert2.getDialogPane().setContent(text);
               alert2.showAndWait();
            }
        }
    }

    public void refreshTable() {
        listaPedidos = FXCollections.observableArrayList(gestorPedidos.getAllPedidos());
        tablaPedidos.setItems(listaPedidos);
    }
}
