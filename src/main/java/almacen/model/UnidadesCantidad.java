package almacen.model;

public enum UnidadesCantidad {
    KG("Kg"),
    LITRO("Litro"),
    Unidades("Ud.");

    private UnidadesCantidad(String nombre) {
        this.nombre = nombre;
    }


    private String nombre;

    public String toString() {
        return this.nombre;
    }

}
