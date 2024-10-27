# API REST PARA PANADERIAS CON SPRINGBOOT-JAVA 🌿☕

**ESTE MODELO AUN ESTA EN DESARROLLO PERO YA SE PUEDEN PROBAR LOS METODOS MÁS COMUNES DE UNA API CON DATA RELACIONAL.**

> [!NOTE]
> La base de datos tiene NO TIENE datos embebidos...

> [!NOTE]
> PROBADO EN SWAGGER Y POSTMAN

> [!IMPORTANT]
> LOS OBJETOS DEBEN MANDARSE A POSTMAN COMO SE DESCRIBE EN LA DOCUMENTACION DE SWAGGER. De lo contrario va a causar malas relaciones en la base de datos y los datos podrian no eliminarse o generar conflictos.

## Levantar el proyecto

Clonar el repositorio en una carpeta y abrir con el editor de codigo de preferencia. (Se sugiere Visual Studio o IntelliJ)

> [!IMPORTANT]
>
> Debes tener instalada la version de Java compatible con tu sistema operativo.

Levantar el proyecto y para revisar la documentacion y empezar las pruebas, visitar este enlace en el navegador:
[SwaggerUi](http://localhost:8080/swagger-ui/index.html#/ "Abrir interfaz de Swagger")

Para revisar las tablas con los elementos creados visitar el enlace de la base de datos embebida.

[H2-Console](http://localhost:8080/h2-console/ "Consola para base de datos embebida")

Luego en JDBC URL agregar esta direccion:

jdbc:h2:file:./data/testDB
