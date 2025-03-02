package modulo1;

import javax.swing.JOptionPane;
import modulo1.*;
import utilidades.*;
import modulo2.*;

public class GestionUsuarios {

    private Configuracion config; // Instancia de configuración del sistema
    private ConfiguracionData datos; // Instancia para almacenar datos de usuario

    // Constructor: Inicializa la configuración y obtiene los datos actuales
    public GestionUsuarios(Configuracion config) {
        this.config = config;  // Guarda la referencia a `Configuracion`
        this.datos = config.getDatos(); // Obtiene los datos actuales de usuario
    }

    // Método para mostrar el menú de gestión de usuarios
    public void mostrarMenuUsuarios() {
        // Verifica si existen usuarios registrados
        boolean hayUsuarios = !datos.usuario1.isEmpty() || !datos.usuario2.isEmpty();

        if (!hayUsuarios) {
            // Si no hay usuarios, solo muestra la opción de registro o salida
            String opcion = JOptionPane.showInputDialog(
                    "No hay usuarios registrados. ¿Desea registrarse?\n1. Registrarse\n2. Salir");

            if ("1".equals(opcion)) {
                registrarUsuario(); // Llama al método de registro
            }
        } else {
            // Si hay usuarios, permite iniciar sesión o registrar nuevos usuarios
            String opcion = JOptionPane.showInputDialog(
                    "Seleccione una opción:\n1. Iniciar sesión\n2. Registrarse\n3. Salir");

            if ("1".equals(opcion)) {
                iniciarSesion(); // Llama al método de inicio de sesión
            } else if ("2".equals(opcion)) {
                registrarUsuario(); // Llama al método de registro
            }
        }
    }

    // Método para registrar un nuevo usuario
    private void registrarUsuario() {
        // Verifica si ya se alcanzó el número máximo de usuarios permitidos
        if (!datos.usuario1.isEmpty() && !datos.usuario2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se pueden registrar más usuarios.");
            return;
        }

        // Solicita los datos del usuario a registrar
        String username = JOptionPane.showInputDialog("Ingrese un nombre de usuario:");
        String password = JOptionPane.showInputDialog("Ingrese una contraseña:");

        // Verifica que el usuario no haya cancelado y que los campos no estén vacíos
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Registro cancelado.");
            return;
        }

        // Guarda los datos en el primer espacio disponible
        if (datos.usuario1.isEmpty()) {
            datos.usuario1 = username;
            datos.password1 = password;
        } else {
            datos.usuario2 = username;
            datos.password2 = password;
        }

        // Guarda la configuración actualizada
        config.guardarConfiguracion();

        JOptionPane.showMessageDialog(null, "Usuario registrado con éxito.");
        iniciarSesion(); // Llama a iniciar sesión después del registro
    }

    // Método para iniciar sesión de un usuario registrado
    private void iniciarSesion() {
        boolean autenticado = false;

        while (!autenticado) {
            // Solicita credenciales al usuario
            String username = JOptionPane.showInputDialog("Ingrese su nombre de usuario:");
            if (username == null) {
                return; // Si el usuario cancela, se sale del método
            }
            String password = JOptionPane.showInputDialog("Ingrese su contraseña:");
            if (password == null) {
                return;
            }

            // Verifica si las credenciales coinciden con algún usuario registrado
            if ((username.equals(datos.usuario1) && password.equals(datos.password1))
                    || (username.equals(datos.usuario2) && password.equals(datos.password2))) {

                autenticado = true;
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso. ¡Bienvenido " + username + "!");

                // Instancias de configuración y utilidades para la gestión del sistema
                Configuracion configurar = new Configuracion();
                Serializacion serializador = new Serializacion();
                Cola cola = new Cola();

                // Muestra datos generales de la sucursal
                System.out.println("Sucursal: " + configurar.getSucursal());
                System.out.println("Cantidad de Cajas Normales: " + configurar.getCajasNormales());
                System.out.println("Total de Cajas (con rápidas y preferenciales): " + configurar.getTotalCajas());

                // Crea una lista enlazada con las opciones del menú
                ListaEnlazada menuOpciones = new ListaEnlazada();

                // Agrega las opciones disponibles en el menú
                menuOpciones.agregarOpcion("Agregar Cliente");
                menuOpciones.agregarOpcion("Salir");

                boolean continuar = true;

                while (continuar) {
                    // Obtiene las opciones en formato de texto para mostrarlas al usuario
                    String opcionesTexto = menuOpciones.obtenerOpcionesComoTexto();

                    // Muestra el menú de selección de opciones
                    String seleccionTexto = JOptionPane.showInputDialog(
                            "Seleccione una opción ingresando el número:\n" + opcionesTexto
                    );

                    // Verifica si la entrada es nula (usuario presionó "Cancelar")
                    if (seleccionTexto == null) {
                        JOptionPane.showMessageDialog(null, "Selección inválida.");
                        continue;
                    }

                    int seleccion;
                    try {
                        // Intenta convertir la entrada en un número entero
                        seleccion = Integer.parseInt(seleccionTexto);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Entrada no válida.");
                        continue;
                    }

                    // Obtiene la opción seleccionada según el índice ingresado
                    String opcionSeleccionada = menuOpciones.obtenerOpcionPorIndice(seleccion);

                    // Verifica si la opción seleccionada es válida
                    if (opcionSeleccionada == null) {
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                        continue;
                    }

                    // Ejecuta la acción correspondiente a la opción seleccionada
                    switch (opcionSeleccionada) {
                        case "Agregar Cliente":
                            AgregarCliente.agregarCliente(configurar, cola);
                            break;
                        case "Salir":
                            continuar = false;
                            JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                            break;
                    }
                }

                // Guarda la información de la cola en formato JSON antes de salir
                serializador.serializarCola(cola);

            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos. Intente nuevamente.");
            }
        }
    }

}
