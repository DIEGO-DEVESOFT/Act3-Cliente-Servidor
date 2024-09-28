import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Clase cliente que se conecta al servidor. Permite al usuario enviar comandos para gestionar tareas y
 * recibir respuestas del servidor.
 */
public class Client {

    /**
     * Método principal que permite al cliente conectarse al servidor y enviar comandos.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9999)) { // Conexión al servidor
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            String command;
            // Bucle para permitir al cliente enviar comandos hasta que decida salir
            while (true) {
                System.out.println("\nComandos disponibles:");
                System.out.println("ADD <tarea> - Agregar una nueva tarea");
                System.out.println("VIEW - Ver todas las tareas");
                System.out.println("REMOVE <tarea> - Eliminar una tarea");
                System.out.println("EXIT - Cerrar la conexión");

                command = scanner.nextLine(); // Lee el comando del usuario
                output.println(command); // Envía el comando al servidor

                String response = input.readLine(); // Recibe la respuesta del servidor
                System.out.println(response);

                if (command.equals("EXIT")) {
                    break; // Sale del bucle si el usuario decide cerrar la conexión
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
