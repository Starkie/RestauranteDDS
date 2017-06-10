package view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import restaurante.modelo.Patron_Comando.Repartidor;

public class ControladorVistaRepartidor {

    private Stage stage;
    private Repartidor repartidor;

    @FXML
    private Label lblAcceso;

    @FXML
    private TableView<?> tablaPedidos;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colPlatos;

    @FXML
    private TextArea txtPedidoActual;

    @FXML
    private Button btnSiguientePedido;

    @FXML
    void pressSiguiente(ActionEvent event) {

    }

    public void initStage(Stage stage, Repartidor repartidor) {
        this.stage = stage;
        this.repartidor = repartidor;
    }
}