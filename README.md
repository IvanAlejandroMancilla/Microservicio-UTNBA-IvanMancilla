# 🏦 Ecosistema de Microservicios FinTech — TP Final UTN BA

> **Diplomatura en Desarrollo de Software FinTech: IA y Microservicios**  
> **Alumno**: Ivan Alejandro Mancilla  
> **Repositorio Principal del Ecosistema**: [Microservicio-UTNBA-IvanMancilla](https://github.com/IvanAlejandroMancilla/Microservicio-UTNBA-IvanMancilla)

---

## 📌 1. Visión General y Arquitectura

Este repositorio contiene la solución completa al Trabajo Práctico Final de la Diplomatura. El sistema implementa una arquitectura distribuida de microservicios en **Java 21** y **Spring Boot 3.3.2**, estructurada según los patrones de **Spring Cloud** (Config Server, Eureka Server, OpenFeign).

```
                       ┌───────────────────────────┐
                       │  Repo Git Remoto (GitHub) │  (application.yml, customer-service.yml, product-service.yml)
                       └─────────────┬─────────────┘  https://github.com/IvanAlejandroMancilla/tp-config-repo-IVANMANCILLA
                                     │ lee al arrancar
                       ┌─────────────▼─────────────┐
                       │       config-server       │  :8888
                       └─────────────┬─────────────┘  /config-server
                ┌────────────────────┼────────────────────┐
                │ pide config        │                    │ pide config
         ┌──────▼──────┐      ┌──────▼──────┐      ┌──────▼──────┐
         │ eureka-srv  │      │  customer-  │      │  product-   │
         │   :8761     │◄─────┤   service   │      │   service   │
         └─────────────┘      │    :8081    │      │    :8082    │
              ▲  ▲            └──────┬──────┘      └──────▲──────┘
              │  └───────────────────┼────────────────────┘
              │             se registran en Eureka
              │                                           │
              └────────────────── Feign Client ───────────┘
                           (customer llama a product)
```

---

## 🔗 2. Repositorios del Ecosistema

| Componente | Repositorio GitHub | Puerto |
|---|---|:---:|
| 🌐 **Config Repo Remoto** | [tp-config-repo-IVANMANCILLA](https://github.com/IvanAlejandroMancilla/tp-config-repo-IVANMANCILLA) | — |
| ⚙️ **Config Server** | [config-server-Ivan-mancilla](https://github.com/IvanAlejandroMancilla/config-server-Ivan-mancilla) | `8888` |
| 🔍 **Eureka Server** | [eureka-server-IvanMancilla](https://github.com/IvanAlejandroMancilla/eureka-server-IvanMancilla) | `8761` |
| 👤 **Customer Service** (Principal) | [customer-service-Ivan-mancilla](https://github.com/IvanAlejandroMancilla/customer-service-Ivan-mancilla) | `8081` |
| 📦 **Product Service** (Secundario) | [Microservicio-UTNBA-IvanMancilla](https://github.com/IvanAlejandroMancilla/Microservicio-UTNBA-IvanMancilla) | `8082` |

---

## 📂 3. Estructura de Proyectos en este Repositorio

Para facilitar la descarga y prueba rápida del evaluador, este repositorio incluye todos los microservicios organizados en subcarpetas:

```
Microservicio-UTNBA-IvanMancilla/
├── config-server/       # Servidor de configuración centralizada (:8888)
├── eureka-server/       # Servidor de descubrimiento de servicios (:8761)
├── product-service/      # Microservicio de productos y tarjetas (:8082)
├── customer-service/     # Microservicio de clientes con Feign (:8081)
└── README.md             # Guía completa de uso
```

---

## 🚀 4. Guía de Inicio Rápido (Secuencia de Arranque)

Para ejecutar la solución completa, debes iniciar los microservicios en el siguiente **orden obligatorio**:

### Paso 1: Config Server (Puerto 8888)
```bash
cd config-server
./mvnw spring-boot:run
```
*(Espera a que inicie y confirme la conexión con GitHub).*

### Paso 2: Eureka Server (Puerto 8761)
```bash
cd eureka-server
./mvnw spring-boot:run
```
*(Comprueba el dashboard en `http://localhost:8761`).*

### Paso 3: Product Service (Puerto 8082)
```bash
cd product-service
./mvnw spring-boot:run
```

### Paso 4: Customer Service (Puerto 8081)
```bash
cd customer-service
./mvnw spring-boot:run
```

---

## 📡 5. Catálogo de Endpoints REST

### 👤 `customer-service` (Puerto `8081`):
- `POST /clientes` ➔ Registrar cliente nuevo.
- `GET /clientes` ➔ Listar todos los clientes.
- `GET /clientes/{id}` ➔ Obtener datos del cliente por ID.
- ⭐ **`GET /clientes/{id}/productos`** ➔ **Endpoint Principal**: Devuelve el cliente + sus productos consultados a `product-service` vía **OpenFeign** y **Eureka**.
- `PUT /clientes/{id}` ➔ Actualizar datos del cliente.
- `DELETE /clientes/{id}` ➔ Eliminar cliente por ID.

### 📦 `product-service` (Puerto `8082`):
- ⭐ **`GET /productos/cliente/{clienteId}`** ➔ **Endpoint Obligatorio**: Devuelve los productos (cuentas, tarjetas, plazos fijos) vinculados a un cliente.
- `GET /productos` ➔ Listar todos los productos.
- `GET /productos/{id}` ➔ Obtener producto por ID.
- `GET /productos/activos` ➔ Consultar productos activos.
- `POST /productos/agregar` ➔ Registrar un nuevo producto.
- `PUT /productos/actualizar/{id}` ➔ Actualizar un producto.
- `DELETE /productos/eliminar/{id}` ➔ Eliminar un producto.

---

## 🧪 6. Pruebas de Integración End-to-End

1. **Verificación Eureka**: Acceder a `http://localhost:8761` y validar que `PRODUCT-SERVICE` y `CUSTOMER-SERVICE` figuren en estado **UP**.
2. **Prueba Endpoint Principal**:
   ```bash
   curl -X GET http://localhost:8081/clientes/1/productos
   ```
   **Respuesta esperada (JSON combinado)**:
   ```json
   {
     "id": 1,
     "nombre": "Ivan Mancilla",
     "documento": "12345678",
     "email": "ivan@example.com",
     "saldo": 500000.00,
     "productos": [
       {
         "id": 1,
         "nombre": "Caja de Ahorro en Pesos",
         "tipo": "CAJA_AHORRO",
         "montoAsociado": 150000.00
       },
       {
         "id": 2,
         "nombre": "Tarjeta de Crédito VISA Gold",
         "tipo": "TARJETA_CREDITO",
         "montoAsociado": 1000000.00
       },
       {
         "id": 3,
         "nombre": "Plazo Fijo Uva",
         "tipo": "PLAZO_FIJO",
         "montoAsociado": 500000.00
       }
     ]
   }
   ```
