// ------------------ ACTIVIDAD 3 CLIENTE - SERVIDOR --------------------------------//

EXPLICACIÓN DEL CÓDIGO 

Server: 

Se usa el Principio de Inversión de Dependencias (DIP) al inyectar la instancia de TaskManager en ConnectionHandler, lo que permite cambiar la implementación del gestor de tareas sin afectar la lógica del servidor. 

Al iniciar, se configura para escuchar en el puerto 9999. Cada vez que un cliente se conecta, se crea un nuevo hilo para manejar la comunicación. 

El servidor permite que el cliente: 

Agregar una tarea con el comando ADD <tarea>. 

Ver todas las tareas con el comando VIEW. 

Eliminar una tarea con el comando REMOVE <tarea>. 

Cerrar la conexión con EXIT. 

Client: 

El cliente se conecta al servidor utilizando la dirección localhost y el puerto 9999. 

El usuario puede interactuar con el servidor escribiendo comandos en la consola. 

Cada comando se envía al servidor, y luego el cliente recibe una respuesta que se muestra en la consola. 

 

Task mánager: 

Esta clase se encargará de la lógica relacionada con la gestión de las tareas, aplicando el Principio de Responsabilidad Única (SRP). 

 

Conection handler: 

Esta clase gestiona la comunicación con el cliente, respetando el Principio de Responsabilidad Única (SRP) y separando la lógica de tareas de la de las conexiones. 

 

APLICACIÓN DE LOS PRINCIPIOS SOLID EN JAVA: 

 

S: Principio de Responsabilidad Única (SRP): La gestión de tareas está separada de la gestión de conexiones, siguiendo este principio. 

 

O: Principio de Abierto/Cerrado (OCP): El código está diseñado para que puedas extender TaskManager para manejar tareas de otras formas (por ejemplo, almacenar en base de datos), sin modificar el código existente. 

 

L: Principio de Sustitución de Liskov (LSP): Si quisiéramos extender TaskManager, podríamos hacerlo respetando la interfaz definida por la clase base. 

 

I: Principio de Segregación de Interfaces (ISP): Aunque no se define explícitamente una interfaz en este ejemplo, se puede aplicar dividiendo responsabilidades entre clases como TaskManager y ConnectionHandler. 

 

D: Principio de Inversión de Dependencias (DIP): El servidor depende de la abstracción TaskManager y no de una implementación específica. 
