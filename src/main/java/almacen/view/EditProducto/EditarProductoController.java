package almacen.view.EditProducto;

import almacen.business.controllers.AlimentoController;
import almacen.business.controllers.ProductoAlmacenController;
import almacen.domain.Producto;
import almacen.domain.ProductoAlmacen;
import almacen.domain.UnidadesCantidad;
import domain.Alimento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditarProductoController implements Initializable {

    @FXML
    private TextField nombreText;

    @FXML
    private ComboBox<Alimento> alimentoCombo;

    @FXML
    private TextField precioText;

    @FXML
    private TextField cantidadText;

    @FXML
    private ComboBox<UnidadesCantidad> unidadesCombo;

    @FXML
    private TextField stockText;

    @FXML
    private Button cancelarButton;

    @FXML
    private Button aceptarButton;

    @FXML
    private Button crearAlimentoButton;

    @FXML
    private Label tituloLabel;

    private ProductoAlmacen productoAlmacen;

    private boolean editarProducto = false;

    private ProductoAlmacenController productoAlmacenController;

    private AlimentoController alimentoController;

    private ObservableList<Alimento> alimentoObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alimentoController = AlimentoController.getInstance();
        refreshComboAlimento();

        ArrayList<UnidadesCantidad> unidadesCantidadList = new ArrayList<>();
        unidadesCantidadList.add(UnidadesCantidad.KG);
        unidadesCantidadList.add(UnidadesCantidad.LITRO);
        unidadesCantidadList.add(UnidadesCantidad.Unidades);
        unidadesCombo.setItems(FXCollections.observableArrayList(unidadesCantidadList));

        unidadesCombo.getSelectionModel().select(0);
        DecimalFormat format = new DecimalFormat( "#.0" );
        precioText.setTextFormatter( new TextFormatter<>(c ->
        {
            if ( c.getControlNewText().isEmpty() )
            {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = format.parse( c.getControlNewText(), parsePosition );

            if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
            {
                return null;
            }
            else
            {
                return c;
            }
        }));

        cantidadText.setTextFormatter( new TextFormatter<>(c ->
        {
            if ( c.getControlNewText().isEmpty() )
            {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = format.parse( c.getControlNewText(), parsePosition );

            if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
            {
                return null;
            }
            else
            {
                return c;
            }
        }));

        stockText.setTextFormatter( new TextFormatter<>(c ->
        {
            if ( c.getControlNewText().isEmpty() )
            {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = format.parse( c.getControlNewText(), parsePosition );

            if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
            {
                return null;
            }
            else
            {
                return c;
            }
        }));

        precioText.setText("0.0");
        cantidadText.setText("0");
        cantidadText.setText("0");

        productoAlmacen = new ProductoAlmacen(new Producto(), 0);

        productoAlmacenController = ProductoAlmacenController.getInstance();
    }

    public void refreshComboAlimento() {
        alimentoObservableList = FXCollections.observableList(alimentoController.getAllAlimentos());
        alimentoCombo.setItems(alimentoObservableList);
    }

    public void setProductoAlmacen(ProductoAlmacen productoAlmacen) {
        this.productoAlmacen = productoAlmacen;
        editarProducto = true;

        tituloLabel.setText("Editar Producto");
        nombreText.setText(productoAlmacen.getProducto().getNombre());
        alimentoCombo.getSelectionModel().select(productoAlmacen.getProducto().getAlimento());
        precioText.setText("" + productoAlmacen.getProducto().getPrecio());
        cantidadText.setText("" + productoAlmacen.getProducto().getCantidad());
        unidadesCombo.getSelectionModel().select(productoAlmacen.getProducto().getUnidades());
        stockText.setText("" + productoAlmacen.getStock());

    }

    @FXML
    public void OnClickAceptarButton() {
        if(noHayCamposVacios()) {
            obtenerDatosCampos();
            productoAlmacenController.guardarProducto(productoAlmacen);
            Stage stage = (Stage) cancelarButton.getScene().getWindow();
            stage.close();
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hay campos vacios");
            alert.setHeaderText("Hay Campos Vacios");
            alert.setContentText("Debe rellenar todos los campos para continuar.");
            alert.showAndWait();
        }
    }

    private void obtenerDatosCampos() {
        productoAlmacen.getProducto().setNombre(nombreText.getText());
        productoAlmacen.getProducto().setAlimento(alimentoCombo.getSelectionModel().getSelectedItem());
        productoAlmacen.getProducto().setPrecio( Double.parseDouble(precioText.getText()));
        productoAlmacen.getProducto().setCantidad(Double.parseDouble(cantidadText.getText()));
        productoAlmacen.getProducto().setUnidades(unidadesCombo.getSelectionModel().getSelectedItem());
        productoAlmacen.setSock((int) Double.parseDouble(stockText.getText()));
    }

    private boolean noHayCamposVacios() {
        return !nombreText.getText().isEmpty() && !precioText.getText().isEmpty() && !cantidadText.getText().isEmpty() && !stockText.getText().isEmpty();
    }

    @FXML
    public void OnCrearAlimentoClick() {
        Optional<String> result = promptNombreAlimento();
        if(result.isPresent()) {
            String nombreAlimento = result.get();

            Optional<ButtonType> crearAlimento = confirmarCrearAlimento(nombreAlimento);
            if(crearAlimento.get() == ButtonType.OK) {
                Alimento nuevoAlimento = alimentoController.crearAlimento(nombreAlimento);
                refreshComboAlimento();
                alimentoCombo.getSelectionModel().select(nuevoAlimento);
            }
        }
    }

    public Optional<ButtonType> confirmarCrearAlimento(String nombreAlimento) {
        Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
        confirmar.setTitle("Crear Alimento");
        confirmar.setHeaderText("Confirme creación Alimento");
        confirmar.setContentText("¿Desea crear el alimento \"" + nombreAlimento + "\"?");
        return confirmar.showAndWait();
    }

    public Optional<String> promptNombreAlimento() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Crear Alimento");
        dialog.setHeaderText("Crear Nuevo Alimento");
        dialog.setContentText("Nombre:");
        return dialog.showAndWait();
    }

    @FXML
    public void OnCancelarClickButton() {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }
}
