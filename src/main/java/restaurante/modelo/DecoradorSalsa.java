package restaurante.modelo;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Víctor
 */
public abstract class DecoradorSalsa implements Plato{

    protected int calorias;
    protected Plato plato;
    
    public DecoradorSalsa(Plato plato) {
        this.plato = plato;
    }
    
    @Override
    public double getPrecio() {
        return 0;
    }

    @Override
    public double getCalorias() {
        return calorias + plato.getCalorias();
    }
    
    @Override
    public int getNumeroComplementosGamba() {
        return plato.getNumeroComplementosGamba();
    }

    @Override
    public int getNumeroComplementosPollo() {
        return plato.getNumeroComplementosPollo();
    }

    @Override
    public int getNumeroComplementosTernera() {
        return plato.getNumeroComplementosTernera();
    }

    public String getDescripcionBase() {
        if(plato instanceof DecoradorComplemento) return ((DecoradorComplemento)plato).getDescripcionBase();
        if(plato instanceof DecoradorSalsa) return ((DecoradorSalsa)plato).getDescripcionBase();
        else return plato.getDescripcion();
    }
   
    @Override
    public String getDescripcion() {
        String s = getDescripcionBase();
        if(tieneComplementos()){
            s+= " con ";
                if(getNumeroComplementosGamba()>0){ s+= getNumeroComplementosGamba() + " de Gambas crujientes ";}
                if(getNumeroComplementosPollo()>0){ s+= getNumeroComplementosPollo() + " de Pollo frito ";}
                if(getNumeroComplementosTernera()>0){ s+= getNumeroComplementosTernera()+ " de Ternera asada ";}
        }
        if(tieneSalsa()) s+= getDescripcionSalsa();
        return s;
    }
    
    public abstract String getDescripcionSalsa();
    
    private boolean tieneComplementos(){
        return (getNumeroComplementosGamba() + getNumeroComplementosPollo() + getNumeroComplementosTernera())>0;
    }
}
