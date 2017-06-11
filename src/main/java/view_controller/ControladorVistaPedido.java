package view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Usuario;

public class ControladorVistaPedido{

    private Stage stage;
    private Usuario usuario;

    @FXML
    private Button btnNuevoPedido;

    @FXML
    private Button btnCancelarPedido;

    @FXML
    private Button btnReclamarRetraso;

    @FXML
    private TableView<?> tablaPedidos;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colPlatos;

    @FXML
    private TableColumn<?, ?> colPrecio;

    @FXML
    private TableColumn<?, ?> colHoraConf;

    @FXML
    private TableColumn<?, ?> colHoraRec;

    @FXML
    private Label lblAcceso;

    public void initStage(Stage stage, Usuario usuario) {
        this.stage = stage;
        this.usuario = usuario;
        lblAcceso.setText("Usuario: "+ usuario.getNombre());
    }

}

