package almacen.pedidos.domain;

import javax.persistence.*;
import java.util.Iterator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ListaCompra implements  Cloneable{
    protected String nombre;
    protected String descripcion;
    protected double precio;
    protected int unidades;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getUnidades() { return unidades; }

    public abstract double getPrecio();

    public abstract void add(ListaCompra p);

    public abstract void remove(ListaCompra p);

    public abstract Iterator<ListaCompra> createIterator();

    public abstract ListaCompra clone() throws CloneNotSupportedException;


}
