package restaurante.domain;

import domain.Alimento;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Plato {
     @Id
     @GeneratedValue(strategy = GenerationType.TABLE)
     private long id;

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "pedidoRestaurante_fk")
     private PedidoRestaurante pedidoRestaurante;

     @ManyToOne
     private Alimento alimento;

     protected double precio;
     protected double calorias;
     protected String descripcion;
     protected int numeroComplementosGamba;
     protected int numeroComplementosPollo;
     protected int numeroComplementosTernera;

     public Plato() {
     }

     public Plato(double precio, double calorias, String descripcion, int numeroComplementosGamba, int numeroComplementosPollo, int numeroComplementosTernera, Alimento alimento) {
          this.precio = precio;
          this.calorias = calorias;
          this.descripcion = descripcion;
          this.numeroComplementosGamba = numeroComplementosGamba;
          this.numeroComplementosPollo = numeroComplementosPollo;
          this.numeroComplementosTernera = numeroComplementosTernera;
          this.alimento = alimento;
     }

     public Plato(double precio, double calorias, String descripcion, int numeroComplementosGamba, int numeroComplementosPollo, int numeroComplementosTernera) {
          this.precio = precio;
          this.calorias = calorias;
          this.descripcion = descripcion;
          this.numeroComplementosGamba = numeroComplementosGamba;
          this.numeroComplementosPollo = numeroComplementosPollo;
          this.numeroComplementosTernera = numeroComplementosTernera;
     }

     public long getId() {
          return id;
     }

     public double getPrecio(){
          return precio;
     }
     
     public double getCalorias(){
          return calorias;
     }
     
     public int getNumeroComplementosGamba(){
          return numeroComplementosGamba;
     }
     
     public int getNumeroComplementosPollo(){
          return numeroComplementosPollo;
     }
     
     public int getNumeroComplementosTernera(){
          return numeroComplementosTernera;
     }
     
     public String getDescripcion(){
          return descripcion;
     }
     
     public boolean tieneSalsa(){
      return false;
     }

     public PedidoRestaurante getPedidoRestaurante() {
          return pedidoRestaurante;
     }

     public void setPedidoRestaurante(PedidoRestaurante pedidoRestaurante) {
          this.pedidoRestaurante = pedidoRestaurante;
     }

     public Alimento getAlimento(){
          return alimento;
     }

     public List<Alimento> getAlimentosPlato() {
          List<Alimento> alimentos = new ArrayList<Alimento>();
          alimentos.add(alimento);
          return alimentos;
     }

     public void setAlimento(Alimento alimento) {
          this.alimento = alimento;
     }
}
