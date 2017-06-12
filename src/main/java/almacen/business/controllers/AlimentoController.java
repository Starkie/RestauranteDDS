package almacen.business.controllers;

import domain.Alimento;
import persistance.AlimentoService;
import persistance.AppContext;

import java.util.ArrayList;

public class AlimentoController {
    private static AlimentoController alimentoController;
    private static AlimentoService alimentoService;

    private AlimentoController(AlimentoService alimentoService) {
        this.alimentoController = this;
        this.alimentoService = alimentoService;
    }

    public static AlimentoController getInstance() {
        if(alimentoController == null) {
            AlimentoService alimentoService = (AlimentoService) AppContext.getBean("alimentoService");
            alimentoController = new AlimentoController(alimentoService);
        }
        return alimentoController;
    }

    public void crearAlimento(String nombre) {
        Alimento alimento = new Alimento(nombre);
        guardarAlimento(alimento);
    }

    public void guardarAlimento(Alimento alimento) {
        alimentoService.add(alimento);
    }

    public ArrayList<Alimento> getAllAlimentos() {
        ArrayList<Alimento> alimentos = new ArrayList<>();
        alimentoService.findAll().forEach(a -> alimentos.add(a));
        return alimentos;
    }

}
