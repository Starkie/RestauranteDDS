package view_controller;

import bussiness_controller.ControladorPedidoRestaurante;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.PedidoRestaurante;
import model.Usuario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ControladorVistaPedido{

    private ControladorPedidoRestaurante controladorPedido;
    private Stage stage;
    private Usuario usuario;

    @FXML
    private Button btnNuevoPedido;

    @FXML
    private Button btnCancelarPedido;

    @FXML
    private Button btnReclamarRetraso;

    @FXML
    private TableView<PedidoRestaurante> tablaPedidos;

    @FXML
    private TableColumn<PedidoRestaurante, Long> colID;

    @FXML
    private TableColumn<PedidoRestaurante, String> colPlatos;


    @FXML
    private TableColumn<PedidoRestaurante, String> colEstado;

    @FXML
    private TableColumn<PedidoRestaurante, Double> colPrecio;

    @FXML
    private TableColumn<PedidoRestaurante, String> colHoraConf;

    @FXML
    private TableColumn<PedidoRestaurante, String> colHoraRec;

    @FXML
    private Label lblAcceso;

    public void initStage(Stage stage, Usuario usuario) {
        this.stage = stage;
        this.usuario = usuario;
        this.controladorPedido = ControladorPedidoRestaurante.getControladorPedidoRestaurante();

        iniciarLabelAcceso();
        iniciarColumnasTablaPedidos();
        poblarTablaPedidos();
        deshabilitarBotonesPorSeleccion();
    }

    private void deshabilitarBotonesPorSeleccion() {
        btnReclamarRetraso.disableProperty().bind(
                Bindings.isEmpty(tablaPedidos.getSelectionModel().getSelectedItems()));
        btnCancelarPedido.disableProperty().bind(
                Bindings.isEmpty(tablaPedidos.getSelectionModel().getSelectedItems()));
    }

    private void poblarTablaPedidos() {
        ObservableList<PedidoRestaurante> datos = FXCollections.observableArrayList(usuario.getPedidosDelUsuario());
        tablaPedidos.setItems(datos);
        tablaPedidos.refresh();
    }

    private void iniciarColumnasTablaPedidos() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyy HH:mm");
        colID.setCellValueFactory(o-> new SimpleLongProperty(o.getValue().getId()).asObject());
        colPlatos.setCellValueFactory(o-> new SimpleStringProperty(o.getValue().toString()));
        colHoraConf.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PedidoRestaurante, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PedidoRestaurante, String> param) {
                if(param.getValue().getHoraConfirmacion() == null) return null;
                else return new SimpleStringProperty(df.format(param.getValue().getHoraConfirmacion()));
            }
        });
        colHoraRec.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PedidoRestaurante, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PedidoRestaurante, String> param) {
                if(param.getValue().getHoraConfirmacion() == null) return null;
                else return new SimpleStringProperty(df.format(param.getValue().getHoraRecibido()));
            }
        });
        colPrecio.setCellValueFactory(o-> new SimpleDoubleProperty(o.getValue().getPrecio()).asObject());
        colEstado.setCellValueFactory(o-> new SimpleStringProperty(o.getValue().getEstado().getDescripcion()));
    }

    private void iniciarLabelAcceso() {
        lblAcceso.setText("Usuario: "+ usuario.getNombre());
    }

    @FXML
    void pressCancelarPedido(ActionEvent event) {
        PedidoRestaurante pedidoSeleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
        try{
            controladorPedido.CancelarPedido(pedidoSeleccionado);
            poblarTablaPedidos();
        }catch (Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alerta.showAndWait();
        }
    }

    @FXML
    void pressNuevoPedido(ActionEvent event) throws Exception{
        //La excepción no se captura, ya que quien la produciría es el load.

        PedidoRestaurante pedidoNuevo = controladorPedido.NuevoPedido(usuario);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/PlatoView.fxml"));
        Parent root = loader.load();

        ControladorVistaPlato controladorVistaPlato = loader.getController();
        controladorVistaPlato.initStage(stage, usuario, pedidoNuevo);

        stage.setTitle("Nuevo Pedido");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void pressRetraso(ActionEvent event) {
        PedidoRestaurante pedidoSeleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
        try{
            controladorPedido.reclamarRetraso(pedidoSeleccionado);
        }catch (Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alerta.showAndWait();
        }
    }

}

