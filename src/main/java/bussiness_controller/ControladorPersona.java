package bussiness_controller;

import model.Persona;
import persistance.AppContext;
import persistance.PersonaService;

public class ControladorPersona{

    private static ControladorPersona controladorPersona;
    private PersonaService personaService;

    private ControladorPersona() {
        personaService = (PersonaService) AppContext.getBean("personaService");
    }

    public static ControladorPersona getControladorPersona(){
        if(controladorPersona == null) controladorPersona = new ControladorPersona();
        return controladorPersona;
    }

    public Persona comprobarContrasenya(String nombre, String contraseña) throws Exception{
        Persona p = personaService.findByName(nombre);
        if(p==null) throw new Exception("No estás registrado en el sistema");
        else{
            if(p.getContraseña().equals(contraseña)) return p;
            else throw new Exception("Contraseña incorrecta");
        }
    }

}
