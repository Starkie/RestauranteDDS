package model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="TipoPersona",
        discriminatorType = DiscriminatorType.STRING
)
public abstract class Persona {
    @Id
    private int dni;
    private String nombre;

    public Persona(String nombre, int dni) {
        this.nombre = nombre;
        this.dni = dni;
    }
}
