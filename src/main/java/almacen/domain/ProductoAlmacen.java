package almacen.domain;

import javax.persistence.*;

@Entity
public class ProductoAlmacen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Producto producto;
    private int stock;

    public ProductoAlmacen() {}

    public ProductoAlmacen(Producto producto, int stock) {
        this.producto = producto;
        this.stock = stock;
    }

    public Producto getProducto() { return  producto; }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getStock() { return  stock; }

    public void setSock(int stock) { this.stock = stock; }

}
