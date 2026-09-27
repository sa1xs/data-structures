package pilas.dinamica;

import java.util.ArrayList;

public class PilaDinamica {

    //ATRIBUTOS
    private ArrayList<String> elementos;

    //METODOS
    //CONSTRUCTOR
    public PilaDinamica(){
        elementos = new ArrayList<>();
    }

    //OPERACIONES
    private boolean estaVacia(){
        return elementos.isEmpty();
    }

    public void push(String elemento){
        elementos.addLast(elemento);
    }

    public String pop(){
        if (estaVacia()){
            System.out.println("La pila esta vacia\n");
            return null;
        }
        return elementos.removeLast();
    }

    public String peek(){
        if (estaVacia()){
            System.out.println("La pila esta vacia\n");
            return null;
        }
        return elementos.getLast();
    }
}
