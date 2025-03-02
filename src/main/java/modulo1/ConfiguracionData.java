package modulo1;

/**
 * Clase que almacena la configuración de la sucursal y los datos de los usuarios.
 * Contiene información sobre la cantidad de cajas disponibles y los usuarios registrados.
 * 
 * @author user
 */
public class ConfiguracionData {

    // Nombre de la sucursal
    public String sucursal;

    // Número de cajas normales ingresadas por el usuario
    public int cajasNormales;       

    // Número fijo de cajas para trámites rápidos (siempre 1)
    public int cajaTramitesRapidos; 

    // Número fijo de cajas preferenciales (siempre 1)
    public int cajaPreferencial;    

    // Total de cajas, calculado como la suma de todas
    public int totalCajas;          

    // Datos de los usuarios registrados
    public String usuario1;
    public String password1;
    public String usuario2;
    public String password2;


    public ConfiguracionData(String sucursal, int cajasNormales) {
        this.sucursal = sucursal;         // Asigna el nombre de la sucursal
        this.cajasNormales = cajasNormales; // Guarda la cantidad de cajas normales
        this.cajaTramitesRapidos = 1;     // Siempre hay 1 caja para trámites rápidos
        this.cajaPreferencial = 1;        // Siempre hay 1 caja preferencial
        this.totalCajas = cajasNormales + 2; // Calcula el total de cajas disponibles
        this.usuario1 = "";               // Inicializa el primer usuario como vacío
        this.password1 = "";              // Inicializa la contraseña del primer usuario como vacía
        this.usuario2 = "";               // Inicializa el segundo usuario como vacío
        this.password2 = "";              // Inicializa la contraseña del segundo usuario como vacía
    }

}
