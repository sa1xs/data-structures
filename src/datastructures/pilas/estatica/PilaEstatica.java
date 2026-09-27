package pilas.estatica;

public class PilaEstatica {

    //ATRIBUTOS
    private String[] elementos;
    private int top;

    //METODOS
    //CONSTRUCTOR
    public PilaEstatica(int longitud){
        elementos = new String[longitud];
        top = -1;
    }

    //OPERACIONES
    private boolean estaVacia(){
        return top == -1;
    }

    private boolean estaLlena(){
        return top == elementos.length -1;
    }

    public void push(String elemento){
        if(estaLlena()){
            System.out.println("La pila esta llena\n");
            return;
        }
        elementos[++top] = elemento;
    }

    public String pop(){
        if (estaVacia()){
            System.out.println("La pila esta vacia\n");
            return null;
        }
        return elementos[top--];
    }

    public String peek(){
        if (estaVacia()){
            System.out.println("La pila esta vacia\n");
            return null;
        }
        return elementos[top];
    }
}
