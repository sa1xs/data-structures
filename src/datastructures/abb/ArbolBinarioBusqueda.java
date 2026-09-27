package abb;

public class ArbolBinarioBusqueda {

    //ATRIBUTOS
    private NodoArbol raiz;

    //CONSTRUCTOR
    public BST(){
        raiz = null;
    }

    //GETTER
    public NodoArbol getRaiz() {
        return raiz;
    }

    //SETTER
    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    //OPERACIONES
    private boolean estaVacia() {
        /*if (raiz == null){
            System.out.println ("El arbol esta vacio");
        }
        return true; */
        return raiz == null;
    }

    public void insertar(int llave, String nombre, String familia, int hablantes) {
        NodoArbol nodo = new NodoArbol(llave, nombre, familia, hablantes);

        if (estaVacia()){
            setRaiz(nodo);
            return;
        }

        NodoArbol temp = raiz;
        NodoArbol padreTemp = temp;

        while (temp != null) {
            padreTemp = temp;
            if (temp.getLlave() < llave) temp = temp.getDer();
            else if (temp.getLlave()> llave) temp = temp.getIzq();
            else{
                System.out.println("La llave ya se encuentra en el arbol\n");
                return;
            }
        }
        if (padreTemp.getLlave() < llave) padreTemp.setDer(nodo);
        else padreTemp.setIzq(nodo);
    }
    public NodoArbol buscar(int llave) {
        if (estaVacia()){
            System.out.println("El arbol esta vacio\n");
            return null;
        }

        NodoArbol temp = raiz;

        while (temp != null) {
            if (temp.getLlave() < llave) temp = temp.getDer();
            else if (temp.getLlave()> llave) temp = temp.getIzq();
            else return temp;
        }

        System.out.println("La llave no se encuentra en el arbol\n");
        return null;
    }

    private NodoArbol buscarPadre(int llave) {
        if (estaVacia()){
            System.out.println("El arbol esta vacio\n");
            return null;
        }

        NodoArbol temp = raiz;
        NodoArbol padreTemp = temp;

        while (temp != null) {
            if (temp.getLlave() == llave) return padreTemp;
            padreTemp = temp;
            if (temp.getLlave() < llave) temp = temp.getDer();
            else temp = temp.getIzq();
        }

        System.out.println("La llave no se encuentra en el arbol\n");
        return null;

    }

    private void enOrdenRec (NodoArbol nodoActual){
        if (nodoActual != null){
            enOrdenRec(nodoActual.getIzq());
            System.out.println(nodoActual.getLlave() + " ");
            enOrdenRec(nodoActual.getDer());
        }
    }

    public void enOrden(){
        enOrdenRec(raiz);
    }

    private void preOrdenRec (NodoArbol nodoActual){
        if (nodoActual != null){
            System.out.println(nodoActual.getLlave() + " ");
            preOrdenRec(nodoActual.getIzq());
            preOrdenRec(nodoActual.getDer());
        }
    }

    public void preOrden(){
        preOrdenRec(raiz);
    }

    private void postOrdenRec (NodoArbol nodoActual){
        if (nodoActual != null){
            postOrdenRec(nodoActual.getIzq());
            postOrdenRec(nodoActual.getDer());
            System.out.println(nodoActual.getLlave() + " ");
        }
    }

    public void postOrden(){
        postOrdenRec(raiz);
    }

    private NodoArbol buscarSucesor (NodoArbol nodo){
        NodoArbol temp = nodo.getDer();
        NodoArbol sucesor = nodo;
        NodoArbol padreSucesor = sucesor;

        while (temp != null){
            padreSucesor = sucesor;
            sucesor = temp;
            temp = temp.getIzq();
        }
        if (sucesor != nodo.getDer()){
            padreSucesor.setIzq(sucesor.getDer());
            sucesor.setDer(nodo.getDer());
        }
        return sucesor;
    }

    public NodoArbol eliminar(int llave) {

        // No se puede eliminar si el árbol está vacío.
        if (estaVacia()) {
            System.out.println("El árbol está vacío\n");
            return null;
        }

        // Busca el nodo que se desea eliminar.
        NodoArbol nodo = buscar(llave);

        // La llave no existe en el árbol.
        if (nodo == null) {
            return null;
        }

        /*
         * La raíz se procesa por separado porque no tiene padre.
         */
        if (nodo == raiz) {

            // Caso 1: la raíz no tiene hijos.
            if (nodo.getIzq() == null && nodo.getDer() == null) {
                setRaiz(null);
            }

            // Caso 2: la raíz solamente tiene hijo izquierdo.
            else if (nodo.getDer() == null) {
                setRaiz(nodo.getIzq());
            }

            // Caso 3: la raíz solamente tiene hijo derecho.
            else if (nodo.getIzq() == null) {
                setRaiz(nodo.getDer());
            }

            // Caso 4: la raíz tiene dos hijos.
            else {
                NodoArbol sucesor = buscarSucesor(nodo);

                // El sucesor adopta el subárbol izquierdo.
                sucesor.setIzq(nodo.getIzq());

                // El sucesor se convierte en la nueva raíz.
                setRaiz(sucesor);
            }

            return nodo;
        }

        /*
         * Para los demás nodos necesitamos conocer al padre,
         * porque debemos modificar una de sus referencias.
         */
        NodoArbol padre = buscarPadre(llave);

        // Caso 1: el nodo no tiene hijos.
        if (nodo.getIzq() == null && nodo.getDer() == null) {

            // El nodo es hijo izquierdo.
            if (nodo == padre.getIzq()) {
                padre.setIzq(null);
            }

            // El nodo es hijo derecho.
            else {
                padre.setDer(null);
            }
        }

        // Caso 2: el nodo solamente tiene hijo izquierdo.
        else if (nodo.getDer() == null) {

            // El padre adopta al hijo izquierdo del nodo eliminado.
            if (nodo == padre.getIzq()) {
                padre.setIzq(nodo.getIzq());
            } else {
                padre.setDer(nodo.getIzq());
            }
        }

        // Caso 3: el nodo solamente tiene hijo derecho.
        else if (nodo.getIzq() == null) {

            // El padre adopta al hijo derecho del nodo eliminado.
            if (nodo == padre.getIzq()) {
                padre.setIzq(nodo.getDer());
            } else {
                padre.setDer(nodo.getDer());
            }
        }

        // Caso 4: el nodo tiene dos hijos.
        else {
            NodoArbol sucesor = buscarSucesor(nodo);

            // El sucesor adopta el subárbol izquierdo del nodo eliminado.
            sucesor.setIzq(nodo.getIzq());

            // El padre ahora apunta al sucesor.
            if (nodo == padre.getIzq()) {
                padre.setIzq(sucesor);
            } else {
                padre.setDer(sucesor);
            }
        }

        // Retorna el nodo que fue eliminado.
        return nodo;
    }
}
