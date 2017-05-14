package almacen.modelo.pedidos;

import almacen.modelo.Producto;

import java.util.List;

public class GestorPedidos {
    private static GestorPedidos gestorPedidos;

    private GestorPedidos() {}

    public static GestorPedidos getInstance() {
        if(gestorPedidos == null) {
            gestorPedidos = new GestorPedidos();
        }
        return gestorPedidos;
    }

    public Pedido crearPedido(List<Producto> productos) {
        ListaCompra listaCompra = new ListaCompuesto("Pedido", "Lista Productos");
        for(Producto p : productos) {
            listaCompra.add(new ListaElemento(p.getNombre(), p.getDescripcion(), 1, p.getPrecio()));
        }
        return new Pedido(listaCompra);
    }

    public Pedido crearPedido(ListaCompra listaCompra) {
        return new Pedido(listaCompra);
    }

    public void addToPedido(Pedido pedido, ListaCompra lista) {
        pedido.addToPedido(lista);
    }

    public void addToPedido(Pedido pedido, Producto producto) {
        pedido.addToPedido(new ListaElemento(producto.getNombre(), producto.getDescripcion(), 1, producto.getPrecio()));
    }

    public void addToPedido(Pedido pedido, List<Producto> productos) {
        for(Producto p : productos) {
            addToPedido(pedido, p);
        }
    }

    public void addProductoToPedido(Pedido pedido, Producto producto) {
        ListaElemento listaElemento = new ListaElemento(producto.getNombre(), producto.getDescripcion(), 1, producto.getPrecio());
        addToPedido(pedido, listaElemento);
    }

    public void confirmarPedido(Pedido pedido) throws AlmacenException{
        pedido.confirmarPedido();
    }

    public void recibirPedido(Pedido pedido) throws AlmacenException {
        pedido.recibirPedido();
    }

    public void cancelarPedido(Pedido pedido) throws AlmacenException {
        pedido.cancelarPedido();
    }

}
