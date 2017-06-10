package restaurante.persistencia;

import Main.MainApplication;
import model.PedidoRestaurante;
import model.Persona;
import model.Usuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import persistance.AppContext;
import persistance.PersonaService;
import restaurante.modelo.Patron_Comando.Cocinero;
import restaurante.modelo.Patron_Comando.Repartidor;

import java.util.Iterator;

public class TestPersonaService {
    private static PersonaService crudService;

    @BeforeClass
    public static void setUp(){
        SpringApplication.run(MainApplication.class);
        crudService = (PersonaService) AppContext.getBean("personaService");
    }

    @Test
    public void TestAdd() {
        Persona p1 = new Repartidor("Repartidor",223232);
        Persona p2 = new Usuario("Usuario",12344,"Direccion");
        Persona p3 = new Cocinero("Cocinero",1212);
        boolean[] encontrados = new boolean[3];

        crudService.add(p1);
        crudService.add(p2);
        crudService.add(p3);

        Iterator<Persona> iterator = crudService.findAll().iterator();
        while(iterator.hasNext()){
            Persona p = iterator.next();
            if(p.getDni()==(p1.getDni())) encontrados [0] = true;
            if(p.getDni()==(p2.getDni())) encontrados [1] = true;
            if(p.getDni()==(p3.getDni())) encontrados [2] = true;
        }
        for(int i=0;i<encontrados.length;i++){
            if(!encontrados[i]) Assert.fail();
        }
    }

    @Test
    public void TestUpdate() {
        Persona usuario = new Usuario("Pedro",232323,"Casa");
        crudService.add(usuario);
        PedidoRestaurante elPedido = new PedidoRestaurante((Usuario)usuario);
        ((Usuario) usuario).addPedidoUsuario(elPedido);

        crudService.update(usuario);

        Persona recuperado = crudService.findById(usuario.getDni());

        Assert.assertEquals(1,((Usuario)recuperado).getPedidosDelUsuario().size());
    }

    @Test
    public void TestRemove() {
        Persona usuario = new Usuario("Pedro",232323,"Casa");
        crudService.add(usuario);
        boolean encontrado = false;

        crudService.remove(usuario);

        Iterator<Persona> iterator = crudService.findAll().iterator();
        while(iterator.hasNext()){
            Persona p = iterator.next();
            if(p.getDni()==usuario.getDni()) encontrado = true;
        }
        if(encontrado) Assert.fail();
    }

    @Test
    public void TestFindById() {
        Persona usuario = new Usuario("Pedro",232323,"Casa");
        crudService.add(usuario);

        Persona encontrado = crudService.findById(usuario.getDni());
        Assert.assertEquals(usuario.getDni(),encontrado.getDni());
    }

    @Test
    public void TestFindAll() {
        //Se han de crear una total de 3 filas en la tabla
        Persona p1 = new Repartidor("Repartidor", 223232);
        Persona p2 = new Usuario("Usuario", 12344, "Direccion");
        Persona p3 = new Cocinero("Cocinero", 1212);
        int cuenta = 0;
        crudService.add(p1);
        crudService.add(p2);
        crudService.add(p3);

        Iterator<Persona> iterator = crudService.findAll().iterator();

        while (iterator.hasNext()) {
            Persona p = iterator.next();
            cuenta++;
        }

        Assert.assertEquals(3, cuenta);
    }

    @After
    public void tearDown(){
        crudService.findAll().forEach(o -> crudService.remove(o));
    }
}
