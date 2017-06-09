package model;

import javax.persistence.*;

@Entity
public abstract class Plato {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private long id;

     @ManyToOne
     @JoinColumn(name = "pedidoRestaurante")
     private PedidoRestaurante pedidoRestaurante;

     protected double precio;
     protected double calorias;
     protected String descripcion;
     protected int numeroComplementosGamba;
     protected int numeroComplementosPollo;
     protected int numeroComplementosTernera;

     public Plato(double precio, double calorias, String descripcion, int numeroComplementosGamba, int numeroComplementosPollo, int numeroComplementosTernera) {
          this.precio = precio;
          this.calorias = calorias;
          this.descripcion = descripcion;
          this.numeroComplementosGamba = numeroComplementosGamba;
          this.numeroComplementosPollo = numeroComplementosPollo;
          this.numeroComplementosTernera = numeroComplementosTernera;
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
     
}
