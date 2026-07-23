# 🐾 Adopción de Perritos (Full Stack Project)

Plataforma web para gestionar la adopción de perros rescatados. Desarrollada con arquitectura multicapa, principios SOLID, y POO estricto.

## 🛠 Tecnologías Utilizadas
- **Backend:** Java 17+, Spring Boot, Spring Data JPA, Hibernate, Maven.
- **Frontend:** React 18, Vite, React Router, Axios, Bootstrap 5.
- **Base de Datos:** Microsoft SQL Server (JDBC).

## 📋 Requisitos
- JDK 17 o superior.
- Node.js 20+ y npm.
- SQL Server y SQL Server Management Studio (SSMS).

## 🚀 Instalación y Configuración

### 1. Base de datos
1. Abre SSMS y conéctate a tu instancia de SQL Server.
2. Abre el archivo `database/adopcion_perritos.sql` y ejecútalo para crear la DB y las tablas.

### 2. Backend (Spring Boot)
1. Abre la carpeta `backend/` en tu IDE (IntelliJ, Eclipse o VS Code).
2. Verifica en `src/main/resources/application.properties` que tu usuario de SQL Server, contraseña y puerto sean correctos.
3. Ejecuta la clase `AdopcionApplication.java`.
4. La API REST estará corriendo en `http://localhost:8080`.

### 3. Frontend (React + Vite)
1. Abre una terminal en la carpeta `frontend/`.
2. Ejecuta `npm install` para descargar las dependencias.
3. Ejecuta `npm run dev` para levantar el entorno de desarrollo.
4. Accede a la aplicación en `http://localhost:5173`.

## 📌 Endpoints Disponibles
- `GET /api/perros`: Listar todos los perros.
- `GET /api/perros/{id}`: Obtener un perro por ID.
- `POST /api/perros`: Crear un nuevo registro.
- `PUT /api/perros/{id}`: Modificar un registro.
- `DELETE /api/perros/{id}`: Eliminar un perro.
- `PATCH /api/perros/{id}/adoptar`: Cambiar estado a "ADOPTADO".
- `GET /api/perros/buscar/nombre?valor={nombre}`: Buscar por nombre.

## 🏗 Arquitectura y Código Limpio
- **Capa Controller:** Manejo de peticiones HTTP, validaciones DTO.
- **Capa Service:** Lógica de negocio (Interfaces e Implementación).
- **Capa Repository:** Conexión a Base de Datos (JPA).
- **Manejo de Excepciones:** Clase `GlobalExceptionHandler` intercepta errores globalmente asegurando respuestas JSON limpias.