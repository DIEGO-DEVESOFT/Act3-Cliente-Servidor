import java.io.*;
import java.net.Socket;

/**
 * Clase responsable de manejar las conexiones con los clientes. Aplica el Principio de Responsabilidad Única (SRP),
 * separando la lógica de conexión de la gestión de tareas.
 */
public class ConnectionHandler implements Runnable {
    private Socket clientSocket;
    private TaskManager taskManager;

    /**
     * Constructor que recibe el socket del cliente y el gestor de tareas.
     *
     * @param clientSocket El socket del cliente.
     * @param taskManager  El gestor de tareas utilizado para administrar las tareas.
     */
    public ConnectionHandler(Socket clientSocket, TaskManager taskManager) {
        this.clientSocket = clientSocket;
        this.taskManager = taskManager;
    }

    /**
     * Método que maneja la comunicación con el cliente.
     * Se ejecuta en un hilo separado para soportar múltiples conexiones simultáneas.
     */
    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            String request;
            // Bucle para escuchar las solicitudes del cliente
            while ((request = input.readLine()) != null) {
                String response;
                // Comando para agregar una tarea
                if (request.startsWith("ADD")) {
                    String task = request.substring(4); // Extrae el nombre de la tarea
                    response = taskManager.addTask(task);
                }
                // Comando para visualizar todas las tareas
                else if (request.equals("VIEW")) {
                    response = taskManager.viewTasks();
                }
                // Comando para eliminar una tarea
                else if (request.startsWith("REMOVE")) {
                    String task = request.substring(7); // Extrae el nombre de la tarea
                    response = taskManager.removeTask(task);
                }
                // Comando para salir de la conexión
                else if (request.equals("EXIT")) {
                    response = "Conexión cerrada.";
                    output.println(response);
                    break; // Termina el ciclo y cierra la conexión
                } else {
                    response = "Comando no válido.";
                }
                // Envía la respuesta al cliente
                output.println(response);
            }

            clientSocket.close(); // Cierra el socket al final de la conexión
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
