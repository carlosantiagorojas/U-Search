# U-Search
Este es un proyecto creado para la asignatura de Fundamentos de Ingeniería de Software. El objetivo del proyecto es desarrollar una aplicación movil para ayudar a las personas a encontrar objetos perdidos.

Intregrantes:

- Santiago Molina Aranza
- Javier Felipe Santana Díaz
- Carlos Santiago Rojas Calderón

## Conexión a la base de datos MySQL
Para conectarse a la base de datos MySQL, siga los siguientes pasos:

1. Descargue e instale la base de datos MySQL localmente desde la página oficial de MySQL: https://dev.mysql.com/downloads/windows/installer/8.0.html. En este proyecto se usó la versión 8.0.33.0.
   Una vez instalado MySQL, configure su usuario y contraseña. En este proyecto, se usó el usuario "root" y la contraseña "1234", pero puede utilizar las que desee.

## Configuración de MySQL

1. Inicie el Local instance en MySQL y diríjase a "File" -> "Open Model..." y seleccione el archivo "DiagramaBD.mwb" que se encuentra en la carpeta "U-search" del proyecto.

2. Una vez abierto el archivo, seleccione la opción "Forward Engineer...". Esto creará una base de datos llamada "u-search" y una tabla llamada "usuarios", "objetosperdidos" y "notificaciones". Para visualizarlas, es necesario refrescar la conexión a la base de datos.

3. Por último, coloque el mismo nombre del "schema" que se creó (en este caso, "u-search"), la contraseña y el usuario en la clase "ConexionDB.java" en el método "conectar()".

Si todo se ha realizado correctamente, ya puede ejecutar el proyecto.