package restaurante.persistencia;

import Main.MainApplication;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;

import persistance.ServiceLocator;
import restaurante.business.modelo.Patron_Decorador.BaseArroz;
import restaurante.domain.PedidoRestaurante;
import restaurante.domain.Plato;
import restaurante.domain.Usuario;
import restaurante.persistance.PedidoRestauranteService;
import restaurante.persistance.PlatoService;

import java.util.Iterator;

public class TestPedidoRestauranteService {

    private static PedidoRestauranteService crudService;
    private static PlatoService platoService;

    @BeforeClass
    public static void setUp(){
        SpringApplication.run(MainApplication.class);
        platoService = ServiceLocator.getPlatoService();
        crudService = ServiceLocator.getPedidoRestauranteService();
    }

    @Test
    public void TestAdd() {
        PedidoRestaurante objetoInicio = new PedidoRestaurante(new Usuario("Paco",23232,"Mi casa","dcdc"));
        objetoInicio.addPlatoPedido(new BaseArroz());
        boolean encontrado = false;

        crudService.add(objetoInicio);

        Iterator<PedidoRestaurante> iterator = crudService.findAll().iterator();
        while(iterator.hasNext()){
            PedidoRestaurante p = iterator.next();
            if(p.toString().equals(objetoInicio.toString())) encontrado = true;
        }
        if(!encontrado) Assert.fail();
    }

    @Test
    public void TestUpdate() {
        boolean encontrado = false;
        PedidoRestaurante objetoInicio = new PedidoRestaurante(new Usuario("Paco",23232,"Mi casa","sds"));
        Plato p = new BaseArroz();
        objetoInicio.addPlatoPedido(p);
        crudService.add(objetoInicio);
        objetoInicio.setEstaCocinado(true);
        crudService.update(objetoInicio);

        PedidoRestaurante recuperado = crudService.findById(objetoInicio.getId());

        Assert.assertEquals(objetoInicio.isCocinado(),recuperado.isCocinado());
    }


    @Test
    public void TestRemove() {
        PedidoRestaurante objetoInicio = new PedidoRestaurante(new Usuario("Paco",23232,"Mi casa","sdsd"));
        objetoInicio.addPlatoPedido(new BaseArroz());
        boolean encontrado = false;
        crudService.add(objetoInicio);

        crudService.remove(objetoInicio);

        Iterator<PedidoRestaurante> iterator = crudService.findAll().iterator();
        while(iterator.hasNext()){
            PedidoRestaurante p = iterator.next();
            if(p.toString().equals(objetoInicio.toString())) encontrado = true;
        }
        if(encontrado) Assert.fail();
    }

    @Test
    public void TestFindById() {
        PedidoRestaurante objetoInicio = new PedidoRestaurante(new Usuario("Paco",23232,"Mi casa","sd"));
        objetoInicio.addPlatoPedido(new BaseArroz());
        crudService.add(objetoInicio);

        PedidoRestaurante encontrado = crudService.findById(objetoInicio.getId());
        Assert.assertEquals(objetoInicio.toString(),encontrado.toString());
    }

    @Test
    public void TestFindAll() {
        //Se han de crear una total de 2 filas en la tabla
        PedidoRestaurante objeto1 = new PedidoRestaurante(new Usuario("Paco",23232,"Mi casa","ddf"));
        PedidoRestaurante objeto2 = new PedidoRestaurante(new Usuario("Pedro",21232,"Mi casa2","df"));
        crudService.add(objeto1);
        crudService.add(objeto2);
        int cuenta = 0;

        Iterator<PedidoRestaurante> iterador = crudService.findAll().iterator();
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
