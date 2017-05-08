package restaurante.modelo;

public class ComplementoPollo extends DecoradorComplemento{
       
    public ComplementoPollo(Plato p) {
        super(p,1.0,100,"Pollo Crujiente",0,1,0);
    }

    @Override
    public int getNumeroComplementosPollo() {
        return 1 + plato.getNumeroComplementosPollo();
    }

}
