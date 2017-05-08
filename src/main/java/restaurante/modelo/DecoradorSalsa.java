package restaurante.modelo;

public abstract class DecoradorSalsa extends Decorador{

    public DecoradorSalsa (Plato plato, double precio, double calorias, String descripcion, int numeroComplementosGamba, int numeroComplementosPollo, int numeroComplementosTernera)
    throws Exception
    {
        super(plato,0.0, calorias, descripcion, numeroComplementosGamba, numeroComplementosPollo, numeroComplementosTernera);
        if(plato.tieneSalsa()) {
            throw new Exception("El platoya tiene una salsa.");
        }
    }

}
