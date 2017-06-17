package almacen.domain;

public enum UnidadesCantidad {
    KG("Kg"),
    LITRO("Litro"),
    Unidades("Ud.");

    UnidadesCantidad(String nombre) {
        this.nombre = nombre;
    }


    private String nombre;

    public String toString() {
        return this.nombre;
    }

}
