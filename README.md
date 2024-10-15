# API REST PARA PANADERIAS CON SPRINGBOOT-JAVA 🌿☕

**ESTE MODELO AUN ESTA EN DESARROLLO PERO YA SE PUEDEN PROBAR LOS METODOS MÁS COMUNES DE UNA API CON DATA RELACIONAL.**

> [!NOTE]
> La base de datos tiene NO TIENE datos embebidos...

> [!NOTE]
> PROBADO EN POSTMAN

> [!IMPORTANT]
> LOS OBJETOS DEBEN MANDARSE A POSTMAN COMO SE DESCRIBE EN EL README. De lo contrario va a causar malas relaciones en la base de datos y los datos podrian no eliminarse.

## Clase Panaderia

### Obtener panaderias:

* **Metodo** : GET
* **URL** : /api/panaderias
* **Descripción** : Retorna todas las panaderías disponibles en la base de datos.
* **Ejemplo de solicitud en Postman** :
* **GET** `http://localhost:8080/api/panaderias`

### Obtener panaderias por ID:

* **Metodo** : GET
* **URL** : /api/panaderias/{ID}
* **Descripción** : Retorna la panaderia por ID
* **Ejemplo de solicitud en Postman** : Se debe reemplazar las llaves y el ID por el ID existente
* **GET** `http://localhost:8080/api/panaderias/1`

### Crear panaderias

* **Método** : POST
* **URL** : `/api/panaderias/createPanaderia`
* **Descripción** : Crea una nueva panadería en la base de datos.
* **Cuerpo de la solicitud (JSON)** :

  `{ "nombre": "Panadería Nueva", "direccion": "Calle Falsa 123", "telefono": "123456789" } `
* **Ejemplo de solicitud en Postman** :* **POST** `http://localhost:8080/api/panaderias/createPanaderia`
* **Body** : Seleccionar **raw** y luego **JSON** para ingresar el cuerpo de la solicitud.

### Actualizar panaderia existente

* **Método** : PUT
* **URL** : `/api/panaderias/{id}`
* **Descripción** : Actualiza una panadería existente con los nuevos datos proporcionados.
* **Cuerpo de la solicitud (JSON)**
  `{ "nombre": "Panadería Actualizada", "direccion": "Calle Actualizada 456", "telefono": "987654321" }`
* **Ejemplo de solicitud en Postman** :
* **PUT** `http://localhost:8080/api/panaderias/1` (cambiar `1` por el ID de la panadería a actualizar)
* **Body** : Seleccionar **raw** y luego **JSON** para ingresar el cuerpo de la solicitud.

### Eliminar una panaderia

* **Método** : DELETE
* **URL** : `/api/panaderias/{id}`
* **Descripción** : Elimina una panadería de la base de datos.
* **Ejemplo de solicitud en Postman** :
* **DELETE** `http://localhost:8080/api/panaderias/1` (cambiar numero por el ID de la panadería a eliminar)

## Clase producto

### Obtener productos

* **Metodo** : GET
* **URL** : /api/productos
* **Descripción** : Retorna todos los productos de la base de datos
* **Ejemplo de solicitud en Postman** :
* **GET** `http://localhost:8080/api/productos`

### Obtener un producto por su ID

* **Método** : GET
* **URL** : `/api/productos/{id}`
* **Descripción** : Devuelve el producto correspondiente al ID proporcionado. Retorna un **404 Not Found** si no existe.
* **Ejemplo de solicitud en Postman** :
* **GET** `http://localhost:8080/api/productos/1`

### Crear productos nuevos

* **Método** : POST
* **URL** : `/api/productos/createProduct`
* **Descripción** : Crea un nuevo producto con la información proporcionada en el cuerpo de la solicitud. Retorna un mensaje de éxito y el producto creado.
* **Cuerpo de la solicitud:**
* `{ "nombre": "Pan Integral", "precio": 3.50, "stock": 100, "categoria": "Panadería", "panaderia": { "id": 1 } }`

### Actualizar un producto por ID

