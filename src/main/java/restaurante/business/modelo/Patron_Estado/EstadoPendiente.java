package restaurante.business.modelo.Patron_Estado;

import almacen.business.controllers.AlimentoController;
import domain.Alimento;
import restaurante.domain.PedidoRestaurante;
import restaurante.domain.Reclamacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("Pendiente")
public class EstadoPendiente extends EstadoPedido {

    public EstadoPendiente() {
        super("Pendiente de Cocina");
    }

    @Override
    public void confirmarPedido(PedidoRestaurante pedido) throws Exception {
        throw new Exception("El pedido ya ha sido confirmado.");
    }

    @Override
    public void cancelarPedido(PedidoRestaurante pedido) throws Exception {
        AlimentoController alimentoController = AlimentoController.getInstance();

        List<Alimento> alimentosDelPedido = new ArrayList<Alimento>();
        pedido.getPlatosPedido().forEach(plato-> plato.getAlimentosPlato().forEach(alimentoPlato -> alimentosDelPedido.add(alimentoPlato)));
        
        alimentoController.devolverAlStock(alimentosDelPedido);
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
