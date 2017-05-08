package restaurante.modelo;

public class BaseTallarines implements Plato{
  
    private double precio;
    private int calorias;  
    private String descripcion;
    
    public BaseTallarines(){
        this.precio = 3.00;
        this.calorias = 230;
        this.descripcion = "Tallarines Pad Mei";
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public double getCalorias() {
        return calorias;
    }
    
        @Override
    public int getNumeroComplementosGamba() {
        return 0;
    }

    @Override
    public int getNumeroComplementosPollo() {
        return 0;
    }

    @Override
    public int getNumeroComplementosTernera() {
        return 0;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public boolean tieneSalsa() {
        return false;
    }
}
