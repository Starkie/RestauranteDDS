package restaurante.business.bussiness_controller;

import domain.Persona;
import persistance.AppContext;
import persistance.PersonaService;
import restaurante.business.modelo.Patron_Comando.*;
import restaurante.domain.PedidoRestaurante;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ControladorPersona{

    private static ControladorPersona controladorPersona;
    private PersonaService personaService;
    private EmisorOrdenes elEmisor;

    private ControladorPersona() {
        personaService = (PersonaService) AppContext.getBean("personaService");
        elEmisor = EmisorOrdenes.getEmisorOrdenes();
    }

    public static ControladorPersona getControladorPersona(){
        if(controladorPersona == null) controladorPersona = new ControladorPersona();
        return controladorPersona;
    }

    public Persona comprobarContrasenya(String nombre, String contraseña) throws Exception{
        Persona p = personaService.findByName(nombre);
        if(p==null) throw new Exception("No estás registrado en el sistema");
        else{
            if(p.getContraseña().equals(contraseña)) return p;
            else throw new Exception("Contraseña incorrecta");
        }
    }

    public List<PedidoRestaurante> obtenerPendientesRepartidor() {
        List<PedidoRestaurante> pedidosPorRepartir = new ArrayList<PedidoRestaurante>();
        Iterator<OrdenRepartir> OrdenesPorRepartir = elEmisor.getOrdenesARepartir().iterator();
        while(OrdenesPorRepartir.hasNext()){
            pedidosPorRepartir.add(OrdenesPorRepartir.next().getPedido());
        }
        return pedidosPorRepartir;
    }

    public List<PedidoRestaurante> obtenerPendientesCocinero() {
        List<PedidoRestaurante> pedidosPorCocinar = new ArrayList<PedidoRestaurante>();
        Iterator<OrdenCocinar> OrdenesPorCocinar = elEmisor.getOrdenesACocinar().iterator();
        while(OrdenesPorCocinar.hasNext()){
            pedidosPorCocinar.add(OrdenesPorCocinar.next().getPedido());
        }
        return pedidosPorCocinar;
    }

    public void finalizarActualRepartidor(Repartidor repartidor) {
        repartidor.finalizarEnvio();
        personaService.update(repartidor);
    }


    public void finalizarActualCocinero(Cocinero cocinero) {
        cocinero.finalizarCocinaPedido();
        personaService.update(cocinero);
    }
}
