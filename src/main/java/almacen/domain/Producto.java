package almacen.domain;

import domain.Alimento;

import javax.persistence.*;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    @ManyToOne
    private Alimento alimento;
    private double precio;
    private double cantidad;
    private UnidadesCantidad unidades;

    public Producto() {}

    public Producto(String nombre, Alimento alimento, double precio, double cantidad, UnidadesCantidad unidades) {
        this.nombre = nombre;
        this.alimento = alimento;
        this.precio = precio;
        this.cantidad = cantidad;
        this.unidades = unidades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    @Enumerated(EnumType.STRING)
    public UnidadesCantidad getUnidades() {
        return unidades;
    }

    public void setUnidades(UnidadesCantidad unidades) {
        this.unidades = unidades;
    }

    public boolean consumirProducto(double cantidadConsumir) {
        if(this.cantidad >= cantidadConsumir) {
            this.cantidad -= cantidadConsumir;
                return true;
        }
        return false;
    }
}
