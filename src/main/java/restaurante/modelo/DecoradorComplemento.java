package restaurante.modelo;

public abstract class DecoradorComplemento implements Plato{
    
    protected Plato plato;
    protected double precio;
    protected int calorias;
          
    
    public DecoradorComplemento(Plato p){
        this.plato = p;
    }
    
    @Override
    public double getPrecio() {
       return precio + plato.getPrecio();
    }

    @Override
    public double getCalorias() {
        return calorias + plato.getCalorias();
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
    
    private boolean tieneComplementos(){
        return (getNumeroComplementosGamba() + getNumeroComplementosPollo() + getNumeroComplementosTernera())>0;
    }

    public String getDescripcionBase() {
        if(plato instanceof DecoradorComplemento) return ((DecoradorComplemento)plato).getDescripcionBase();
        if(plato instanceof DecoradorSalsa) return ((DecoradorSalsa)plato).getDescripcionBase();
        else return plato.getDescripcion();
    }
    
    public String getDescripcionSalsa() {
        if(plato instanceof DecoradorComplemento) return ((DecoradorComplemento)plato).getDescripcionSalsa();
        if(plato instanceof DecoradorSalsa) return ((DecoradorSalsa)plato).getDescripcionSalsa();
        else return "";
    }
    
    public boolean tieneSalsa(){
        return plato.tieneSalsa();
    }
}
