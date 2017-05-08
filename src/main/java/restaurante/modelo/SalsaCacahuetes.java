package restaurante.modelo;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Víctor
 */
public class SalsaCacahuetes extends DecoradorSalsa{

    public SalsaCacahuetes(Plato plato) {
        super(plato);
    }

    @Override
    public String getDescripcionSalsa() {
        return "Salsa de Cacahuetes";
    }

    @Override
    public boolean tieneSalsa() {
        return true;
    }
    
}
