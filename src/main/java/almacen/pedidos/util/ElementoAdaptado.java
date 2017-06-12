package almacen.pedidos.util;

import almacen.model.Producto;

public class ElementoAdaptado {
    Producto producto;
    int unidades;

    public ElementoAdaptado(Producto producto, int unidades) {
        this.producto = producto;
        this.unidades = unidades;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getUnidades() {
        return unidades;
    }

    public boolean equals(Object o) {
        return o instanceof ElementoAdaptado
                &&  ((ElementoAdaptado) o).getProducto().equals(producto)
                &&  ((ElementoAdaptado) o).getUnidades() == unidades;
    }
}
