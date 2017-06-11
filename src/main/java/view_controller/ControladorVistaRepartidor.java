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
import restaurante.modelo.Patron_Comando.Repartidor;

public class ControladorVistaRepartidor {

    private Stage stage;
    private Repartidor repartidor;
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
        controladorPersona.finalizarActualRepartidor(repartidor);
        actualizarPedidoAtendiendo();
        actualizarTabla();
    }

    public void initStage(Stage stage, Repartidor repartidor) {
        this.stage = stage;
        this.repartidor = repartidor;
        this.controladorPersona = ControladorPersona.getControladorPersona();

        iniciarLabelAcceso();
        iniciarColumnasTabla();
        actualizarTabla();
        actualizarPedidoAtendiendo();
    }

    private void actualizarPedidoAtendiendo() {
        txtPedidoActual.setEditable(false);
        if(repartidor.getPedidoAtendiendo() == null) txtPedidoActual.setText("");
        else txtPedidoActual.setText(repartidor.getPedidoAtendiendo().toString() + '\n' +
                "Usuario: " + repartidor.getPedidoAtendiendo().getUsuario().getNombre() + '\n' +
                "Dirección: " + repartidor.getPedidoAtendiendo().getUsuario().getDireccion());
    }

    private void actualizarTabla() {
        ObservableList<PedidoRestaurante> datos = FXCollections.observableArrayList(controladorPersona.obtenerPendientesRepartidor());
        tablaPedidos.setItems(datos);
        tablaPedidos.refresh();
    }

    private void iniciarColumnasTabla() {
        colID.setCellValueFactory(o-> new SimpleLongProperty(o.getValue().getId()).asObject());
        colPlatos.setCellValueFactory(o-> new SimpleStringProperty(o.getValue().toString()));
    }

    private void iniciarLabelAcceso() {
        lblAcceso.setText("Repartidor: "+ repartidor.getNombre());
    }
}