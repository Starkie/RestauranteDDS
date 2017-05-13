package restaurante.modelo.Patron_Estado;

import model.PedidoRestaurante;
import model.Reclamacion;

import java.util.Date;

public class EstadoPendiente extends EstadoPedido{

    public EstadoPendiente() {
        super("Pendiente de Cocina");
    }

    @Override
    public void confirmarPedido(PedidoRestaurante pedido) throws Exception {
        throw new Exception("El pedido ya ha sido confirmado.");
    }

    @Override
    public void cancelarPedido(PedidoRestaurante pedido) throws Exception {
        pedido.setEstado(new EstadoCancelado());
    }

    @Override
    public Reclamacion reclamarRetraso(PedidoRestaurante pedido) throws Exception {
        Date horaActual = new Date();
        long diferenciaEnMinutos = (horaActual.getTime() - pedido.getHoraConfirmacion().getTime()) / 60000;
        if(diferenciaEnMinutos>30.0){
            return new Reclamacion(horaActual,pedido);
        }
        else{
            throw new Exception("No puede reclamar hasta que no pasen 30 minutos desde la confirmación de su pedido.");
        }
    }
}
