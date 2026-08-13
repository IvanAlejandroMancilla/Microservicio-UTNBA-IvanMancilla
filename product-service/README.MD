# product-service

Microservicio de gestión de productos financieros (cuentas, tarjetas, plazos fijos) construido con **Spring Boot 3** y **Java 21**. Es el servicio secundario/de soporte del ecosistema: se registra en **Eureka**, obtiene su configuración desde un **Config Server**, y es consumido por `customer-service` (vía **OpenFeign**) para enriquecer los datos de cada cliente con sus productos asociados.

## ✨ Funcionalidades

- CRUD completo de productos (crear, listar, obtener por ID, actualizar, eliminar).
- Consulta de productos por cliente (`clienteId`) — endpoint clave, consumido por `customer-service`.
- Consulta de productos activos.
- Manejo centralizado de errores con respuestas JSON consistentes (`404`, `500`).
- Datos de prueba precargados al iniciar la aplicación (cuentas, tarjeta y plazo fijo asociados a dos clientes de ejemplo).

## 🧱 Stack técnico

| Tecnología | Uso |
|---|---|
| Java 21 | Lenguaje |
| Spring Boot 3.3.2 | Framework base |
| Spring Web (MVC) | API REST |
| Spring Data JPA | Persistencia |
| H2 Database | Base de datos en memoria |
| Spring Cloud Netflix Eureka Client | Descubrimiento de servicios (se registra en `eureka-server`) |
| Spring Cloud Config | Configuración centralizada (vía `config-server`) |
| Maven | Gestión de dependencias y build |

## 📁 Estructura del proyecto

```
src/main/java/com/ivanmancilla/productservice
├── ProductServiceApplication.java   # Clase principal (@SpringBootApplication)
├── config/
│   └── ProductDataInitializer.java  # Carga de datos iniciales (productos de prueba)
├── controller/
│   └── ProductController.java       # Endpoints REST (/productos)
├── dto/
│   ├── ProductRequestDTO.java
│   └── ProductResponseDTO.java
├── entity/
│   └── Product.java                 # Entidad JPA (tabla products)
├── exception/
│   ├── ProductNotFoundException.java
│   ├── ErrorResponse.java
│   └── GlobalExceptionHandler.java  # Manejo global de excepciones
├── mapper/
│   ├── ProductMapper.java
│   └── GenericMapper.java
├── repository/
│   └── ProductRepository.java
└── service/
    └── ProductService.java
```

## ⚙️ Requisitos previos

- JDK 21+
- Maven 3.9+
- (Recomendado, para funcionamiento completo del ecosistema)
  - Un **Config Server** corriendo en `http://localhost:8888`
  - Un **Eureka Server** para el registro del servicio

> El servicio necesita resolver `configserver:http://localhost:8888` al arrancar, por lo que fallará al iniciar si el Config Server no está disponible.

## 🚀 Cómo ejecutar

```bash
cd product-service
./mvnw spring-boot:run
```

También puede empaquetarse y ejecutarse como JAR:

```bash
mvn clean package
java -jar target/product-service-0.0.1-SNAPSHOT.jar
```

La configuración de la aplicación (`application.yaml`) importa propiedades adicionales desde el Config Server:

```yaml
spring:
  application:
    name: product-service
  config:
    import: "configserver:http://localhost:8888"
```

## 📡 Endpoints

Base path: `/productos`

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/productos` | Lista todos los productos |
| `GET` | `/productos/{id}` | Obtiene un producto por ID |
| `GET` | `/productos/cliente/{clienteId}` | ⭐ Lista los productos de un cliente — consumido por `customer-service` vía Feign |
| `GET` | `/productos/activos` | Lista los productos con `activo = true` |
| `POST` | `/productos/agregar` | Crea un nuevo producto |
| `PUT` | `/productos/actualizar/{id}` | Actualiza un producto existente |
| `DELETE` | `/productos/eliminar/{id}` | Elimina un producto |

### Ejemplo — Crear producto

```bash
curl -X POST http://localhost:8082/productos/agregar \
  -H "Content-Type: application/json" \
  -d '{
    "clienteId": 1,
    "tipo": "CAJA_AHORRO",
    "nombre": "Caja de Ahorro en Pesos",
    "descripcion": "Cuenta a la vista en pesos argentinos",
    "montoAsociado": 150000.00,
    "activo": true,
    "fechaInicio": "2024-01-10"
  }'
```

### Ejemplo — Obtener productos de un cliente

```bash
curl http://localhost:8082/productos/cliente/1
```

## 🧩 Modelo de datos

**Product (entidad, tabla `products`)**

| Campo | Tipo | Restricciones |
|---|---|---|
| `id` | Long | PK, autogenerado |
| `clienteId` | Long | Requerido — vincula el producto con un cliente de `customer-service` |
| `tipo` | String | Requerido (ej: `CAJA_AHORRO`, `TARJETA_CREDITO`, `PLAZO_FIJO`) |
| `nombre` | String | Requerido |
| `descripcion` | String | — |
| `montoAsociado` | BigDecimal | — |
| `tasaInteres` | BigDecimal | — (aplica principalmente a plazos fijos) |
| `activo` | boolean | Requerido |
| `fechaInicio` | LocalDate | — |
| `fechaVencimiento` | LocalDate | — |

`ProductRequestDTO` y `ProductResponseDTO` replican estos mismos campos (el segundo agrega el `id`).

## ⚠️ Manejo de errores

El `GlobalExceptionHandler` centraliza las respuestas de error con un formato consistente (`timestamp`, `status`, `error`, `message`, `path`):

| Excepción | Código HTTP |
|---|---|
| `ProductNotFoundException` | 404 Not Found |
| Excepción genérica | 500 Internal Server Error |

## 🔗 Relación con el resto del ecosistema

`customer-service` llama a `GET /productos/cliente/{clienteId}` a través de un cliente Feign para resolver el endpoint `GET /clientes/{id}/productos`. Ver el diagrama y la guía de arranque completa en el [README principal](../README.md) del repositorio.

## 👤 Autor

**Ivan Alejandro Mancilla**
