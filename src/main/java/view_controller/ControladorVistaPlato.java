package view_controller;

import bussiness_controller.ControladorPlato;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.PedidoRestaurante;
import model.Plato;
import model.Usuario;

public class ControladorVistaPlato {

    private Usuario usuario;
    private Stage stage;
    private PedidoRestaurante pedido;
    private ControladorPlato controladorPlato;

    @FXML
    private Button btnPollo;

    @FXML
    private Button btnTernera;

    @FXML
    private Button btnGambas;

    @FXML
    private Button btnCacahuetes;

    @FXML
    private Button btnOstras;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnEliminarPlato;

    @FXML
    private TableView<Plato> tablaPlatos;

    @FXML
    private TableColumn<Plato, String> colPlato;

    @FXML
    private TableColumn<Plato, Double> colPrecio;

    @FXML
    private TableColumn<Plato, Double> colCalorias;

    @FXML
    private Label precioTotal;

    @FXML
    private Label CaloriasTotal;

    @FXML
    void pressArroz(ActionEvent event) {
        controladorPlato.crearPlatoArroz(pedido);
        actualizarTablaPlatos();
    }

    @FXML
    void pressTallarines(ActionEvent event) {
        controladorPlato.crearPlatoTallarines(pedido);
        actualizarTablaPlatos();
    }

    @FXML
    void pressCancelar(ActionEvent event) throws Exception {
        //Aparece como que lanza excepción, pero solo lo lanza en caso de que el pedido no se pueda cancelar.
        //Un pedido nuevo SI se puede cancelar.
        controladorPlato.cancelarPedido(pedido);
        irAPedidos();
    }

    @FXML
    void pressConfirmar(ActionEvent event){
        //Aparece como que lanza excepción, pero solo lo lanza en caso de que el pedido no se pueda confirmar.
        //Un pedido nuevo no se puede confirmar si no tiene platos.
        try {
            controladorPlato.confirmarPedido(pedido);
            irAPedidos();
        }catch (Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alerta.showAndWait();
        }
    }

    private void irAPedidos() throws java.io.IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/PedidosView.fxml"));
        Parent root = loader.load();

        ControladorVistaPedido controladorVistaPedido = loader.getController();
        controladorVistaPedido.initStage(stage, usuario);

        stage.setTitle("Pedidos");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void pressEliminarPlato(ActionEvent event) {
        Plato seleccionado = tablaPlatos.getSelectionModel().getSelectedItem();
        controladorPlato.eliminarPlatoDelPedido(seleccionado,pedido);
        actualizarTablaPlatos();
    }

    @FXML
    void pressPollo(ActionEvent event) {
        Plato seleccionado = tablaPlatos.getSelectionModel().getSelectedItem();
        controladorPlato.anyadirPollo(seleccionado,pedido);
        actualizarTablaPlatos();
    }

    @FXML
    void pressTernera(ActionEvent event) {
        Plato seleccionado = tablaPlatos.getSelectionModel().getSelectedItem();
        controladorPlato.anyadirTernera(seleccionado,pedido);
        actualizarTablaPlatos();
    }

    @FXML
    void pressGambas(ActionEvent event) {
        Plato seleccionado = tablaPlatos.getSelectionModel().getSelectedItem();
        controladorPlato.anyadirGambas(seleccionado,pedido);
        actualizarTablaPlatos();
    }

    @FXML
    void pressOstras(ActionEvent event) {
        try {
            Plato seleccionado = tablaPlatos.getSelectionModel().getSelectedItem();
            controladorPlato.anyadirOstras(seleccionado, pedido);
            actualizarTablaPlatos();
        }catch(Exception e){ //Doble salsa
            Alert alerta = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alerta.showAndWait();
        }
    }

    @FXML
    void pressCacahuetes(ActionEvent event) {
        try {
            Plato seleccionado = tablaPlatos.getSelectionModel().getSelectedItem();
            controladorPlato.anyadirCacahuetes(seleccionado, pedido);
            actualizarTablaPlatos();
        }catch(Exception e){ //Doble salsa
            Alert alerta = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alerta.showAndWait();
        }
    }

    public void initStage(Stage stage, Usuario usuario, PedidoRestaurante pedido) {
        this.stage = stage;
        this.usuario = usuario;
        this.pedido = pedido;
        this.controladorPlato = ControladorPlato.getControladorPlato();

        iniciarColumnasTablaPedidos();
        actualizarTablaPlatos();
        deshabilitarBotonesPorSeleccion();

    }

    private void actualizarTablaPlatos(){
        ObservableList<Plato> datos = FXCollections.observableArrayList(pedido.getPlatosPedido());
        tablaPlatos.setItems(datos);
        tablaPlatos.refresh();
        precioTotal.setText(pedido.getPrecio()+" €");
        CaloriasTotal.setText(pedido.getCalorias() + " kcal.");
    }

    private void iniciarColumnasTablaPedidos() {
        colPlato.setCellValueFactory(o-> new SimpleStringProperty(o.getValue().getDescripcion()));
        colPrecio.setCellValueFactory(o-> new SimpleDoubleProperty(o.getValue().getPrecio()).asObject());
        colCalorias.setCellValueFactory(o-> new SimpleDoubleProperty(o.getValue().getCalorias()).asObject());
    }

    private void deshabilitarBotonesPorSeleccion() {
        btnOstras.disableProperty().bind(
                Bindings.isEmpty(tablaPlatos.getSelectionModel().getSelectedItems()));
        btnCacahuetes.disableProperty().bind(
                Bindings.isEmpty(tablaPlatos.getSelectionModel().getSelectedItems()));
        btnGambas.disableProperty().bind(
                Bindings.isEmpty(tablaPlatos.getSelectionModel().getSelectedItems()));
        btnPollo.disableProperty().bind(
                Bindings.isEmpty(tablaPlatos.getSelectionModel().getSelectedItems()));
        btnTernera.disableProperty().bind(
                Bindings.isEmpty(tablaPlatos.getSelectionModel().getSelectedItems()));
        btnEliminarPlato.disableProperty().bind(
                Bindings.isEmpty(tablaPlatos.getSelectionModel().getSelectedItems()));
    }
}
