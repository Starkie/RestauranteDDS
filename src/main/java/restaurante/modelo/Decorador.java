package restaurante.modelo;

public abstract class Decorador extends Plato {
    protected Plato plato;

    public Decorador(Plato plato, double precio, double calorias, String descripcion, int numeroComplementosGamba, int numeroComplementosPollo, int numeroComplementosTernera)
    {
        super(precio,calorias,descripcion,numeroComplementosGamba,numeroComplementosPollo,numeroComplementosTernera);
        this.plato=plato;
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
    public int getNumeroComplementosGamba() {
        return  plato.getNumeroComplementosGamba();
    }

    @Override
    public int getNumeroComplementosPollo() {
        return  plato.getNumeroComplementosPollo();
    }

    @Override
    public int getNumeroComplementosTernera() {
        return  plato.getNumeroComplementosTernera();
    }

    @Override
    public boolean tieneSalsa(){ return plato.tieneSalsa(); }

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

    protected String getDescripcionBase() {
        if(plato instanceof Decorador) return ((Decorador)plato).getDescripcionBase();
        else return plato.getDescripcion();
    }

    protected String getDescripcionSalsa() {
        if(plato instanceof DecoradorSalsa) return ((DecoradorSalsa)plato).getDescripcionSalsa();
        if(plato instanceof DecoradorComplemento) return ((DecoradorComplemento) plato).getDescripcionSalsa();
        else return "";
    }

    private boolean tieneComplementos(){
        return (getNumeroComplementosGamba() + getNumeroComplementosPollo() + getNumeroComplementosTernera())>0;
    }

}
