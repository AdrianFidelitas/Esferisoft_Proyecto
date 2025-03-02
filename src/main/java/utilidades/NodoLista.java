package utilidades;

/**
 * Clase que representa un nodo en una lista enlazada.
 * Cada nodo almacena un dato y una referencia al siguiente nodo en la lista.
 */
public class NodoLista {
    private String dato; // Almacena el valor del nodo
    private NodoLista siguiente; // Referencia al siguiente nodo en la lista

    /**
     * Constructor que inicializa un nodo con un dato.
     * @param dato El valor que se almacenará en el nodo.
     */
    public NodoLista(String dato) {
        this.dato = dato;
        this.siguiente = null; // Inicialmente, no tiene un nodo siguiente
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     * @return El dato del nodo.
     */
    public String getDato() {
        return dato;
    }

    /**
     * Obtiene la referencia al siguiente nodo en la lista.
     * @return El nodo siguiente, o null si no hay más nodos.
     */
    public NodoLista getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el siguiente nodo en la lista enlazada.
     * @param siguiente Nodo que será el siguiente en la lista.
     */
    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }
}
