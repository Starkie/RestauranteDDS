package almacen.pedidos.domain;

import almacen.domain.Producto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Collections;
import java.util.Iterator;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class ListaElemento extends ListaCompra {
    @ManyToOne(fetch = FetchType.EAGER)
    private Producto producto;

    public ListaElemento() {}

    public ListaElemento(Producto producto, int unidades) {
        this.producto = producto;
        this.nombre = producto.getNombre();
        this.descripcion = "";
        this.precio = producto.getPrecio();
        this.unidades = unidades;
    }

    @Override
    public double getPrecio() {
        return unidades * precio;
    }

    @Override
    public void add(ListaCompra p) {
        throw new UnsupportedOperationException("El objeto no soporta esta operación");
    }

    @Override
    public void remove(ListaCompra p) {
        throw new UnsupportedOperationException("El objeto no soporta esta operación");
    }

    public Producto getProducto() {
        return producto;
    }

    @Override
    public Iterator<ListaCompra> createIterator() {
        return Collections.emptyIterator();
    }

    public String toString() {
        return "> " + unidades +"x - " + this.nombre  + " - Ud: " +  precio + "€";
    }
}
