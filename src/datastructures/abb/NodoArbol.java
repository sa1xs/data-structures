package abb;

public class NodoArbol {

    //Atributos
    private final int llave;
    private String nombre;
    private String familia;
    private int hablantes;
    private NodoArbol izq;
    private NodoArbol der;

    //CONSTRUCTOR
    public NodoArbol(int llave, String nombre, String familia, int hablantes) {
        this.llave = llave;
        this.nombre = nombre;
        this.familia = familia;
        this.hablantes = hablantes;
        izq = der = null;
    }

    //GETTERS
    public int getLlave() {
        return llave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFamilia() {
        return familia;
    }

    public int getHablantes() {
        return hablantes;
    }

    public NodoArbol getIzq() {
        return izq;
    }

    public NodoArbol getDer() {
        return der;
    }

    //SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public void setHablantes(int hablantes) {
        this.hablantes = hablantes;
    }

    public void setIzq(NodoArbol izq) {
        this.izq = izq;
    }

    public void setDer(NodoArbol der) {
        this.der = der;
    }
}