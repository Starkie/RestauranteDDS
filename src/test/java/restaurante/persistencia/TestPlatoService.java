package restaurante.persistencia;

import Main.MainApplication;
import model.PedidoRestaurante;
import model.Plato;
import model.Usuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import persistance.AppContext;
import persistance.PlatoService;
import restaurante.modelo.Patron_Decorador.BaseTallarines;
import restaurante.modelo.Patron_Decorador.ComplementoGamba;
import restaurante.modelo.Patron_Decorador.ComplementoTernera;

import java.util.Iterator;

public class TestPlatoService {

    private static PlatoService crudService;

    @BeforeClass
    public static void setUp(){
        SpringApplication.run(MainApplication.class);
        crudService = (PlatoService) AppContext.getBean("platoService");
    }

    @Test
    public void TestAdd() {
        Plato objetoInicio = new ComplementoGamba(new BaseTallarines());
        boolean encontrado = false;

        crudService.add(objetoInicio);

        Iterator<Plato> iterator = crudService.findAll().iterator();
        while(iterator.hasNext()){
            Plato p = iterator.next();
            if(p.getDescripcion().equals(objetoInicio.getDescripcion())) encontrado = true;
        }
        if(!encontrado) Assert.fail();
    }

    @Test
    public void TestUpdate() {
        boolean encontrado = false;
        Plato objetoInicio = new ComplementoGamba(new BaseTallarines());
        crudService.add(objetoInicio);
        PedidoRestaurante elPedido = new PedidoRestaurante(new Usuario("Paco",224545,"Mi casa","jnjds"));
        elPedido.addPlatoPedido(objetoInicio);

        crudService.update(objetoInicio);

        Iterator<Plato> iterator = crudService.findAll().iterator();
        while(iterator.hasNext()){
            Plato p = iterator.next();
            if(p.getDescripcion().equals(objetoInicio.getDescripcion()) &&
                    p.getPedidoRestaurante().toString().equals(elPedido.toString())) encontrado = true;
        }
        if(!encontrado) Assert.fail();
    }

    @Test
    public void TestRemove() {
        Plato objetoInicio = new ComplementoTernera(new ComplementoGamba(new BaseTallarines()));
        boolean encontrado = false;
        crudService.add(objetoInicio);

        crudService.remove(objetoInicio);

        Iterator<Plato> iterator = crudService.findAll().iterator();
        while(iterator.hasNext()){
            Plato p = iterator.next();
            if(p.getDescripcion().equals(objetoInicio.getDescripcion())) encontrado = true;
        }
        if(encontrado) Assert.fail();
    }

   @Test
    public void TestFindById() {
       Plato objetoInicio = new ComplementoTernera(new ComplementoGamba(new BaseTallarines()));
       crudService.add(objetoInicio);

       Plato encontrado = crudService.findById(objetoInicio.getId());
       Assert.assertEquals(objetoInicio.getDescripcion(),encontrado.getDescripcion());
    }

    @Test
    public void TestFindAll() {
        //Se han de crear una total de 3 filas en la tabla
        Plato objetoInicio = new ComplementoTernera(new ComplementoGamba(new BaseTallarines()));
        crudService.add(objetoInicio);
        int cuenta = 0;

        Iterator<Plato> iterador = crudService.findAll().iterator();
        while(iterador.hasNext()){
            iterador.next();
            cuenta++;
        }

        Assert.assertEquals(3,cuenta);
    }

    @After
    public void tearDown(){
        crudService.findAll().forEach(o -> crudService.remove(o));
    }
}
