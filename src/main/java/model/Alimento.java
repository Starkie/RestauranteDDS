package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private String descripcion;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Categoria> categorias;

    public Alimento() {}

    public Alimento(String nombre) {
        this.nombre = nombre;
        //this.categorias = new ArrayList<>();
    }

    public Alimento(String nombre, String descripcion) {
        this(nombre);
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void addCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public boolean removeCategoria(Categoria categoria) {
        return categorias.remove(categoria);
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public int countCategorias() {
        return categorias.size();
    }


}
