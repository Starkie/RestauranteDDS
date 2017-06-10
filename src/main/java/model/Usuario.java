package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario extends Persona{
    private String direccion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PedidoRestaurante> pedidosDelUsuario;

    public Usuario() {}

    public Usuario(String nombre, int dni, String direccion) {
        super(nombre, dni);
        this.direccion = direccion;
        pedidosDelUsuario = new ArrayList<>();
    }

    public List<PedidoRestaurante> getPedidosDelUsuario() {
        return pedidosDelUsuario;
    }

    public void addPedidoUsuario(PedidoRestaurante pedido){
        this.pedidosDelUsuario.add(pedido);
    }
}
