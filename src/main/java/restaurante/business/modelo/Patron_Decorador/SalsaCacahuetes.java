package restaurante.business.modelo.Patron_Decorador;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Alimento;
import restaurante.domain.Plato;

import javax.persistence.Entity;

@Entity
public class SalsaCacahuetes extends DecoradorSalsa {

    public SalsaCacahuetes() {
        super(0,60,"Salsa de Cacahuetes",0,
                0,0);
    }

    public SalsaCacahuetes(Plato plato) throws Exception {
        super(plato,0,60,"Salsa de Cacahuetes",0,
                0,0,new Alimento("Cacahuetes"));
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
