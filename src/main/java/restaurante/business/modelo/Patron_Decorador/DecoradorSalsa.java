package restaurante.business.modelo.Patron_Decorador;

import restaurante.domain.Plato;

import javax.persistence.Entity;

@Entity
public abstract class DecoradorSalsa extends Decorador{

    public DecoradorSalsa() {
    }

    public DecoradorSalsa (Plato plato, double precio, double calorias, String descripcion, int numeroComplementosGamba, int numeroComplementosPollo, int numeroComplementosTernera)
            throws Exception
    {
        super(plato,0.0, calorias, descripcion, numeroComplementosGamba, numeroComplementosPollo, numeroComplementosTernera);
        if(plato.tieneSalsa()) {
            throw new SalsaException();
        }
    }

    public DecoradorSalsa (double precio, double calorias, String descripcion, int numeroComplementosGamba, int numeroComplementosPollo, int numeroComplementosTernera)
    {
        super(0.0, calorias, descripcion, numeroComplementosGamba, numeroComplementosPollo, numeroComplementosTernera);
    }

    public void setPlato(Plato plato) throws Exception{
        if(plato.tieneSalsa()) {
            throw new SalsaException();
        }
        this.plato = plato;
    }

}
