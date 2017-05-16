package restaurante.modelo.Patron_Estado;

import model.PedidoRestaurante;
import model.Reclamacion;

import java.util.Date;

public class EstadoEntregado extends EstadoPedido{

    public EstadoEntregado() {
        super("Pedido recibido.");
    }

    @Override
    public void confirmarPedido(PedidoRestaurante pedido) throws Exception {
        throw new Exception("El pedido ya ha sido recibido");
    }

    @Override
    public void cancelarPedido(PedidoRestaurante pedido) throws Exception {
        throw new Exception("El pedido ya ha sido recibido");
    }

    @Override
    public Reclamacion reclamarRetraso(PedidoRestaurante pedido) throws Exception {
        Date horaRecibido = pedido.getHoraRecibido();
        long diferenciaEnMinutos = (horaRecibido.getTime() - pedido.getHoraConfirmacion().getTime()) / 60000;
        if(diferenciaEnMinutos>30.0){
            return new Reclamacion(horaRecibido,pedido);
        }
        else{
            throw new Exception("No puede reclamar si no hay una diferencia de 30 minutos entre su pedido y la recepción de éste.");
        }
    }
}
