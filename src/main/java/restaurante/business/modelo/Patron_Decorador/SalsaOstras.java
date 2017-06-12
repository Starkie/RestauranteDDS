package restaurante.business.modelo.Patron_Decorador;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import restaurante.domain.Plato;

import javax.persistence.Entity;

@Entity
public class SalsaOstras extends DecoradorSalsa {

    public SalsaOstras() {
    }

    public SalsaOstras(Plato plato) throws Exception{
        super(plato,0,60,"Salsa de Ostras",0,0,0);
    }
    
    @Override
    public String getDescripcionSalsa() {
        return "Salsa de Ostras";
    }

    @Override
    public boolean tieneSalsa() {
        return true;
    }
    
}
