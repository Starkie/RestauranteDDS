package restaurante.ordenes;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import restaurante.modelo.Patron_Comando.EmisorOrdenes;
import restaurante.modelo.Patron_Comando.Repartidor;

import java.util.Iterator;

public class TestEmisorOrdenes {
    private static EmisorOrdenes elEmisor;

    @BeforeClass
    public static void setUp(){
        elEmisor = EmisorOrdenes.getEmisorOrdenes();
    }

    @Test
    public void TestanyadirRepartidor(){
        Repartidor repartidor = new Repartidor("Paco",23232,"contraseña");
        EmisorOrdenes elEmisor = EmisorOrdenes.getEmisorOrdenes();
        Iterator<Repartidor> repartidorList = elEmisor.getRepartidores().iterator();
        boolean encontrado = false;
        while(repartidorList.hasNext()){
            if(repartidor.equals(repartidorList.next())) encontrado = true;
        }
        if(!encontrado) Assert.fail();
    }
}
