package listaenlazada;

public class ListaEnlazada{

    // ATRIBUTO
    private NodoLista primero;

    // MÉTODOS
    // CONSTRUCTOR
    public ListaEnlazada() {
        primero = null;
    }

    // OPERACIONES
    // AGREGAR A LA LISTA UNA OPERACIÓN PRIVADA QUE EVALUE SI ESTÁ VACÍA

    public boolean estaVacia() {
        return primero == null;
    }

    // Constuir el nodo con los datos recibidos de parámatro
    public void insertarInicio(int tamaño, boolean RGB, String switches, String tecla){
        // Constuir el nodo con los datos recibidos de parámatro
        // Hacer que el nuevo nodo apunte como siguiente al primero de la lista
        NodoLista nodoNuevo = new NodoLista(tamaño, RGB, switches, tecla);
        nodoNuevo.setSiguiente(primero);

        // Hacer que el primero de la lista sea el nuevo nodo
        primero = nodoNuevo;
    }

    public void InsertarFinal(int tamaño, boolean RGB, String switches, String tecla){
        NodoLista nodoNuevo = new NodoLista(tamaño, RGB, switches, tecla);

        if(estaVacia()){
            primero = nodoNuevo;
            return;
        }
        NodoLista temp = primero;
        while(temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(nodoNuevo);
    }

    public NodoLista buscar(int tamaño){
        if(estaVacia()){
            System.out.println("La lista está vacía.\n");
            return null;
        }
        NodoLista temp = primero;
        while(temp != null){
            if(temp.getTamaño() == (tamaño)) return temp;
            temp = temp.getSiguiente();
        }
        System.out.println("El tamaño buscado no está en la lista.\n");
        return null;
    }

    public NodoLista eliminar(int tamaño){
        if(estaVacia()){
            System.out.println("La lista está vacía.\n");
            return null;
        }
        if(primero.getTamaño() == tamaño){
            NodoLista aux = primero;
            primero = primero.getSiguiente();
            return aux;
        }
        NodoLista temp = primero;
        NodoLista anterior = temp;
        while (temp != null && temp.getTamaño() != tamaño) {
            anterior = temp;
            temp = temp.getSiguiente();
        }
        if(temp == null){
            System.out.println("El nombre buscado no está en la lista.\n");
            return null;
        }
        anterior.setSiguiente(temp.getSiguiente());
        return temp;
        }

    public void mostrar(){
        if(estaVacia()){
            System.out.println("La lista está vacía.\n");
            return;
        }
        NodoLista temp = primero;
        while(temp != null){
            System.out.println(temp);
            temp = temp.getSiguiente();
        }
    }
}