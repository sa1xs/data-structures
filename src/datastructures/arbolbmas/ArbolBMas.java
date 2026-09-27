package arbolbmas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArbolBMas {

    // Atributos
    private final int orden;
    private NodoArbolBMas raiz;

    // Métodos
    // Constructor
    public ArbolBMas(int orden) {
        this.orden = orden;
        raiz = new NodoArbolBMas(true);
    }

    /**
     * Determina si una clave existe en una hoja del árbol B+.
     *
     * @param clave clave que se desea buscar.
     * @return true si la clave existe; false en caso contrario.
     */
    public boolean buscar(int clave) {

        // Todos los datos reales del árbol B+ están almacenados en hojas.
        NodoArbolBMas hoja = buscarHoja(clave);

        // binarySearch aprovecha que las claves están ordenadas.
        return Collections.binarySearch(
                hoja.getClaves(),
                clave
        ) >= 0;
    }

    // Inserta una clave y su dato asociado en el árbol B+.
    public void insertar(int clave, String dato) {

        // Se evita insertar una clave repetida.
        if (buscar(clave)) {
            System.out.println("La clave " + clave + " ya existe.");
            return;
        }

        insertarRecursivo(this.raiz, clave, dato);

        // Si la raíz se desborda, se crea una nueva raíz.
        if (this.raiz.getClaves().size() == orden) {
            NodoArbolBMas nuevaRaiz = new NodoArbolBMas(false);
            nuevaRaiz.getHijos().add(this.raiz);

            dividirHijo(nuevaRaiz, 0);

            this.raiz = nuevaRaiz;
        }
    }

    // Inserta recursivamente una clave y su dato asociado.
    private void insertarRecursivo(
            NodoArbolBMas nodo,
            int clave,
            String dato
    ) {
        int i = nodo.getClaves().size() - 1;

        if (nodo.esHoja()) {

            // Busca la posición donde debe insertarse la clave.
            while (i >= 0 && clave < nodo.getClaves().get(i)) {
                i--;
            }

            int posicion = i + 1;

            // La clave y el dato deben ocupar la misma posición.
            nodo.getClaves().add(posicion, clave);
            nodo.getDatos().add(posicion, dato);

        } else {

            // Determina cuál hijo debe recibir la nueva clave.
            while (i >= 0 && clave < nodo.getClaves().get(i)) {
                i--;
            }

            i++;

            NodoArbolBMas hijo = nodo.getHijos().get(i);

            // También debe enviarse el dato en la llamada recursiva.
            insertarRecursivo(hijo, clave, dato);

            // Si el hijo se desborda, se divide.
            if (hijo.getClaves().size() == orden) {
                dividirHijo(nodo, i);
            }
        }
    }

    // Dividir un nodo que está lleno
    private void dividirHijo(NodoArbolBMas padre, int indice) {
        NodoArbolBMas nodoLleno = padre.getHijos().get(indice);
        NodoArbolBMas nuevoNodo = new NodoArbolBMas(nodoLleno.esHoja());
        int mitad = orden / 2;

        if (nodoLleno.esHoja()) {

            /*
             * Se crean copias antes de modificar nodoLleno.
             * Las claves y los datos deben dividirse usando exactamente
             * las mismas posiciones.
             */
            ArrayList<Integer> clavesIzquierda = new ArrayList<>(
                    nodoLleno.getClaves().subList(0, mitad)
            );

            ArrayList<Integer> clavesDerecha = new ArrayList<>(
                    nodoLleno.getClaves().subList(
                            mitad,
                            nodoLleno.getClaves().size()
                    )
            );

            ArrayList<String> datosIzquierda = new ArrayList<>(
                    nodoLleno.getDatos().subList(0, mitad)
            );

            ArrayList<String> datosDerecha = new ArrayList<>(
                    nodoLleno.getDatos().subList(
                            mitad,
                            nodoLleno.getDatos().size()
                    )
            );

            // La hoja original conserva la mitad izquierda.
            nodoLleno.setClaves(clavesIzquierda);
            nodoLleno.setDatos(datosIzquierda);

            // La nueva hoja recibe la mitad derecha.
            nuevoNodo.setClaves(clavesDerecha);
            nuevoNodo.setDatos(datosDerecha);

            /*
             * Antes:
             * nodoLleno -> antiguaSiguiente
             *
             * Después:
             * nodoLleno -> nuevoNodo -> antiguaSiguiente
             */
            nuevoNodo.setSiguiente(nodoLleno.getSiguiente());
            nodoLleno.setSiguiente(nuevoNodo);

            // La primera clave de la nueva hoja se copia al padre.
            int claveSeparadora = nuevoNodo.getClaves().get(0);

            padre.getClaves().add(indice, claveSeparadora);
            padre.getHijos().add(indice + 1, nuevoNodo);
        }
        else {
            // En nodos internos, la llave media sube y se elimina del hijo
            int claveMedia = nodoLleno.getClaves().get(mitad);
            nuevoNodo.setClaves(new ArrayList<>(nodoLleno.getClaves().subList(mitad + 1, nodoLleno.getClaves().size())));
            nodoLleno.setClaves(new ArrayList<>(nodoLleno.getClaves().subList(0, mitad)));

            // Mover los hijos del nodo dividido
            nuevoNodo.setHijos(new ArrayList<>(nodoLleno.getHijos().subList(mitad + 1, nodoLleno.getHijos().size())));
            nodoLleno.setHijos(new ArrayList<>(nodoLleno.getHijos().subList(0, mitad + 1)));

            padre.getClaves().add(indice, claveMedia);
            padre.getHijos().add(indice + 1, nuevoNodo);
        }
    }

    // Impresión del árbol como parte de la interfaz pública del árbol
    public void imprimirArbol() {
        imprimirNodo(raiz, "", true);
    }

    // Impresión recursiva privada de los nodos a partir de uno inicial
    private void imprimirNodo(NodoArbolBMas nodo, String indentacion, boolean esUltimo) {
        System.out.println(indentacion + "+- " + (nodo.esHoja() ? "Hoja > " : "Interno > ") + nodo.getClaves());
        indentacion += esUltimo ? "   " : "|  ";
        for(int i = 0; i < nodo.getHijos().size(); i++) {
            imprimirNodo(nodo.getHijos().get(i), indentacion, i == (nodo.getHijos().size() - 1));
        }
    }

    /**
     * Elimina una clave y su dato asociado del árbol B+.
     *
     * @param clave clave que se desea eliminar.
     * @return true si la clave fue eliminada; false si no existía.
     */
    public boolean eliminar(int clave) {

        // Caso especial: árbol completamente vacío.
        if (raiz.esHoja() && raiz.getClaves().isEmpty()) {
            System.out.println("El árbol está vacío.");
            return false;
        }

        /*
         * Estas listas guardan el camino desde la raíz hasta la hoja.
         *
         * padres.get(i) contiene un nodo interno.
         * indicesHijo.get(i) indica cuál hijo se tomó desde ese padre.
         */
        List<NodoArbolBMas> padres = new ArrayList<>();
        List<Integer> indicesHijo = new ArrayList<>();

        NodoArbolBMas nodoActual = raiz;

        // Se desciende hasta encontrar la hoja correspondiente.
        while (!nodoActual.esHoja()) {

            int indice = 0;

            /*
             * Cuando la clave es igual a un separador,
             * debemos avanzar al hijo derecho.
             */
            while (indice < nodoActual.getClaves().size()
                    && clave >= nodoActual.getClaves().get(indice)) {
                indice++;
            }

            padres.add(nodoActual);
            indicesHijo.add(indice);

            nodoActual = nodoActual.getHijos().get(indice);
        }

        // Busca la posición exacta de la clave dentro de la hoja.
        int posicion = Collections.binarySearch(
                nodoActual.getClaves(),
                clave
        );

        if (posicion < 0) {
            System.out.println("La clave " + clave + " no existe.");
            return false;
        }

        // Se eliminan la clave y su dato de la misma posición.
        nodoActual.getClaves().remove(posicion);
        nodoActual.getDatos().remove(posicion);

        /*
         * Si la raíz también es una hoja, no existe ningún padre
         * ni es necesario aplicar rebalanceo.
         */
        if (nodoActual == raiz) {
            return true;
        }

        int nivel = padres.size() - 1;

        /*
         * El desbalance puede propagarse desde una hoja hacia la raíz.
         */
        while (nodoActual != raiz && tieneDesbalance(nodoActual)) {

            NodoArbolBMas padre = padres.get(nivel);
            int indice = indicesHijo.get(nivel);

            NodoArbolBMas hermanoIzquierdo =
                    indice > 0
                            ? padre.getHijos().get(indice - 1)
                            : null;

            NodoArbolBMas hermanoDerecho =
                    indice < padre.getHijos().size() - 1
                            ? padre.getHijos().get(indice + 1)
                            : null;

            boolean reequilibrado;

            if (nodoActual.esHoja()) {

                reequilibrado = redistribuirHoja(
                        nodoActual,
                        hermanoIzquierdo,
                        hermanoDerecho
                );

                if (!reequilibrado) {
                    fusionarHoja(
                            padre,
                            indice,
                            nodoActual,
                            hermanoIzquierdo,
                            hermanoDerecho
                    );
                }

            } else {

                reequilibrado = redistribuirInterno(
                        nodoActual,
                        hermanoIzquierdo,
                        hermanoDerecho
                );

                if (!reequilibrado) {
                    fusionarInterno(
                            padre,
                            indice,
                            nodoActual,
                            hermanoIzquierdo,
                            hermanoDerecho
                    );
                }
            }

            /*
             * Si se pudo pedir prestado, el desbalance quedó resuelto.
             */
            if (reequilibrado) {
                break;
            }

            /*
             * Si ocurrió una fusión, el padre perdió un hijo.
             * Ahora debe comprobarse si el padre quedó desbalanceado.
             */
            nodoActual = padre;
            nivel--;
        }

        /*
         * Si una raíz interna queda con un único hijo,
         * ese hijo se convierte en la nueva raíz.
         */
        while (!raiz.esHoja() && raiz.getHijos().size() == 1) {
            raiz = raiz.getHijos().get(0);
        }

        // Actualiza todas las claves separadoras internas.
        reconstruirClavesInternas(raiz);

        return true;
    }

    /**
     * Determina si un nodo está por debajo de su ocupación mínima.
     */
    private boolean tieneDesbalance(NodoArbolBMas nodo) {

        if (nodo.esHoja()) {
            return nodo.getClaves().size() < minimoClavesHoja();
        }

        return nodo.getHijos().size() < minimoHijosInterno();
    }

    /**
     * Cantidad mínima de claves permitidas en una hoja.
     *
     * Para orden 4:
     * ceil((4 - 1) / 2) = 2 claves.
     */
    private int minimoClavesHoja() {
        return (int) Math.ceil((orden - 1) / 2.0);
    }

    /**
     * Cantidad mínima de hijos permitidos en un nodo interno.
     *
     * Para orden 4:
     * ceil(4 / 2) = 2 hijos.
     */
    private int minimoHijosInterno() {
        return (int) Math.ceil(orden / 2.0);
    }

    /**
     * Intenta corregir el desbalance de una hoja pidiendo
     * una clave y su dato a un hermano.
     */
    private boolean redistribuirHoja(
            NodoArbolBMas hojaActual,
            NodoArbolBMas hermanoIzquierdo,
            NodoArbolBMas hermanoDerecho
    ) {

        /*
         * Primero intenta tomar el último elemento
         * del hermano izquierdo.
         */
        if (hermanoIzquierdo != null
                && hermanoIzquierdo.getClaves().size()
                > minimoClavesHoja()) {

            int ultimaPosicion =
                    hermanoIzquierdo.getClaves().size() - 1;

            int clavePrestada =
                    hermanoIzquierdo.getClaves().remove(ultimaPosicion);

            String datoPrestado =
                    hermanoIzquierdo.getDatos().remove(ultimaPosicion);

            hojaActual.getClaves().add(0, clavePrestada);
            hojaActual.getDatos().add(0, datoPrestado);

            return true;
        }

        /*
         * Si el hermano izquierdo no puede prestar,
         * intenta tomar el primer elemento del derecho.
         */
        if (hermanoDerecho != null
                && hermanoDerecho.getClaves().size()
                > minimoClavesHoja()) {

            int clavePrestada =
                    hermanoDerecho.getClaves().remove(0);

            String datoPrestado =
                    hermanoDerecho.getDatos().remove(0);

            hojaActual.getClaves().add(clavePrestada);
            hojaActual.getDatos().add(datoPrestado);

            return true;
        }

        return false;
    }

    /**
     * Fusiona una hoja desbalanceada con uno de sus hermanos.
     */
    private void fusionarHoja(
            NodoArbolBMas padre,
            int indice,
            NodoArbolBMas hojaActual,
            NodoArbolBMas hermanoIzquierdo,
            NodoArbolBMas hermanoDerecho
    ) {

        if (hermanoIzquierdo != null) {

            /*
             * El hermano izquierdo absorbe a la hoja actual.
             */
            hermanoIzquierdo.getClaves().addAll(
                    hojaActual.getClaves()
            );

            hermanoIzquierdo.getDatos().addAll(
                    hojaActual.getDatos()
            );

            /*
             * Se conserva la cadena de hojas:
             *
             * hermanoIzquierdo -> siguiente de hojaActual
             */
            hermanoIzquierdo.setSiguiente(
                    hojaActual.getSiguiente()
            );

            // El padre deja de referenciar la hoja absorbida.
            padre.getHijos().remove(indice);

        } else if (hermanoDerecho != null) {

            /*
             * La hoja actual absorbe al hermano derecho.
             */
            hojaActual.getClaves().addAll(
                    hermanoDerecho.getClaves()
            );

            hojaActual.getDatos().addAll(
                    hermanoDerecho.getDatos()
            );

            hojaActual.setSiguiente(
                    hermanoDerecho.getSiguiente()
            );

            // El padre deja de referenciar al hermano derecho.
            padre.getHijos().remove(indice + 1);
        }
    }

    /**
     * Intenta corregir el desbalance de un nodo interno
     * pidiendo un hijo a uno de sus hermanos.
     */
    private boolean redistribuirInterno(
            NodoArbolBMas nodoActual,
            NodoArbolBMas hermanoIzquierdo,
            NodoArbolBMas hermanoDerecho
    ) {

        if (hermanoIzquierdo != null
                && hermanoIzquierdo.getHijos().size()
                > minimoHijosInterno()) {

            int ultimaPosicion =
                    hermanoIzquierdo.getHijos().size() - 1;

            NodoArbolBMas hijoPrestado =
                    hermanoIzquierdo.getHijos().remove(ultimaPosicion);

            nodoActual.getHijos().add(0, hijoPrestado);

            return true;
        }

        if (hermanoDerecho != null
                && hermanoDerecho.getHijos().size()
                > minimoHijosInterno()) {

            NodoArbolBMas hijoPrestado =
                    hermanoDerecho.getHijos().remove(0);

            nodoActual.getHijos().add(hijoPrestado);

            return true;
        }

        return false;
    }

    /**
     * Fusiona un nodo interno desbalanceado con un hermano.
     */
    private void fusionarInterno(
            NodoArbolBMas padre,
            int indice,
            NodoArbolBMas nodoActual,
            NodoArbolBMas hermanoIzquierdo,
            NodoArbolBMas hermanoDerecho
    ) {

        if (hermanoIzquierdo != null) {

            // El hermano izquierdo absorbe todos los hijos.
            hermanoIzquierdo.getHijos().addAll(
                    nodoActual.getHijos()
            );

            padre.getHijos().remove(indice);

        } else if (hermanoDerecho != null) {

            // El nodo actual absorbe todos los hijos del derecho.
            nodoActual.getHijos().addAll(
                    hermanoDerecho.getHijos()
            );

            padre.getHijos().remove(indice + 1);
        }
    }

    /**
     * Reconstruye las claves separadoras de todos los nodos internos.
     *
     * Cada clave interna representa la menor clave del hijo
     * ubicado inmediatamente a su derecha.
     *
     * @return menor clave existente en el subárbol recibido.
     */
    private int reconstruirClavesInternas(NodoArbolBMas nodo) {

        // Las hojas ya contienen las claves reales.
        if (nodo.esHoja()) {

            if (nodo.getClaves().isEmpty()) {
                return Integer.MAX_VALUE;
            }

            return nodo.getClaves().get(0);
        }

        // Se eliminan los separadores anteriores.
        nodo.getClaves().clear();

        // Obtiene la menor clave del primer hijo.
        int menorClaveSubarbol =
                reconstruirClavesInternas(
                        nodo.getHijos().get(0)
                );

        /*
         * Por cada hijo después del primero,
         * su menor clave se agrega como separador.
         */
        for (int i = 1; i < nodo.getHijos().size(); i++) {

            int claveSeparadora =
                    reconstruirClavesInternas(
                            nodo.getHijos().get(i)
                    );

            nodo.getClaves().add(claveSeparadora);
        }

        return menorClaveSubarbol;
    }

    /**
     * Busca la posición correspondiente a una clave inicial y muestra
     * la cantidad indicada de elementos consecutivos del árbol.
     *
     * @param claveInicial clave desde la cual comienza el recorrido.
     * @param cantidad cantidad máxima de elementos por mostrar.
     */
    public void recorrerRango(int claveInicial, int cantidad) {

        // No tiene sentido recorrer una cantidad negativa o igual a cero.
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que cero.");
            return;
        }

        // El árbol está vacío cuando la raíz es una hoja sin claves.
        if (raiz.getClaves().isEmpty()) {
            System.out.println("El árbol está vacío.");
            return;
        }

        // Se localiza la hoja donde se encuentra o debería encontrarse la clave.
        NodoArbolBMas hojaActual = buscarHoja(claveInicial);

        // Busca la primera clave mayor o igual que la clave inicial.
        int posicion = 0;

        while (posicion < hojaActual.getClaves().size()
                && hojaActual.getClaves().get(posicion) < claveInicial) {
            posicion++;
        }

        int elementosMostrados = 0;

        System.out.println("\nRecorrido desde la clave " + claveInicial + ":");

        /*
         * Se recorren las hojas enlazadas hasta mostrar la cantidad solicitada
         * o hasta alcanzar el final del árbol.
         */
        while (hojaActual != null && elementosMostrados < cantidad) {

            while (posicion < hojaActual.getClaves().size()
                    && elementosMostrados < cantidad) {

                int clave = hojaActual.getClaves().get(posicion);
                String dato = hojaActual.getDatos().get(posicion);

                System.out.println(clave + " -> " + dato);

                posicion++;
                elementosMostrados++;
            }

            // Continúa desde la primera posición de la siguiente hoja.
            hojaActual = hojaActual.getSiguiente();
            posicion = 0;
        }

        if (elementosMostrados == 0) {
            System.out.println("No existen elementos desde la clave indicada.");
        }
    }

    /**
     * Localiza la hoja donde se encuentra o debería encontrarse una clave.
     *
     * @param clave clave utilizada para descender por el árbol.
     * @return hoja correspondiente a la clave.
     */
    private NodoArbolBMas buscarHoja(int clave) {

        NodoArbolBMas nodoActual = raiz;

        // Se desciende hasta llegar a un nodo hoja.
        while (!nodoActual.esHoja()) {

            int indiceHijo = 0;

            /*
             * En un árbol B+, cuando la clave es igual al separador,
             * se debe continuar por el hijo derecho.
             */
            while (indiceHijo < nodoActual.getClaves().size()
                    && clave >= nodoActual.getClaves().get(indiceHijo)) {
                indiceHijo++;
            }

            nodoActual = nodoActual.getHijos().get(indiceHijo);
        }

        return nodoActual;
    }
}