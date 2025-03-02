package utilidades;

import java.io.File;
import java.io.IOException;
import modulo1.Configuracion;

public class Cola {

    private Configuracion config = new Configuracion(); // Configuración del sistema
    private Nodo frente; // Nodo que representa el frente de la cola
    private Nodo fin; // Nodo que representa el final de la cola
    private int contadorID = 0; // Contador de IDs para los clientes

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    /**
     * Método para agregar un cliente a la cola.
     * @param nombre Nombre del cliente
     * @param edad Edad del cliente
     * @param tramite Tipo de trámite solicitado
     * @param tipoTramite Identificador del tipo de trámite ('P', 'A', 'B')
     * @param esPreferencial Indica si el cliente es preferencial
     * @param esRapido Indica si el trámite es rápido
     */
    public void encolarCliente(String nombre, int edad, String tramite, char tipoTramite, boolean esPreferencial, boolean esRapido) {
        int cajaAsignada = asignarCaja(esPreferencial, esRapido);
        int idGenerado = contadorID++;

        if (cajaAsignada == -1) {
            System.out.println("⏳ " + nombre + " está en espera. No hay caja disponible.");
        } else {
            // Crear un nuevo nodo con la información del cliente
            Nodo nuevo = new Nodo(nombre, idGenerado, edad, tramite, tipoTramite, cajaAsignada, (frente == null));
            
            // Si la cola está vacía, el nuevo nodo será el frente y fin
            if (frente == null) {
                frente = nuevo;
                fin = nuevo;

                // Verificar la existencia del archivo de tickets
                File archivo = new File("src/main/java/data/tiquetes.json");
                if (!archivo.exists()) {
                    try {
                        archivo.createNewFile();
                        System.out.println("Archivo 'tiquetes.json' creado.");
                    } catch (IOException e) {
                        System.out.println("Error al crear el archivo: " + e.getMessage());
                    }
                }
            } else {
                // Agregar el nuevo nodo al final de la cola
                fin.setSiguiente(nuevo);
                fin = nuevo;
            }

            // Calcular cuántas personas están delante en la misma caja
            int personasAdelante = contarPersonasAdelante(nuevo);
            System.out.println("---------------------------------");
            System.out.println("Tiquete creado para " + nombre);
            System.out.println("ID Asignado: " + idGenerado);
            System.out.println("Caja asignada: " + cajaAsignada);
            System.out.println("---------------------------------");
            if (personasAdelante > 0) {
                System.out.println("Personas por delante: " + personasAdelante);
            } else {
                System.out.println("Es su turno, puede pasar a la caja.");
            }
        }
    }

    /**
     * Método para asignar una caja según la prioridad del cliente.
     * @param esPreferencial Indica si el cliente tiene prioridad
     * @param esRapido Indica si el trámite es rápido
     * @return Número de la caja asignada
     */
    private int asignarCaja(boolean esPreferencial, boolean esRapido) {
        if (esPreferencial) {
            return 1; // Caja exclusiva para clientes preferenciales
        } else if (esRapido) {
            return 2; // Caja exclusiva para trámites rápidos
        } else {
            int primeraCajaNormal = 3;
            int ultimaCajaNormal = primeraCajaNormal + config.getCajasNormales() - 1;
            
            // Lógica para asignar la siguiente caja normal disponible
            return primeraCajaNormal + (contadorID % (ultimaCajaNormal - primeraCajaNormal + 1));
        }
    }

    /**
     * Método para contar cuántas personas hay delante en la misma caja y tipo de trámite.
     * @param nodo Nodo del cliente actual
     * @return Número de personas delante en la cola con el mismo tipo de trámite y caja
     */
    private int contarPersonasAdelante(Nodo nodo) {
        int contador = 0;
        Nodo temp = frente;
        while (temp != null && temp != nodo) {
            if (temp.getTipoTramite() == nodo.getTipoTramite() && temp.getCajaAsignada() == nodo.getCajaAsignada()) {
                contador++;
            }
            temp = temp.getSiguiente();
        }
        return contador;
    }

    /**
     * Método para atender a un cliente y eliminarlo de la cola.
     * @return Nodo del cliente atendido
     */
    public Nodo desencolar() {
        if (frente == null) {
            return null;
        }
        Nodo nodoAtendido = frente;
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        return nodoAtendido;
    }

    /**
     * Verifica si la cola está vacía.
     * @return true si la cola está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return frente == null;
    }

    /**
     * Obtiene el número de clientes registrados.
     * @return Contador de clientes encolados
     */
    public int getContadorID() {
        return contadorID;
    }

    /**
     * Obtiene el primer cliente en la cola.
     * @return Nodo del primer cliente en la cola
     */
    public Nodo getFrente() {
        return frente;
    }
}
