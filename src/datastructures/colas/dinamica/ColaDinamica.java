package colas.dinamica;

import java.util.ArrayList;

public class ColaDinamica {

    //Atributos
    private ArrayList<String> elementos;

    // Metodos
    // Constructor
    public ColaDinamica(){
        elementos = new ArrayList<>();
    }

    // Operaciones
    private boolean estaVacia(){
        return elementos.isEmpty() ;
    }

    public void insertar(String elemento){
        elementos.addLast(elemento);
    }

    public String eliminar(){
        if (estaVacia()){
            System.out.println("La cola esta vacia\n");
            return null;
        }
        return elementos.removeFirst();
    }

    public String verFrente(){
        if (estaVacia()){
            System.out.println("La cola esta vacia\n");
            return null;
        }
        return elementos.getFirst();
    }
}
