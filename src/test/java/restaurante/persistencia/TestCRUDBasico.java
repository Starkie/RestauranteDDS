package restaurante.persistencia;

import Main.MainApplication;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.SpringApplication;
import persistance.AppContext;
import persistance.CrudService;
import restaurante.modelo.Patron_Decorador.BaseTallarines;
import restaurante.modelo.Patron_Decorador.ComplementoGamba;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;


@RunWith(Parameterized.class)
public class TestCRUDBasico {
    //Parametrizado

/*    private static PersonaService personaService;
    private static PedidoRestauranteService pedidoRestauranteService;
    private static PlatoService platoService;
    private static ReclamacionService reclamacionService;*/


    private CrudService crudService;
    private Object objetoInicio;

    public TestCRUDBasico(CrudService crudService, Object objetoInicio) {
        this.crudService = crudService;
        this.objetoInicio = objetoInicio;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> generateData() throws Exception {
        SpringApplication.run(MainApplication.class);
        return Arrays.asList(new Object[][]{
                {AppContext.getBean("platoService"),new ComplementoGamba(new BaseTallarines())}
        });
    }

    @Test
    public void TestAdd(){
        boolean encontrado = false;
        crudService.add(objetoInicio);
        Iterator iterator = crudService.findAll().iterator();
        while(iterator.hasNext()){
            Object o = iterator.next();
            if(o.toString().equals(objetoInicio.toString())) encontrado = true;
        }
        if(!encontrado) Assert.fail();
    }

    @After
    public void tearDown(){
        crudService.findAll().forEach(o -> crudService.remove(o));
    }
}
