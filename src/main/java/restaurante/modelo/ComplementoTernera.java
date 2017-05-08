package restaurante.modelo;

public class ComplementoTernera extends DecoradorComplemento{
       
    public ComplementoTernera(Plato p) {
        super(p);
        precio= 1.50;
        calorias = 120;
    }
    
    @Override
    public int getNumeroComplementosGamba() {
        return 0 + plato.getNumeroComplementosGamba();
    }

    @Override
    public int getNumeroComplementosPollo() {
        return 0 + plato.getNumeroComplementosPollo();
    }

    @Override
    public int getNumeroComplementosTernera() {
        return 1 + plato.getNumeroComplementosTernera();
    }
    
}
