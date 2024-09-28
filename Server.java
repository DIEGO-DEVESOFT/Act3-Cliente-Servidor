import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase principal que inicializa el servidor y acepta conexiones de múltiples clientes.
 * Aplica el Principio de Inversión de Dependencias (DIP), ya que inyecta el gestor de tareas en el manejador de conexiones.
 */
public class Server {

    /**
     * Método principal que arranca el servidor.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(); // Inicializa el gestor de tareas

        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            System.out.println("Servidor escuchando en el puerto 9999...");

            // Bucle infinito para aceptar múltiples conexionesa
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Espera una conexión
                System.out.println("Conexión establecida con " + clientSocket.getInetAddress());

                // Crea un nuevo manejador de conexión para cada cliente y lo ejecuta en un nuevo hilo
                ConnectionHandler handler = new ConnectionHandler(clientSocket, taskManager);
                Thread thread = new Thread(handler);
                thread.start(); // Inicia el hilo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
