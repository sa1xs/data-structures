package listaenlazada;

public class NodoLista {

    // ENTIDAD: TECLADO
    // ATRIBUTOS
    private int tamaño;
    private boolean RGB;
    private String switches;
    private String tecla;
    private NodoLista siguiente;

    // MÉTODOS
    // GENERATE --> CONSTRUCTOR
    public NodoLista(int tamaño, boolean RGB, String switches, String tecla) {
        this.tamaño = tamaño;
        this.RGB = RGB;
        this.switches = switches;
        this.tecla = tecla;
        siguiente = null;
    }

    // Getters ---> recolectar datos (atributos)
    public int getTamaño() {
        return tamaño;
    }

    public boolean isRGB() {
        return RGB;
    }

    public String getSwitches() {
        return switches;
    }

    public String getTecla() {
        return tecla;
    }

    public NodoLista getSiguiente() {
        return siguiente;
    }


    //Setters ---> recibir datos (atributos) o cambiar
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public void setRGB(boolean RGB) {
        this.RGB = RGB;
    }

    public void setSwitches(String switches) {
        this.switches = switches;
    }

    public void setTecla(String tecla) {
        this.tecla = tecla;
    }

    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }

    //toString()
    public String toString(){
        return "Tecla: " + tecla + "\nSwitch: " + switches + "\nRGB: " + RGB + "\nTamaño: " + tamaño + "\n";
    }
}