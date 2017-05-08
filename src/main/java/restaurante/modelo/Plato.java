package restaurante.modelo;

public interface Plato {
     double getPrecio();
     
     double getCalorias();
     
     int getNumeroComplementosGamba();
     
     int getNumeroComplementosPollo();
     
     int getNumeroComplementosTernera();
     
     String getDescripcion();
     
     boolean tieneSalsa();
     
}
