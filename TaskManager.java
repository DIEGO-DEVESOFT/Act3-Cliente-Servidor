import java.util.ArrayList;
import java.util.List;

/**
 * Clase responsable de la gestión de tareas. Aplica el Principio de Responsabilidad Única (SRP),
 * separando la lógica de tareas del resto de la aplicación.
 */
public class TaskManager {
    private List<String> tasks;

    /**
     * Constructor que inicializa la lista de tareas.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Método que agrega una nueva tarea a la lista.
     *
     * @param task La tarea a agregar.
     * @return Un mensaje confirmando que la tarea ha sido agregada.
     */
    public String addTask(String task) {
        tasks.add(task);
        return "Tarea '" + task + "' agregada.";
    }

    /**
     * Método que devuelve todas las tareas en formato de cadena.
     *
     * @return Una lista de todas las tareas o un mensaje si no hay tareas.
     */
    public String viewTasks() {
        if (tasks.isEmpty()) {
            return "No hay tareas.";
        }
        StringBuilder taskList = new StringBuilder();
        for (String task : tasks) {
            taskList.append(task).append("\n");
        }
        return taskList.toString();
    }

    /**
     * Método que elimina una tarea de la lista.
     *
     * @param task La tarea a eliminar.
     * @return Un mensaje confirmando la eliminación o un error si la tarea no existe.
     */
    public String removeTask(String task) {
        if (tasks.contains(task)) {
            tasks.remove(task);
            return "Tarea '" + task + "' eliminada.";
        } else {
            return "Tarea '" + task + "' no encontrada.";
        }
    }
}
