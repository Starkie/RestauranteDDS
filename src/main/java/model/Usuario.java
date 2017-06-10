package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
    }

    public void addPedidoUsuario(PedidoRestaurante pedido){
        this.pedidosDelUsuario.add(pedido);
    }
}
