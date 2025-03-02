package modulo2;

import javax.swing.JOptionPane;
import modulo1.Configuracion;
import utilidades.Cola;
import utilidades.ListaEnlazada;

public class AgregarCliente {
    public static void agregarCliente(Configuracion config, Cola cola) {
        // Pedir datos del cliente
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del cliente:"));

        //  Crear lista de trámites con ListaEnlazada
        ListaEnlazada listaTramites = new ListaEnlazada();
        listaTramites.agregarOpcion("Depósito");
        listaTramites.agregarOpcion("Retiro");
        listaTramites.agregarOpcion("Cambio de Divisas");
        listaTramites.agregarOpcion("Servicios");

        // Mostrar opciones como texto y solicitar selección
        String opcionesTexto = listaTramites.obtenerOpcionesComoTexto();
        String seleccionTexto = JOptionPane.showInputDialog(
            "Seleccione el trámite ingresando el número:\n" + opcionesTexto
        );

        // Validar que la entrada no sea nula
        if (seleccionTexto == null) {
            JOptionPane.showMessageDialog(null, " Trámite inválido.");
            return;
        }

        //  Convertir la selección a número y manejar errores
        int seleccionTramite;
        try {
            seleccionTramite = Integer.parseInt(seleccionTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, " Entrada no válida.");
            return;
        }

        //  Obtener el trámite seleccionado
        String tramite = listaTramites.obtenerOpcionPorIndice(seleccionTramite);
        if (tramite == null) {
            JOptionPane.showMessageDialog(null, " Trámite inválido.");
            return;
        }

        //  Crear lista de tipos de trámite con ListaEnlazada
        ListaEnlazada listaTiposTramite = new ListaEnlazada();
        listaTiposTramite.agregarOpcion("Preferencial (P)");
        listaTiposTramite.agregarOpcion("Un solo trámite (A)");
        listaTiposTramite.agregarOpcion("Más de un trámite (B)");

        //  Mostrar opciones como texto y solicitar selección
        String opcionesTipoTexto = listaTiposTramite.obtenerOpcionesComoTexto();
        String seleccionTipoTexto = JOptionPane.showInputDialog(
            "Seleccione el tipo de trámite ingresando el número:\n" + opcionesTipoTexto
        );

        //  Validar que la entrada no sea nula
        if (seleccionTipoTexto == null) {
            JOptionPane.showMessageDialog(null, " Tipo de trámite inválido.");
            return;
        }

        //  Convertir la selección a número y manejar errores
        int seleccionTipo;
        try {
            seleccionTipo = Integer.parseInt(seleccionTipoTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, " Entrada no válida.");
            return;
        }

        //  Obtener el tipo de trámite seleccionado
        String tipoSeleccionado = listaTiposTramite.obtenerOpcionPorIndice(seleccionTipo);
        if (tipoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, " Tipo de trámite inválido.");
            return;
        }

        //  Asignar tipo de trámite según la selección
        char tipoTramite = 'B'; // Por defecto, más de un trámite
        if (tipoSeleccionado.startsWith("Preferencial")) tipoTramite = 'P';
        if (tipoSeleccionado.startsWith("Un solo")) tipoTramite = 'A';

        //  Determinar si es preferencial o rápido
        boolean esPreferencial = (edad >= 60 || tipoTramite == 'P');
        boolean esRapido = (tipoTramite == 'A');

        //  Encolar cliente en la cola correcta con sus datos
        cola.encolarCliente(nombre, edad, tramite, tipoTramite, esPreferencial, esRapido);

        // Mensaje de confirmación con el ID asignado automáticamente
        JOptionPane.showMessageDialog(null, "Cliente agregado");
    }
}
