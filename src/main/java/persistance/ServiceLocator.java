package persistance;

import almacen.persistance.ProductoAlmacenService;
import almacen.persistance.ProductoService;
import almacen.persistance.pedidos.PedidoService;
import persistance.AlimentoService;
import persistance.AppContext;
import persistance.PersonaService;
import restaurante.persistance.PedidoRestauranteService;
import restaurante.persistance.PlatoService;
import restaurante.persistance.ReclamacionService;

public class ServiceLocator {


    public static final String ALIMENTO_SERVICE = "alimentoService";
    public static final String PEDIDO_SERVICE = "pedidoService";
    public static final String PEDIDORESTAURANTE_SERVICE = "pedidoRestauranteService";
    public static final String PERSONA_SERVICE = "personaService";
    public static final String PLATO_SERVICE =  "platoService";
    public static final String PRODUCTO_SERVICE = "productoService";
    public static final String PRODUCTOALMACEN_SERVICE = "productoAlmacenService";
    public static final String RECLAMACION_SERVICE = "reclamacionService";

    private ServiceLocator() {}

    public static AlimentoService getAlimentoService() {
        return (AlimentoService) AppContext.getBean(ALIMENTO_SERVICE);
    }

    public static PedidoService getPedidoService() {
        return (PedidoService) AppContext.getBean(PEDIDO_SERVICE);
    }

    public static PedidoRestauranteService getPedidoRestauranteService() {
        return (PedidoRestauranteService) AppContext.getBean(PEDIDORESTAURANTE_SERVICE);
    }

    public static PersonaService getPersonaService() {
        return (PersonaService) AppContext.getBean(PERSONA_SERVICE);
    }

    public static PlatoService getPlatoService() {
        return (PlatoService) AppContext.getBean(PLATO_SERVICE);
    }

    public static ProductoService getProductoService() {
        return (ProductoService) AppContext.getBean(PRODUCTO_SERVICE);
    }

    public static ProductoAlmacenService getProductoAlmacenService() {
        return (ProductoAlmacenService) AppContext.getBean(PRODUCTOALMACEN_SERVICE);
    }

    public static ReclamacionService getReclamacionService() {
        return (ReclamacionService) AppContext.getBean(RECLAMACION_SERVICE);
    }

}
