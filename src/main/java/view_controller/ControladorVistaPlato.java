package view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.PedidoRestaurante;
import model.Usuario;

public class ControladorVistaPlato {

    private Usuario usuario;
    private Stage stage;
    private PedidoRestaurante pedido;

    @FXML
    private TableView<?> tablaPlatos;

    @FXML
    private TableColumn<?, ?> colPlato;

    @FXML
    private TableColumn<?, ?> colPrecio;

    @FXML
    private TableColumn<?, ?> colCalorias;

    @FXML
    private Label precioTotal;

    @FXML
    private Label CaloriasTotal;

    @FXML
    void pressArroz(ActionEvent event) {

    }

    @FXML
    void pressCacahuetes(ActionEvent event) {

    }

    @FXML
    void pressCancelar(ActionEvent event) {

    }

    @FXML
    void pressConfirmar(ActionEvent event) {

    }

    @FXML
    void pressEliminarPlato(ActionEvent event) {

    }

    @FXML
    void pressGambas(ActionEvent event) {

    }

    @FXML
    void pressNuevoPlato(ActionEvent event) {

    }

    @FXML
    void pressOstras(ActionEvent event) {

    }

    @FXML
    void pressPollo(ActionEvent event) {

    }

    @FXML
    void pressTallarines(ActionEvent event) {

    }

    @FXML
    void pressTernera(ActionEvent event) {

    }

    public void initStage(Stage stage, Usuario usuario, PedidoRestaurante pedido) {
        this.stage = stage;
        this.usuario = usuario;
        this.pedido = pedido;
    }
}
