package almacen.modelo;

import model.Alimento;

public class Producto {
    private String nombre, descripcion;
    private Alimento alimento;
    private Proveedor proveedor;
    private int precio;
    private double cantidad;
    private UnidadesCantidad unidades;

    public Producto() {}

    public Producto(String nombre, Alimento alimento, Proveedor proveedor, int precio, double cantidad, UnidadesCantidad unidades) {
        this.nombre = nombre;
        this.alimento = alimento;
        this.proveedor = proveedor;
        this.precio = precio;
        this.cantidad = cantidad;
        this.unidades = unidades;
    }

    public Producto(String nombre, String descripcion, Alimento alimento, Proveedor proveedor, int precio, double cantidad, UnidadesCantidad unidades) {
        this(nombre, alimento, proveedor, precio, cantidad, unidades);
        this.descripcion = descripcion;
    }


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

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

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
