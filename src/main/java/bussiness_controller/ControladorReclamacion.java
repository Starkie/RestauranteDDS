package bussiness_controller;

import model.PedidoRestaurante;
import model.Reclamacion;
import persistance.AppContext;
import persistance.ReclamacionService;

import java.util.Date;

public class ControladorReclamacion {

    private static ControladorReclamacion controladorReclamacion;
    private ReclamacionService reclamacionService;

    private ControladorReclamacion() {
        reclamacionService = (ReclamacionService) AppContext.getBean("reclamacionService");
    }

    public static ControladorReclamacion getControladorReclamacion() {
        if(controladorReclamacion == null) controladorReclamacion = new ControladorReclamacion();
        return controladorReclamacion;
    }

    public void anyadirReclamacion(String titulo, String descripcion, PedidoRestaurante pedidoRestaurante) throws Exception {
        if(titulo.isEmpty() || descripcion.isEmpty()) throw new Exception("Por favor, rellene los campos de la Reclamación.");

        Reclamacion reclamacion = new Reclamacion();
        reclamacionService.add(reclamacion);

        reclamacion.setDescripcion(descripcion);
        reclamacion.setTitulo(titulo);
        reclamacion.setHoraReclamacion(new Date()); //Ponemos la hora actual
        reclamacion.setPedidoRestaurante(pedidoRestaurante);
        pedidoRestaurante.setReclamacion(reclamacion);

        reclamacionService.update(reclamacion);
    }
}