* **Método** : PUT
* **URL** : `/api/productos/{id}`
* **Descripción** : Actualiza los detalles de un producto existente usando el ID proporcionado y un objeto `ProductoDTO` en el cuerpo de la solicitud. Retorna el producto actualizado o un **404 Not Found** si no existe.

### Eliminar un producto

* **Método** : `DELETE`
* **URL** : `/api/productos/{id}`
* **Descripción** : Elimina el producto correspondiente al ID proporcionado. Retorna un mensaje de éxito o un mensaje de error si no se pudo eliminar.
* **Ejemplo de solicitud en Postman** :
* **DELETE** `http://localhost:8080/api/productos/1` (cambiar numero por el ID de la panadería a eliminar)

### Modificar el stock de un producto

* **Método** : `PATCH`
* **URL** : `/api/productos/{id}/stock`
* **Descripción** : Modifica el stock del producto correspondiente al ID proporcionado, usando un parámetro de consulta `nuevoStock`. Retorna **204 No Content** si se actualiza correctamente o **404 Not Found** si el producto no existe.
* **Ejemplo de solicitud en Postman** :
* **PATCH** `/api/productos/1/stock?nuevoStock=25`

## Clase Cliente

### Obtener todos los clientes

* **Método:** GET
* **Endpoint:** `/api/clientes`
* **Descripción:** Obtiene la lista de todos los clientes

### Crear un cliente

* **Método:** `POST`
* **Endpoint:** `/api/clientes/createClient`
* **Cuerpo de la Solicitud (JSON):**
* **Cliente sin asociar:**
* `{ "nombre": "Camilo Medina", "correo": "camilo.medina@example.com", "telefono": "123456789", "edad": 25 }`
* **Cliente asociado a una o mas panaderias:**
* `{ "nombre": "Camilo Medina", "correo": "camilo.medina@example.com", "telefono": "123456789", "edad": 25, "clientePanaderias": [ { "panaderia": { "id": 1 } }, { "panaderia": { "id": 2 } } ] } `

### Actualizar un cliente

* **Método:** `PUT`
* **Endpoint:** `/api/clientes/{id}`
* **Cuerpos de la Solicitud (JSON):**
* **Put a cliente sencillo:**
* `{ "id": 1, "nombre": "Camilo Medina", "correo": "nuevo.email@example.com", "telefono": "987654321", "edad": 26 }`
* **Put a cliente actualizando panaderias:**
* `{ "id": 1, "nombre": "Camilo Medina", "correo": "nuevo.email@example.com", "telefono": "987654321", "edad": 26, "clientePanaderias": [ { "id": 1,   "panaderia": { "id": 2 } }, { "id": 2, "panaderia": { "id": 3 } } ] } `
* **Detalles del JSON**
* id: Es necesario especificar el id del cliente para que el servidor sepa a qué cliente se refiere la actualización.
* clientePanaderias: Esta es la lista de asociaciones actualizadas del cliente con las panaderías. Aquí se pueden modificar las panaderías existentes o agregar nuevas relaciones.

  * Cada objeto dentro de `clientePanaderias` debe tener su propio `id` (el `id` de la relación existente en la tabla de unión `ClientePanaderia`), lo cual es necesario para identificar qué relación modificar.
  * El `id` de la `panaderia` indica a qué panadería se está asociando el cliente.
* **Consideraciones:**
* **Actualización de las relaciones** : Si no envías el campo `clientePanaderias`, no se actualizarán las relaciones con las panaderías. Si lo incluyes, las relaciones existentes podrían ser reemplazadas por las nuevas que envíes en el JSON.
* **Relaciones en cascada** : Dado que tienes `cascade = CascadeType.ALL` en las relaciones de `ClientePanaderia`, cualquier cambio que envíes en las asociaciones de panaderías se aplicará en cascada.

### Eliminar un cliente

* **Método:** `DELETE`
* **Endpoint:** `/api/clientes/{id}`
* **Descripción:** Elimina un cliente existente. Cambia `{id}` por el ID del cliente que deseas eliminar.
