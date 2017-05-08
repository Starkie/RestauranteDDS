package restaurante.modelo;

public class ComplementoPollo extends DecoradorComplemento{
       
    public ComplementoPollo(Plato p) {
        super(p);
        precio = 1.00;
        calorias = 100;
    }
    
    @Override
    public int getNumeroComplementosGamba() {
        return 0 + plato.getNumeroComplementosGamba();
    }

    @Override
    public int getNumeroComplementosPollo() {
        return 1 + plato.getNumeroComplementosPollo();
    }

    @Override
    public int getNumeroComplementosTernera() {
        return 0 + plato.getNumeroComplementosTernera();
    }
    
    
}
