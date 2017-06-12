package view_controller;

import bussiness_controller.ControladorPersona;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.PedidoRestaurante;
import restaurante.modelo.Patron_Comando.Cocinero;

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
}

