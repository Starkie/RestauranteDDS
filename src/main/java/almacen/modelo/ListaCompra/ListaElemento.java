package almacen.modelo.ListaCompra;

public class ListaElemento extends ListaCompra {

    public ListaElemento(int id, String nombre, String descripcion, int precio) {
        this.idElemento = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    @Override
    public double getPrecio() {
        return precio / 100.0;
    }

    @Override
    public void add(ListaCompra p) {
        throw new UnsupportedOperationException("El objeto no soporta esta operación");
    }

    @Override
    public void remove(ListaCompra p) {
        throw new UnsupportedOperationException("El objeto no soporta esta operación");
    }
}
