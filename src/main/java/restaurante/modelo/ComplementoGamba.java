package restaurante.modelo;

public class ComplementoGamba extends DecoradorComplemento{
     
    public ComplementoGamba(Plato p) {
        super(p);
        precio = 1.50;
        calorias = 90;
    }
    
    @Override
    public int getNumeroComplementosGamba() {
        return 1 + plato.getNumeroComplementosGamba();
    }

    @Override
    public int getNumeroComplementosPollo() {
        return 0 + plato.getNumeroComplementosPollo();
    }

    @Override
    public int getNumeroComplementosTernera() {
        return 0 + plato.getNumeroComplementosTernera();
    }
}
