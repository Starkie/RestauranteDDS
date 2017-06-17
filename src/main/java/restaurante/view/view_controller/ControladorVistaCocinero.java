package restaurante.view.view_controller;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import restaurante.business.bussiness_controller.ControladorPersona;
import restaurante.business.modelo.Patron_Comando.Cocinero;
import restaurante.domain.PedidoRestaurante;

import java.io.IOException;

public class ControladorVistaCocinero {

    private Stage stage;
    private Cocinero cocinero;
    private ControladorPersona controladorPersona;
    
    @FXML
    private Label lblAcceso;

    @FXML
    private TableView<PedidoRestaurante> tablaPedidos;

    @FXML
    private TableColumn<PedidoRestaurante, Long> colID;

    @FXML
    private TableColumn<PedidoRestaurante, String> colPlatos;

    @FXML
    private TextArea txtPedidoActual;

    @FXML
    private Button btnSiguientePedido;

    @FXML
    void pressSiguiente(ActionEvent event) {
        controladorPersona.finalizarActualCocinero(cocinero);
        actualizarPedidoAtendiendo();
        actualizarTabla();
    }


    @FXML
    void pressAccesoAlmacen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/almacen/view/AlmacenMain.fxml"));
        BorderPane root = loader.load();

        Stage ventanaPedidos = new Stage();
        ventanaPedidos.setTitle("Almacen");
        ventanaPedidos.setScene(new Scene(root, 600, 400));
        ventanaPedidos.initModality(Modality.APPLICATION_MODAL);
        ventanaPedidos.show();
    }

    public void initStage(Stage stage, Cocinero cocinero) {
        this.stage = stage;
        this.cocinero = cocinero;
        this.controladorPersona = ControladorPersona.getControladorPersona();

        iniciarLabelAcceso();
        iniciarColumnasTabla();
        actualizarTabla();
        actualizarPedidoAtendiendo();
    }

    private void actualizarPedidoAtendiendo() {
        txtPedidoActual.setEditable(false);
        if(cocinero.getPedidoAtendiendo() == null) txtPedidoActual.setText("");
        else txtPedidoActual.setText(cocinero.getPedidoAtendiendo().toString() + '\n' +
                "Usuario: " + cocinero.getPedidoAtendiendo().getUsuario().getNombre());

    }

    private void actualizarTabla() {
        ObservableList<PedidoRestaurante> datos = FXCollections.observableArrayList(controladorPersona.obtenerPendientesCocinero());
        tablaPedidos.setItems(datos);
        tablaPedidos.refresh();
    }

    private void iniciarColumnasTabla() {
        colID.setCellValueFactory(o-> new SimpleLongProperty(o.getValue().getId()).asObject());
        colPlatos.setCellValueFactory(o-> new SimpleStringProperty(o.getValue().toString()));

    }

    private void iniciarLabelAcceso() {lblAcceso.setText("Cocinero: "+ cocinero.getNombre());
    }

    @FXML
    void volverPress(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/restaurante/view/view_files/LoginView.fxml"));
        Parent root = loader.load();

        ControladorVistaLogin controladorLogin = loader.getController();
        controladorLogin.initStage(stage);
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}

