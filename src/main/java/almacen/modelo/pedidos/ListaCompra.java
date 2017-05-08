package almacen.modelo.pedidos;

public abstract class ListaCompra {
    protected int idElemento;
    protected String nombre;
    protected String descripcion;
    protected int precio;

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

    public int getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }

    public  abstract double getPrecio();

    public abstract void add(ListaCompra p);

    public abstract void remove(ListaCompra p);

}
