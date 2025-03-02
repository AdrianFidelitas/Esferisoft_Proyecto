package utilidades;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Clase que maneja la serialización y deserialización de una cola de nodos en formato JSON.
 */
public class Serializacion {

    private static final String RUTA_ARCHIVO = "src/main/java/data/tiquetes.json"; // Ruta donde se guarda el archivo JSON
    private final Gson gson; // Objeto Gson para manejar la conversión entre JSON y objetos Java

    /**
     * Constructor que inicializa el objeto Gson con formato legible.
     */
    public Serializacion() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Serializa la cola de nodos y la guarda en un archivo JSON.
     * @param cola La cola que se desea serializar.
     */
    public void serializarCola(Cola cola) {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            writer.write("["); // Inicia el array JSON

            Nodo actual = cola.getFrente();
            boolean primero = true;

            while (actual != null) {
                if (!primero) {
                    writer.write(","); // Agrega coma entre objetos JSON
                }

                // Convierte cada nodo a formato JSON y lo escribe en el archivo
                String jsonNodo = gson.toJson(actual);
                writer.write(jsonNodo);

                primero = false;
                actual = actual.getSiguiente();
            }

            writer.write("]"); // Cierra el array JSON
            System.out.println("Cola guardada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la cola: " + e.getMessage());
        }
    }

    /**
     * Deserializa la cola desde un archivo JSON y la reconstruye en memoria.
     * @return Una cola reconstruida desde el archivo, o una nueva cola si el archivo no existe o está vacío.
     */
    public Cola deserializarCola() {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            System.out.println(" No hay datos previos, iniciando cola vacía.");
            return new Cola();
        }

        Cola cola = new Cola();
        try (FileReader reader = new FileReader(RUTA_ARCHIVO)) {
            String contenido = "";
            int caracter;
            while ((caracter = reader.read()) != -1) {
                contenido += (char) caracter;
            }

            contenido = contenido.trim();
            if (contenido.length() <= 2) { // Si el archivo solo contiene "[]", está vacío
                System.out.println("Archivo vacío, iniciando cola vacía.");
                return new Cola();
            }

            contenido = contenido.substring(1, contenido.length() - 1).trim(); // Elimina los corchetes []
            String objeto = "";
            int idMax = 0;

            // Recorre el contenido del JSON manualmente para reconstruir la cola
            for (int i = 0; i < contenido.length(); i++) {
                char c = contenido.charAt(i);

                if (c == '{') {
                    objeto = "{"; // Inicia un nuevo objeto JSON
                } else if (c == '}') {
                    objeto += "}"; // Termina un objeto JSON

                    // Convierte el objeto JSON en un nodo y lo agrega a la cola
                    Nodo nodo = gson.fromJson(objeto, Nodo.class);
                    cola.encolarCliente(
                        nodo.getNombre(),
                        nodo.getEdad(),
                        nodo.getTramite(),
                        nodo.getTipoTramite(),
                        nodo.getTipoTramite() == 'P', // Verifica si es tipo P
                        nodo.getTipoTramite() == 'B'  // Verifica si es tipo B
                    );
                    idMax++;
                    objeto = "";
                } else {
                    objeto += c; // Continúa formando el objeto JSON
                }
            }

            System.out.println("Cola cargada desde el archivo.");
        } catch (IOException e) {
            System.out.println("Error al cargar la cola: " + e.getMessage());
        }
        return cola;
    }
}
