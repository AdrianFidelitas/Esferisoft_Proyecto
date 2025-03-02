package caso.esferisoft;

import modulo1.*;


public class Esferisoft_Proyecto {
    public static void main(String[] args) {
        // Crea una instancia de Configuracion, que probablemente maneja parámetros del sistema
        Configuracion config = new Configuracion();

        // Crea una instancia de GestionUsuarios, pasando la configuración como argumento
        GestionUsuarios gestionUsuarios = new GestionUsuarios(config);

        // Muestra el menú de gestión de usuarios
        gestionUsuarios.mostrarMenuUsuarios();
    }
}

