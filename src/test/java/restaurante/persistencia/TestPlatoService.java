package restaurante.persistencia;

import Main.MainApplication;
import domain.Alimento;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import persistance.AlimentoService;
import persistance.AppContext;
import restaurante.business.modelo.Patron_Decorador.BaseArroz;
import restaurante.business.modelo.Patron_Decorador.BaseTallarines;
import restaurante.business.modelo.Patron_Decorador.ComplementoGamba;
import restaurante.domain.PedidoRestaurante;
import restaurante.domain.Plato;
import restaurante.domain.Usuario;
import restaurante.persistance.PlatoService;

import java.util.Iterator;

public class TestPlatoService {

    private static PlatoService crudService;
    private static AlimentoService alimentoService;
    @BeforeClass
    public static void setUp(){
        SpringApplication.run(MainApplication.class);
        crudService = (PlatoService) AppContext.getBean("platoService");
        alimentoService = (AlimentoService) AppContext.getBean("alimentoService");
    }

    @Test
    public void TestAdd() {
        Plato objetoInicio = new ComplementoGamba(new BaseTallarines());
        Alimento a = new Alimento("Tallarines");
        alimentoService.add(a);
        crudService.add(objetoInicio);

        objetoInicio.setAlimento(a);
        boolean encontrado = false;

        alimentoService.add(objetoInicio.getAlimento());

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
        Alimento a = new Alimento("TALLARINES");
        alimentoService.add(a);
        objetoInicio.setAlimento(a);
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
        Plato objetoInicio = new BaseTallarines();
        crudService.add(objetoInicio);
        Alimento alimento = new Alimento("Tallarines");
        alimentoService.add(alimento);
        objetoInicio.setAlimento(alimento);
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
       Plato objetoInicio = new BaseTallarines();
       crudService.add(objetoInicio);
       Alimento alimento = new Alimento("Tallarines");
       alimentoService.add(alimento);
       objetoInicio.setAlimento(alimento);

       Plato encontrado = crudService.findById(objetoInicio.getId());
       Assert.assertEquals(objetoInicio.getDescripcion(),encontrado.getDescripcion());
    }

    @Test
    public void TestFindAll() {
        //Se han de crear una total de 2 fila en la tabla
        Plato objetoInicio = new BaseTallarines();
        crudService.add(objetoInicio);
        Alimento alimento = new Alimento("Tallarines");
        alimentoService.add(alimento);
        objetoInicio.setAlimento(alimento);
        Plato objetoInicio2 = new BaseArroz();
        crudService.add(objetoInicio2);
        Alimento alimento2 = new Alimento("Arroz");
        alimentoService.add(alimento2);
        objetoInicio.setAlimento(alimento2);
        int cuenta = 0;

        Iterator<Plato> iterador = crudService.findAll().iterator();
        while(iterador.hasNext()){
            iterador.next();
            cuenta++;
        }

        Assert.assertEquals(2,cuenta);
    }

    @After
    public void tearDown(){
        crudService.findAll().forEach(o -> crudService.remove(o));
    }
}
