package almacen.pedidos.MainScreen.InfoPedidos;


import almacen.model.Producto;
import almacen.pedidos.model.ListaCompra;
import almacen.pedidos.model.ListaElemento;
import almacen.pedidos.model.Pedido;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class ResumenPedidoController implements Initializable {

    @FXML
    private TableView tablaProductos;
    @FXML
    private TableColumn<FilaTabla, Number> unidadesProductoColumn;
    @FXML
    private TableColumn<FilaTabla, String> nombreColumn;
    @FXML
    private TableColumn<FilaTabla, String> alimentoColumn;
    @FXML
    private TableColumn<FilaTabla, Number> cantidadColumn;
    @FXML
    private TableColumn<FilaTabla, String> unidadColumn;
    @FXML
    private TableColumn<FilaTabla, Number> precioColumn;
    @FXML
    private Label tituloLabel;
    @FXML
    private Label estadoLabel;
    @FXML
    private Label precioLabel;


    private ObservableList<FilaTabla> listaProductos;

    private Pedido pedido;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        unidadesProductoColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().unidades));

        nombreColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().producto.getNombre()));

        alimentoColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().producto.getAlimento().getNombre()));

        cantidadColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().producto.getCantidad()));

        unidadColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().producto.getUnidades().toString()));

        precioColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().producto.getPrecio()));

    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        this.tituloLabel.setText("Resumen de Pedido #" + pedido.getId());
        this.estadoLabel.setText(pedido.getEstado().toString());
        this.precioLabel.setText("" + pedido.getPrecio());
        refreshTable(pedido);
    }

    public void refreshTable(Pedido pedido) {
        List<FilaTabla> lista = AdaptadorListaCompra.adaptarListaCompra(pedido.getLista());
        listaProductos = FXCollections.observableArrayList(lista);
        tablaProductos.setItems(listaProductos);
    }

}
class AdaptadorListaCompra {
    public static  List<FilaTabla> adaptarListaCompra(ListaCompra listaCompra) {
        ArrayList<FilaTabla> listaFilas = new ArrayList<>();
        Iterator<ListaCompra> listaCompraIterator = listaCompra.createIterator();
        listaCompraIterator.forEachRemaining(el ->
                {
                    if (el instanceof ListaElemento) {
                        listaFilas.add(
                                new FilaTabla(
                                        ((ListaElemento) el).getProducto(),
                                        ((ListaElemento) el).getUnidades()
                                )
                        );
                    }
                }
        );
        return listaFilas;
    }

}

class FilaTabla {
    Producto producto;
    int unidades;

    public FilaTabla(Producto producto, int unidades) {
        this.producto = producto;
        this.unidades = unidades;
    }
}
