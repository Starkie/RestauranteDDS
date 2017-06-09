package model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Usuario extends Persona{
    private String direccion;

    @OneToMany(mappedBy = "usuario")
    private List<PedidoRestaurante> pedidosDelUsuario;

    public Usuario() {}

    public Usuario(String nombre, int dni, String direccion) {
        super(nombre, dni);
        this.direccion = direccion;
    }

    public void addPedidoUsuario(PedidoRestaurante pedido){
        this.pedidosDelUsuario.add(pedido);
    }
}
