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
import restaurante.domain.Reclamacion;
import restaurante.domain.Usuario;
import restaurante.persistance.ReclamacionService;

import java.util.Date;
import java.util.Iterator;

public class TestReclamacionService {
    private static ReclamacionService crudService;

    @BeforeClass
    public static void setUp(){
        SpringApplication.run(MainApplication.class);
        crudService = ServiceLocator.getReclamacionService();
    }

    @Test
    public void TestAdd() {
        PedidoRestaurante elPedido = new PedidoRestaurante(new Usuario("Paco",232323,"Una Casa Abandonada", "jndjs"));
        Reclamacion objetoInicio = new Reclamacion(new Date(),elPedido);
        boolean encontrado = false;

        crudService.add(objetoInicio);

        Iterator<Reclamacion> iterator = crudService.findAll().iterator();
        while(iterator.hasNext()){
            Reclamacion r = iterator.next();
            if(r.getId() == objetoInicio.getId()) encontrado = true;
        }
        if(!encontrado) Assert.fail();
    }

    @Test
    public void TestUpdate() {
        PedidoRestaurante elPedido = new PedidoRestaurante(new Usuario("Paco",232323,"Una Casa Abandonada","ksmdks"));
        Reclamacion objetoInicio = new Reclamacion(new Date(),elPedido);
        crudService.add(objetoInicio);
        objetoInicio.setDescripcion("Un PROBLEMA");

        crudService.update(objetoInicio);

        Reclamacion recuperado = crudService.findById(objetoInicio.getId());

        Assert.assertEquals(objetoInicio.getDescripcion(),recuperado.getDescripcion());
    }

    @Test
    public void TestRemove() {
        PedidoRestaurante elPedido = new PedidoRestaurante(new Usuario("Paco",232323,"Una Casa Abandonada","sds"));
        Reclamacion objetoInicio = new Reclamacion(new Date(),elPedido);
        crudService.add(objetoInicio);
        boolean encontrado = false;

        crudService.remove(objetoInicio);

        Iterator<Reclamacion> iterator = crudService.findAll().iterator();
        while(iterator.hasNext()){
            Reclamacion p = iterator.next();
            if(p.getId()==objetoInicio.getId()) encontrado = true;
        }
        if(encontrado) Assert.fail();
    }

    @Test
    public void TestFindById() {
        PedidoRestaurante elPedido = new PedidoRestaurante(new Usuario("Paco",232323,"Una Casa Abandonada","nsdj"));
        elPedido.addPlatoPedido(new BaseArroz());
        Reclamacion objetoInicio = new Reclamacion(new Date(),elPedido);
        crudService.add(objetoInicio);

        Reclamacion encontrado = crudService.findById(objetoInicio.getId());
        Assert.assertEquals(objetoInicio.getPedidoRestaurante().toString(),encontrado.getPedidoRestaurante().toString());
    }

    @Test
    public void TestFindAll() {
        //Se han de crear una total de 2 filas en la tabla
        PedidoRestaurante elPedido = new PedidoRestaurante(new Usuario("Paco",232323,"Una Casa Abandonada","sdd"));
        Reclamacion objetoInicio = new Reclamacion(new Date(),elPedido);
        Reclamacion objeto2 = new Reclamacion(new Date(),null);
        crudService.add(objetoInicio);
        crudService.add(objeto2);
        int cuenta = 0;

        Iterator<Reclamacion> iterator = crudService.findAll().iterator();

        while (iterator.hasNext()) {
            Reclamacion r = iterator.next();
            cuenta++;
        }
        Assert.assertEquals(2, cuenta);
    }

    @After
    public void tearDown(){
        crudService.findAll().forEach(o -> crudService.remove(o));
    }
}
