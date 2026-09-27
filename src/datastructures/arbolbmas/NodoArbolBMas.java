package arbolbmas;

import java.util.ArrayList;
import java.util.List;

public class NodoArbolBMas {

    // Indica si el nodo es una hoja o un nodo interno.
    private final boolean esHoja;

    // Claves almacenadas en el nodo.
    private List<Integer> claves;

    // Hijos del nodo. Esta lista permanece vacía cuando es una hoja.
    private List<NodoArbolBMas> hijos;

    // Datos asociados con las claves.
    // Solo los nodos hoja almacenan datos.
    private List<String> datos;

    // Referencia a la siguiente hoja del árbol B+.
    // Permanece en null para los nodos internos.
    private NodoArbolBMas siguiente;

    public NodoArbolBMas(boolean esHoja) {
        this.esHoja = esHoja;
        this.claves = new ArrayList<>();
        this.hijos = new ArrayList<>();

        // Los datos únicamente existen en nodos hoja.
        this.datos = esHoja ? new ArrayList<>() : null;

        // Inicialmente no existe una hoja siguiente.
        this.siguiente = null;
    }

    public boolean esHoja() {
        return esHoja;
    }

    public List<Integer> getClaves() {
        return claves;
    }

    public List<NodoArbolBMas> getHijos() {
        return hijos;
    }

    public List<String> getDatos() {
        return datos;
    }

    public NodoArbolBMas getSiguiente() {
        return siguiente;
    }

    public void setClaves(List<Integer> claves) {
        this.claves = claves;
    }

    public void setHijos(List<NodoArbolBMas> hijos) {
        this.hijos = hijos;
    }

    public void setDatos(List<String> datos) {
        this.datos = datos;
    }

    public void setSiguiente(NodoArbolBMas siguiente) {
        this.siguiente = siguiente;
    }
}