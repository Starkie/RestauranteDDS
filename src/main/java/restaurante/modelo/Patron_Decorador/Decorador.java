package restaurante.modelo.Patron_Decorador;

import model.Plato;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public abstract class Decorador extends Plato {
    @OneToOne(cascade = CascadeType.ALL)
    protected Plato plato;

    public Decorador() {
    }

    public Decorador(Plato plato, double precio, double calorias, String descripcion, int numeroComplementosGamba, int numeroComplementosPollo, int numeroComplementosTernera)
    {
        super(precio,calorias,descripcion,numeroComplementosGamba,numeroComplementosPollo,numeroComplementosTernera);
        this.plato=plato;
    }

    public Decorador(double precio, double calorias, String descripcion, int numeroComplementosGamba, int numeroComplementosPollo, int numeroComplementosTernera) {
        super(precio,calorias,descripcion,numeroComplementosGamba,numeroComplementosPollo,numeroComplementosTernera);
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
        s = s.trim();
        if(tieneSalsa() & tieneComplementos()) s+= " y " + getDescripcionSalsa();
        if(tieneSalsa() & !tieneComplementos()) s+= " con " + getDescripcionSalsa();

        s = s.replace("1","uno");
        s = s.replace("2","doble");
        s = s.replace("3","triple");
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

    public Plato getPlato(){
        return plato;
    }
}
