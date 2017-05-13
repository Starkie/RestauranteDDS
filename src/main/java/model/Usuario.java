package model;

public class Usuario extends Persona{
    private String direccion;

    public Usuario(String nombre, int dni, String direccion) {
        super(nombre, dni);
        this.direccion = direccion;
    }
}
