# H1 API REST PARA PANADERIAS

**ESTE MODELO AUN ESTA EN DESARROLLO PERO YA SE PUEDEN PROBAR LOS METODOS MÁS COMUNES DE UNA API CON DATA RELACIONAL.**

 >[!NOTE]
 >La base de datos tiene datos embebidos para empezar...

>[!NOTE]
>PROBADO EN POSTMAN

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
* `{ "nombre": "Pan Integral", "precio": 150.0, "stock": 50, "categoria": "panes", "panaderiaId": 1 }`

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
* `{ "nombre": "Nombre del Cliente", "correo": "correo@ejemplo.com", "telefono": "123456789", "edad": 30, "panaderiaId": 1 }`

### Actualizar un cliente

* **Método:** `PUT`
* **Endpoint:** `/api/clientes/{id}`
* **Cuerpo de la Solicitud (JSON):**
* `{ "nombre": "Nuevo Nombre", "correo": "nuevo_correo@ejemplo.com", "telefono": "987654321", "edad": 35, "panaderiaId": 2 }`
* **Descripción:** Actualiza la información de un cliente existente. Cambia `{id}` por el ID del cliente que deseas actualizar.

### Eliminar un cliente

* **Método:** `DELETE`
* **Endpoint:** `/api/clientes/{id}`
* **Descripción:** Elimina un cliente existente. Cambia `{id}` por el ID del cliente que deseas eliminar.
