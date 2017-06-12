package restaurante.plato;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import restaurante.business.modelo.Patron_Decorador.*;
import restaurante.domain.Plato;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class TestElaboracionPlato {
    //Test parametrizado

    private Plato plato;
    private double precio;
    private double calorias;
    private String descripcion;
    private int numeroComplementosGamba;
    private int numeroComplementosPollo;
    private int numeroComplementosTernera;
    private boolean tieneSalsa;

    public TestElaboracionPlato(Plato plato, double precio, double calorias, String descripcion, int numeroComplementosGamba, int numeroComplementosPollo, int numeroComplementosTernera, boolean tieneSalsa) {
        this.plato = plato;
        this.precio = precio;
        this.calorias = calorias;
        this.descripcion = descripcion;
        this.numeroComplementosGamba = numeroComplementosGamba;
        this.numeroComplementosPollo = numeroComplementosPollo;
        this.numeroComplementosTernera = numeroComplementosTernera;
        this.tieneSalsa = tieneSalsa;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> generateData() throws Exception {
        return Arrays.asList(new Object[][]{
            {new BaseArroz(),2.5,200,"Delicioso arroz tres delicias",0,0,0,false},
            {new BaseTallarines(),3.0,230,"Tallarines Pad Mei",0,0,0,false},
            {new ComplementoGamba(new BaseTallarines()),4.5,320,"Tallarines Pad Mei con uno de Gambas crujientes",1,0,0,false},
            {new SalsaCacahuetes(new ComplementoPollo(new BaseTallarines())),4.0,390,"Tallarines Pad Mei con uno de Pollo frito y Salsa de Cacahuetes",0,1,0,true},
            {new ComplementoTernera(new ComplementoTernera(new SalsaOstras( new BaseArroz()))),5.5,500,"Delicioso arroz tres delicias con doble de Ternera asada y Salsa de Ostras",0,0,2,true},
            {new SalsaCacahuetes(new BaseArroz()),2.5,260,"Delicioso arroz tres delicias con Salsa de Cacahuetes",0,0,0,true},
        });
    }

    @Test
    public void testgetPrecio(){
        Assert.assertEquals((long)precio,(long)plato.getPrecio());
    }

    @Test
    public void testgetCalorias(){
        Assert.assertEquals((long)calorias,(long)plato.getCalorias());
    }

    @Test
    public void testgetDescripcion(){
        Assert.assertEquals(descripcion, plato.getDescripcion());
    }

    @Test
    public void testgetNumeroComplementosGamba(){
        Assert.assertEquals(numeroComplementosGamba,plato.getNumeroComplementosGamba());
    }

    @Test
    public void testgetNumeroComplementosPollo(){
        Assert.assertEquals(numeroComplementosPollo,plato.getNumeroComplementosPollo());
    }

    @Test
    public void testgetNumeroComplementosTernera(){
        Assert.assertEquals(numeroComplementosTernera,plato.getNumeroComplementosTernera());
    }

    @Test
    public void testTieneSalsa(){
        Assert.assertEquals(tieneSalsa,plato.tieneSalsa());
    }
}
