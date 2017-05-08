package restaurante.modelo;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Víctor
 */
public class Prueba {
    public static void main(String[] args) throws Exception{
       Plato p = new ComplementoGamba( new ComplementoTernera( new SalsaCacahuetes((new BaseArroz()))));
       p.getPrecio();
       Plato p2 = new ComplementoGamba(new ComplementoGamba(new BaseArroz()));
       Plato p3 = new SalsaCacahuetes(new ComplementoGamba(new BaseArroz()));
       p3.getDescripcion();
    }
}
