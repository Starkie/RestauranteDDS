package almacen.modelo.ListaCompra;

import java.util.ArrayList;
import java.util.List;

public class ListaCompuesto extends ListaCompra {
    private List<ListaCompra> hijos;

    public ListaCompuesto(int idElemento, String nombre, String descripcion) {
        this.idElemento = idElemento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        hijos = new ArrayList<>();
    }

    @Override
    public double getPrecio() {
        double precio = 0;
        for (ListaCompra elemento: hijos) {
            precio += elemento.getPrecio();
        }
        return precio;
    }

    @Override
    public void add(ListaCompra p) {
        hijos.add(p);
    }

    @Override
    public void remove(ListaCompra p) {
        hijos.remove(p); //TO-DO Revisar método para eliminar recursivamente?
    }
}
