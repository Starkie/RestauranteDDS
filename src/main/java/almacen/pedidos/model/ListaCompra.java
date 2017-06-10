package almacen.pedidos.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ListaCompra {
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

    public  abstract double getPrecio();

    public abstract void add(ListaCompra p);

    public abstract void remove(ListaCompra p);

}
