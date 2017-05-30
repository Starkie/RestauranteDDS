package almacen.modelo.pedidos;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class ListaCompuesto extends ListaCompra {
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ListaCompra> hijos;

    public ListaCompuesto(String nombre, String descripcion) {
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
        hijos.remove(p);
    }

    public String toString() {
        return "Lista: " + nombre + " - " + descripcion + " - " + getPrecio();
    }
}
