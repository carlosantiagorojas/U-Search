# U-Search
This is a project created for the Software Engineering Fundamentals course. The objective of the project is to develop a mobile application to help people find lost items.

Team Members:

- Santiago Molina Aranza
- Javier Felipe Santana Díaz
- Carlos Santiago Rojas Calderón

## Connecting to MySQL Database
To connect to the MySQL database, follow these steps:

1. Download and install MySQL database locally from the official MySQL page: https://dev.mysql.com/downloads/windows/installer/8.0.html. In this project, version 8.0.33.0 was used.
   Once MySQL is installed, configure your username and password. In this project, the username "root" and password "1234" were used, but you can use any you prefer.

## MySQL Configuration

1. Start the Local instance in MySQL and go to "File" -> "Open Model..." and select the file "DiagramaBD.mwb" located in the "U-search" folder of the project.

2. Once the file is open, select the "Forward Engineer..." option. This will create a database named "u-search" and tables named "usuarios" (users), "objetosperdidos" (lost objects), and "notificaciones" (notifications). To view them, it's necessary to refresh the connection to the database.

3. Finally, put the same name of the "schema" that was created (in this case, "u-search"), the password, and the username in the "ConexionDB.java" class in the "conectar()" method.

If everything has been done correctly, you can now run the project.

## Running the Project
To access the project's functions, you need to register with the following credentials:

### User:

- Email: ejemplo@javeriana.edu.co

- Password: ejemplo

The email must end with @javeriana.edu.co to be able to log in.

### Lost and Found Personnel:

- Email: adminejemplo@javeriana.edu.co

- Password: ejemplo@javeriana.edu.co

You must include "admin" at the beginning to access the application as lost and found personnel.

