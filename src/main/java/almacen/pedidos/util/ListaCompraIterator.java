package almacen.pedidos.util;

import almacen.pedidos.model.ListaCompra;

import java.util.Iterator;
import java.util.Stack;

public class ListaCompraIterator implements Iterator {
    Stack<Iterator<ListaCompra>> stack = new Stack<Iterator<ListaCompra>>();

    public ListaCompraIterator(Iterator iterator) {
        stack.push(iterator);
    }
    @Override
    public boolean hasNext() {
        if(stack.empty()) {
            return false;
        }

        else {
            Iterator<ListaCompra> iterator = stack.peek();
            if(!iterator.hasNext()) {
                stack.pop();
                return hasNext();
            }
            else {
                return true;
            }
        }
    }

    @Override
    public Object next() {
       if(hasNext()) {
           Iterator<ListaCompra> iterator = stack.peek();
           ListaCompra componente = iterator.next();
           stack.push(componente.createIterator());

           return componente;
       }

        else {
           return null;
       }

    }
}
