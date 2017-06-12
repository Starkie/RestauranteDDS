package restaurante.view.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import restaurante.business.bussiness_controller.ControladorReclamacion;
import restaurante.domain.PedidoRestaurante;
import restaurante.domain.Reclamacion;
import restaurante.domain.Usuario;

import java.io.IOException;

public class ControladorVistaReclamacion {

    private Stage stage;
    private Usuario usuario;
    private PedidoRestaurante pedidoRestaurante;
    private Reclamacion reclamacion;
    private ControladorReclamacion controladorReclamacion;

    @FXML
    private Label lblPedidoReclamado;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnContinuar;

    @FXML
    void pressContinuar(ActionEvent event){
        try {
            controladorReclamacion.anyadirReclamacion(txtTitulo.getText(), txtDescripcion.getText(), pedidoRestaurante);
            volverAPedidos();
        }catch (Exception e){ //Obligatorio rellenar los campos
            Alert alerta = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alerta.showAndWait();
        }
    }

    @FXML
    void pressVolver(ActionEvent event) throws IOException {
        volverAPedidos();
    }

    private void volverAPedidos() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/restaurante/view/view_files/PedidosView.fxml"));
        Parent root = loader.load();

        ControladorVistaPedido controladorVistaPedido = loader.getController();
        controladorVistaPedido.initStage(stage, usuario);

        stage.setTitle("Pedidos");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void initStage(Stage stage, Usuario usuario, PedidoRestaurante pedido, Reclamacion reclamacion) {
        this.pedidoRestaurante = pedido;
        this.usuario = usuario;
        this.stage = stage;
        this.reclamacion = reclamacion;
        this.controladorReclamacion = ControladorReclamacion.getControladorReclamacion();

        rellenarCampos();
    }

    private void rellenarCampos() {
        if(reclamacion.getTitulo() != null) txtTitulo.setText(reclamacion.getTitulo());
        if(reclamacion.getDescripcion() != null) txtDescripcion.setText(reclamacion.getDescripcion());
        lblPedidoReclamado.setText("Incidencia en el pedido: " + pedidoRestaurante.getId());
    }
}
