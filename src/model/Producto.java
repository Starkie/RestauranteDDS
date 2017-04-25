package model;

public class Producto {
    private String nombre, descripcion;
    private Alimento alimento;
    private Proveedor proveedor;
    private int precio;

    public Producto(String nombre, Alimento alimento, Proveedor proveedor, int precio) {
        this.nombre = nombre;
        this.alimento = alimento;
        this.proveedor = proveedor;
        this.precio = precio;
    }

    public Producto(String nombre, String descripcion, Alimento alimento, Proveedor proveedor, int precio) {
        this(nombre, alimento, proveedor, precio);
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
}
