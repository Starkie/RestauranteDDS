package model;

import java.util.List;

public class Usuario extends Persona{
    private String direccion;
    private List<PedidoRestaurante> pedidosDelUsuario;

    public Usuario(String nombre, int dni, String direccion) {
        super(nombre, dni);
        this.direccion = direccion;
    }

    public void addPedidoUsuario(PedidoRestaurante pedido){
        this.pedidosDelUsuario.add(pedido);
    }
}
