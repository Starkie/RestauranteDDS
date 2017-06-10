package almacen.pedidos.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class ListaElemento extends ListaCompra {

    public ListaElemento() {}

    public ListaElemento(String nombre, String descripcion, int unidades, double precioUnidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precioUnidad;
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

    public String toString() {
        return "> " + unidades +"x - " + this.nombre + ": " + descripcion + " - " + getPrecio() + "€";
    }
}
