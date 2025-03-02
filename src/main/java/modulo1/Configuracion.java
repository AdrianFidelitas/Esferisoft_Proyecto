/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Clase que gestiona la configuración de la sucursal y la cantidad de cajas.
 * Se encarga de cargar, guardar y solicitar la configuración al usuario.
 */
public class Configuracion {

    // Objeto que almacena la configuración de la sucursal
    private ConfiguracionData datos;

    // Directorio donde se guardará el archivo de configuración
    private static final String DIRECTORIO = "src/main/java/data";

    // Ruta completa del archivo de configuración en formato JSON
    private static final String ARCHIVO_CONFIG = DIRECTORIO + "/config.json";

    /**
     * Constructor de Configuracion.
     * Verifica si existe un archivo de configuración; si no, solicita la información y la guarda.
     */
    public Configuracion() {
        if (existeArchivo()) {
            cargarConfiguracion(); // Carga la configuración desde el archivo JSON
        } else {
            solicitarConfiguracion(); // Solicita los datos al usuario
            guardarConfiguracion();   // Guarda la configuración en un archivo JSON
        }
    }

    /**
     * Verifica si el archivo de configuración existe en la ruta especificada.
     * 
     * @return true si el archivo existe, false en caso contrario.
     */
    private boolean existeArchivo() {
        File archivo = new File(ARCHIVO_CONFIG);
        return archivo.exists();
    }

    /**
     * Carga la configuración desde el archivo JSON.
     * Si el archivo contiene datos inválidos, se muestra un mensaje de error.
     */
    private void cargarConfiguracion() {
        try (FileReader reader = new FileReader(ARCHIVO_CONFIG)) {
            Gson gson = new Gson();
            datos = gson.fromJson(reader, ConfiguracionData.class);

            // Verifica que los datos cargados sean válidos
            if (datos == null || datos.sucursal == null || datos.cajasNormales <= 0) {
                throw new IllegalArgumentException("El archivo JSON está vacío o tiene datos inválidos.");
            }

            // Si el JSON no tiene usuarios, inicializarlos como vacíos
            if (datos.usuario1 == null) {
                datos.usuario1 = "";
            }
            if (datos.password1 == null) {
                datos.password1 = "";
            }
            if (datos.usuario2 == null) {
                datos.usuario2 = "";
            }
            if (datos.password2 == null) {
                datos.password2 = "";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la configuración: " + e.getMessage());
        }
    }

    /**
     * Solicita la configuración al usuario mediante cuadros de diálogo.
     * Pide el nombre de la sucursal y la cantidad de cajas normales.
     */
    private void solicitarConfiguracion() {
        String sucursal = JOptionPane.showInputDialog("Ingrese el nombre de la sucursal:");
        int cajasNormales = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de cajas normales:"));

        datos = new ConfiguracionData(sucursal, cajasNormales);
    }

    /**
     * Guarda la configuración en un archivo JSON.
     * Si el directorio no existe, lo crea antes de guardar el archivo.
     */
    public void guardarConfiguracion() {
        try {
            File directorio = new File(DIRECTORIO);
            if (!directorio.exists()) {
                directorio.mkdirs(); // Crea el directorio si no existe
            }

            try (FileWriter writer = new FileWriter(ARCHIVO_CONFIG)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                writer.write(gson.toJson(datos));
            }

            JOptionPane.showMessageDialog(null, "Configuración guardada correctamente.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la configuración: " + e.getMessage());
        }
    }

    /**
     * Obtiene el nombre de la sucursal configurada.
     * 
     * @return Nombre de la sucursal.
     */
    public String getSucursal() {
        return datos.sucursal;
    }

    /**
     * Obtiene el número total de cajas disponibles en la sucursal.
     * 
     * @return Número total de cajas.
     */
    public int getTotalCajas() {
        return datos.totalCajas;
    }

    /**
     * Obtiene la cantidad de cajas normales configuradas.
     * 
     * @return Número de cajas normales.
     */
    public int getCajasNormales() {
        return datos.cajasNormales;
    }

    /**
     * Obtiene la cantidad de cajas destinadas a trámites rápidos.
     * 
     * @return Número de cajas para trámites rápidos (siempre 1).
     */
    public int getCajaTramitesRapidos() {
        return datos.cajaTramitesRapidos;
    }

    /**
     * Obtiene la cantidad de cajas preferenciales configuradas.
     * 
     * @return Número de cajas preferenciales (siempre 1).
     */
    public int getCajaPreferencial() {
        return datos.cajaPreferencial;
    }

    /**
     * Obtiene los datos completos de la configuración.
     * 
     * @return Objeto ConfiguracionData con la información de la sucursal.
     */
    public ConfiguracionData getDatos() {
        return datos;
    }

    /**
     * Verifica si el archivo de configuración JSON existe en la ruta especificada.
     * 
     * @return true si el archivo existe, false en caso contrario.
     */
    public boolean archivoConfiguracionExiste() {
        File archivo = new File(ARCHIVO_CONFIG);
        return archivo.exists();
    }
}
