/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

/**
 * Clase que implementa una lista enlazada simple para almacenar opciones.
 */
public class ListaEnlazada {
    private NodoLista cabeza; // Referencia al primer nodo de la lista

    /**
     * Constructor que inicializa la lista como vacía.
     */
    public ListaEnlazada() {
        this.cabeza = null;
    }

    /**
     * Agrega una nueva opción a la lista.
     * @param opcion Texto de la opción a agregar.
     */
    public void agregarOpcion(String opcion) {
        NodoLista nuevo = new NodoLista(opcion); // Se crea un nuevo nodo con la opción
        if (cabeza == null) { 
            // Si la lista está vacía, el nuevo nodo se convierte en la cabeza
            cabeza = nuevo;
        } else {
            // Si la lista ya tiene elementos, se recorre hasta el último nodo
            NodoLista actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            // Se enlaza el nuevo nodo al final de la lista
            actual.setSiguiente(nuevo);
        }
    }

    /**
     * Muestra en consola todas las opciones almacenadas en la lista.
     */
    public void mostrarOpciones() {
        NodoLista actual = cabeza;
        int indice = 1; // Inicia la numeración desde 1

        while (actual != null) {
            System.out.println(indice + ". " + actual.getDato()); // Muestra la opción en consola
            actual = actual.getSiguiente(); // Avanza al siguiente nodo
            indice++; // Incrementa el índice
        }
    }

    /**
     * Obtiene el dato de una opción a partir de su índice en la lista.
     * @param indice Posición de la opción en la lista (empezando desde 1).
     * @return Texto de la opción si existe, de lo contrario retorna null.
     */
    public String obtenerOpcionPorIndice(int indice) {
        NodoLista actual = cabeza;
        int contador = 1; // Inicia la numeración desde 1

        while (actual != null) {
            if (contador == indice) {
                return actual.getDato(); // Retorna la opción si coincide con el índice buscado
            }
            actual = actual.getSiguiente(); // Avanza al siguiente nodo
            contador++;
        }
        return null; // Retorna null si el índice no existe en la lista
    }

    /**
     * Retorna todas las opciones almacenadas en la lista en formato de texto.
     * @return Cadena de texto con todas las opciones numeradas.
     */
    public String obtenerOpcionesComoTexto() {
        NodoLista actual = cabeza;
        int indice = 1;
        String opciones = ""; // Almacenará todas las opciones en una sola cadena

        while (actual != null) {
            opciones += indice + ". " + actual.getDato() + "\n"; // Concatenación de opciones con salto de línea
            actual = actual.getSiguiente(); // Avanza al siguiente nodo
            indice++;
        }
        return opciones; // Devuelve la lista de opciones en formato de texto
    }
}
