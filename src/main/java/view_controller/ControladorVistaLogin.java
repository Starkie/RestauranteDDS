
package view_controller;

import bussiness_controller.ControladorPersona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Persona;
import model.Usuario;
import restaurante.modelo.Patron_Comando.Cocinero;
import restaurante.modelo.Patron_Comando.Repartidor;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorVistaLogin implements Initializable {

    private ControladorPersona controladorPersona;
    private Stage stage;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtContraseña;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnContinue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controladorPersona = ControladorPersona.getControladorPersona();
    }

    public void initStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    void pressContinue(ActionEvent event) {
        if(txtNombre.getText().isEmpty() || txtContraseña.getText().isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Por favor, rellena los campos para acceder");
            alerta.showAndWait();
        }
        else {
            try {
                Persona p = controladorPersona.comprobarContrasenya(txtNombre.getText(), txtContraseña.getText());
                irASiguienteVentana(p);
            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alerta.showAndWait();
            }
        }
    }

    private void irASiguienteVentana(Persona p) throws Exception{
        if(p instanceof Usuario) cargarVentanaUsuario(p);
        if(p instanceof Cocinero) cargarVentanaCocinero(p);
        if(p instanceof Repartidor) cargarVentanaRepartidor(p);
    }

    private void cargarVentanaUsuario(Persona p) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/PedidosView.fxml"));
        Parent root = loader.load();

        ControladorVistaPedido controladorVistaPedido = loader.getController();
        controladorVistaPedido.initStage(stage, (Usuario)p);

        stage.setTitle("Pedidos");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    private void cargarVentanaRepartidor(Persona p) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/RepartidorView.fxml"));
        Parent root = loader.load();

        ControladorVistaRepartidor controladorVistaPedido = loader.getController();
        controladorVistaPedido.initStage(stage,(Repartidor) p);

        stage.setTitle("Repartidor");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    private void cargarVentanaCocinero(Persona p) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CocineroView.fxml"));
        Parent root = loader.load();

        ControladorVistaCocinero controladorVistaPedido = loader.getController();
        controladorVistaPedido.initStage(stage,(Cocinero) p);

        stage.setTitle("Cocinero");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void pressSalir(ActionEvent event) {
        stage.close();
    }

}
