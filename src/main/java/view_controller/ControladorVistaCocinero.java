package view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import restaurante.modelo.Patron_Comando.Cocinero;

public class ControladorVistaCocinero {

    private Stage stage;
    private Cocinero cocinero;

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

    public void initStage(Stage stage, Cocinero cocinero) {
        this.stage = stage;
        this.cocinero = cocinero;
    }
}
