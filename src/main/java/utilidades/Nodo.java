package utilidades;

import java.time.LocalTime;

/**
 * Clase que representa un nodo de una lista enlazada, utilizado para gestionar trámites.
 */
public class Nodo {

    private String nombre; // Nombre de la persona asociada al trámite
    private int id; // Identificador único del nodo
    private int edad; // Edad de la persona
    private String tramite; // Descripción del trámite
    private char tipoTramite; // Tipo de trámite (puede representar una categoría)
    private int cajaAsignada; // Número de caja asignada para el trámite
    private boolean cajaOcupada; // Indica si la caja asignada está ocupada
    private transient Nodo siguiente; // Referencia al siguiente nodo en la lista
    private LocalTime horaCreacion; // Hora en que se creó el nodo
    private LocalTime horaAtencion; // Hora en que el trámite fue atendido (null hasta que sea atendido)

    /**
     * Constructor que inicializa un nodo con los datos de un trámite.
     * @param nombre Nombre de la persona.
     * @param id Identificación única del trámite.
     * @param edad Edad de la persona.
     * @param tramite Descripción del trámite.
     * @param tipoTramite Tipo de trámite (char representando una categoría).
     * @param cajaAsignada Número de caja asignada.
     * @param cajaOcupada Indica si la caja está ocupada.
     */
    public Nodo(String nombre, int id, int edad, String tramite, char tipoTramite, int cajaAsignada, boolean cajaOcupada) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.tramite = tramite;
        this.tipoTramite = tipoTramite;
        this.cajaAsignada = cajaAsignada;
        this.cajaOcupada = cajaOcupada;
        this.siguiente = null; // Inicialmente no apunta a otro nodo
        this.horaCreacion = LocalTime.now().plusNanos(System.nanoTime() % 1_000_000); // Se registra la hora de creación
        this.horaAtencion = null; // Se asignará cuando el trámite sea atendido
    }

    // Métodos getter para obtener los valores de los atributos del nodo

    public int getId() {
        return id;
    }

    public int getEdad() {
        return edad;
    }

    public String getTramite() {
        return tramite;
    }

    public char getTipoTramite() {
        return tipoTramite;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente; // Asigna el siguiente nodo en la lista enlazada
    }

    public String getNombre() {
        return nombre;
    }

    public int getCajaAsignada() {
        return cajaAsignada;
    }

    public boolean isCajaOcupada() {
        return cajaOcupada;
    }

    public void setCajaOcupada(boolean cajaOcupada) {
        this.cajaOcupada = cajaOcupada; // Cambia el estado de ocupación de la caja
    }

    public LocalTime getHoraCreacion() {
        return horaCreacion;
    }

    public LocalTime getHoraAtencion() {
        return horaAtencion;
    }

    /**
     * Asigna la hora de atención al trámite solo si aún no ha sido atendido.
     */
    public void setHoraAtencion() {
        if (this.horaAtencion == null) { // ✅ Evita reasignar la hora de atención si ya se estableció antes
            this.horaAtencion = LocalTime.now();
        }
    }
}
