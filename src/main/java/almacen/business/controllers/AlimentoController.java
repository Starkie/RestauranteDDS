package almacen.business.controllers;

import domain.Alimento;
import persistance.AlimentoService;
import persistance.AppContext;

import java.util.ArrayList;

public class AlimentoController {
    private static AlimentoController alimentoController;
    private static AlimentoService alimentoService;

    private AlimentoController() {
        alimentoController = this;
        alimentoService = (AlimentoService) AppContext.getBean("alimentoService");
    }

    public static AlimentoController getInstance() {
        if(alimentoController == null) {
            alimentoController = new AlimentoController();
        }
        return alimentoController;
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
