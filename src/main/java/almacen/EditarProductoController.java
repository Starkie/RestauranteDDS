package almacen;

import almacen.model.UnidadesCantidad;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import model.Alimento;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EditarProductoController implements Initializable {

    @FXML
    private TextField nombreText;

    @FXML
    private TextField descripcionText;

    @FXML
    private ComboBox<Alimento> alimentoCombo;

    @FXML
    private TextField precioText;

    @FXML
    private TextField cantidadText;

    @FXML
    private ComboBox<UnidadesCantidad> unidadesCombo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
