package restaurante.modelo;

public class BaseArroz implements Plato {

    private double precio;
    private int calorias; 
    private String descripcion;
    
    public BaseArroz(){
        this.precio= 2.50;
        this.calorias = 200;
        this.descripcion = "Delicioso arroz 3 delicias";
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
