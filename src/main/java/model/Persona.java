package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Persona {
    @Id
    private int dni;
    private String nombre;
    private String contraseña;

    public Persona() {
    }

    public Persona(String nombre, int dni, String  contraseña) {
        this.nombre = nombre;
        this.dni = dni;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

public String getContraseña() {
        return contraseña;
    }
}
