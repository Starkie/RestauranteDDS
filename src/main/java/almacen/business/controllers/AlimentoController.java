package almacen.business.controllers;

import domain.Alimento;
import persistance.AlimentoService;
import persistance.ServiceLocator;

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
            AlimentoService alimentoService = ServiceLocator.getAlimentoService();
            alimentoController = new AlimentoController(alimentoService);
        }
        return alimentoController;
    }

    public Alimento crearAlimento(String nombre) {
        Alimento alimento = new Alimento(nombre);
        guardarAlimento(alimento);
        return alimento;
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
