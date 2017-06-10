package restaurante.modelo.Patron_Decorador;

import model.Plato;

import javax.persistence.Entity;

@Entity
public abstract class DecoradorComplemento extends Decorador{

    public DecoradorComplemento() {
    }

    public DecoradorComplemento(Plato plato, double precio, double calorias, String descripcion, int numeroComplementosGamba, int numeroComplementosPollo, int numeroComplementosTernera)
    {
        super(plato,precio,calorias,descripcion,numeroComplementosGamba,numeroComplementosPollo,numeroComplementosTernera);
    }

}
