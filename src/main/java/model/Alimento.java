package model;

import java.util.ArrayList;
import java.util.List;

public class Alimento {
    private String nombre;
    private String descripcion;
    private List<Categoria> categorias;

    public Alimento(String nombre) {
        this.nombre = nombre;
        this.categorias = new ArrayList<>();
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
