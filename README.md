# 🌱 MINI CURSO – RETOMANDO JAVA Y SPRING BOOT (2025)

## 📌 Contacto

- 🌐 **Portafolio:** [astralzz.io](https://astralzz.github.io)  
- 📩 **Correo:** [edain.cortez@outlook.com](mailto:edain.cortez@outlook.com)  
- 📱 **Telegram:** [t.me/lAstralz](https://t.me/lAstralz)  
- 🔗 **LinkedIn:** [linkedin.com/in/Edain](https://linkedin.com/in/edain-jesus-cortez-ceron-23b26b155)  
- 😺 **GitHub:** [github.com/Astralzz](https://github.com/Astralzz)

---

## 📌 Introducción

Este curso está pensado para desarrolladores con experiencia y que desean usar **Spring Boot** para crear APIs modernas, seguras y conectadas a React u otros clientes.

---

## 🛠 Requisitos previos

* **Java 17+**
* **Maven**
* **IDE**
    - **Recomendado**: IntelliJ IDEA (Community)
    - **Alternativas**: VS Code (con extensiones), Eclipse

---

## 🚀 Instalación y creación del proyecto

### Opción 1: desde terminal

```bash
curl https://start.spring.io/starter.zip \
  -d dependencies=web,data-jpa,mysql,lombok,devtools \
  -d type=maven-project \
  -d javaVersion=17 \
  -d name=api-demo \
  -o api-demo.zip

unzip api-demo.zip && cd api-demo
```

### Opción 2: desde Spring Initializr GUI

1. Visita [https://start.spring.io](https://start.spring.io)
2. Elige:

   * **Maven**
   * **Java 17**
   * Group: `com.edain.api`
   * Artifact: `project_notes_back`
   * Packaging: `jar`
3. Agrega dependencias:

   * Spring Web
   * Spring Data JPA
   * MySQL Driver
   * Lombok
   * Spring Boot DevTools
4. Descarga, descomprime y abre en tu IDE.

---

## 📁 Estructura típica del proyecto

```
src/
└── main/
    ├── java/com/edain/api/
    │   ├── ApiApplication.java       ← Clase principal (como index.php)
    │   ├── controller/               ← Controladores de rutas
    │   ├── model/                    ← Modelos (entidades JPA)
    │   ├── repository/               ← Repositorios (query builders)
    │   ├── service/                  ← Servicios lógicos
    │   └── config/                   ← Configuraciones personalizadas (CORS, seguridad, múltiples DBs, etc.)
    └── resources/
        ├── application.properties    ← Config DB, puertos, logs, etc.
        ├── static/                   ← Archivos estáticos (si sirve frontend)
        └── templates/                ← HTML si se usa Thymeleaf
```

---

## 📦 Cómo instalar y manejar librerías

Spring usa **Maven**, no `composer` ni `npm`.

Las dependencias se gestionan en el archivo `pom.xml`:

```xml
	<dependencies>

		<!-- Núcleo de Spring Boot (logging, configuración, etc.) -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

		<!-- Spring Web: permite crear APIs RESTful y manejar peticiones HTTP -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<!-- Spring Data JPA: facilita el acceso a bases de datos con ORM -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<!-- API de persistencia de Jakarta (interfaz JPA) -->
		<dependency>
			<groupId>jakarta.persistence</groupId>
			<artifactId>jakarta.persistence-api</artifactId>
			<version>3.1.0</version>
		</dependency>

		<!-- Conector MySQL para conectarse a una base de datos MySQL 
			NOTA:
			La versión x.y.z de mysql-connector-j puede no estar disponible en los repositorios públicos de Maven.
			Se recomienda usar una versión estable y ampliamente disponible, como la x.y.z.
			Referencia: https://central.sonatype.com/artifact/com.mysql/mysql-connector-j
		-->
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<version>9.3.0</version>
		</dependency>

		<!-- Lombok: reduce el código repetitivo (getters/setters, etc.) -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<!-- Spring Boot DevTools: reinicio automático durante el desarrollo -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
		</dependency>

		<!-- Librerías para pruebas unitarias/integración con Spring -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

	</dependencies>
```

### Agregar nuevas librerías:

Ejemplo: agregar JWT para seguridad

```xml
<dependency>
  <groupId>io.jsonwebtoken</groupId>
  <artifactId>jjwt</artifactId>
  <version>0.9.1</version>
</dependency>
```

Luego ejecutas:

```bash
./mvnw install    # instala dependencias
./mvnw spring-boot:run  # corre el proyecto
```

---

## 🧠 Modelo + Repositorio + Servicio + Controlador

### 🧩 Modelo (entidad)

```java
package com.astralz.project_notes_back.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entidad Note que representa una nota en la base de datos.
 *
 * @Entity: Declara que esta clase es una entidad JPA.
 * @Table: Mapea esta clase a la tabla 'notes' en la base de datos.
 * 
 * Lombok:
 * @Data: Genera getters, setters, toString, equals y hashCode.
 * @NoArgsConstructor: Genera constructor vacío.
 * @AllArgsConstructor: Genera constructor con todos los campos.
 */
@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    /**
     * Identificador único de la nota.
     * Se genera automáticamente.
     * @Id: Indica que este campo es la clave primaria de la entidad.
     * @GeneratedValue(strategy = GenerationType.IDENTITY): Genera el valor automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título de la nota (no nulo, máx. 100 caracteres).
     * @Column(nullable = false, length = 100): Indica que el campo no puede ser nulo y tiene un máximo de 100 caracteres.
     */
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * Contenido de la nota.
     * Tipo texto largo.
     * @Column(columnDefinition = "TEXT"): Indica que el campo es de tipo texto largo.
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * Fecha de creación de la nota (solo fecha, sin hora).
     * @CreationTimestamp: Genera la fecha de creación automáticamente.
     * @Column(updatable = false): Evita que se actualice accidentalmente.
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate creationDate;

}

```

### 🧩 Repositorio

```java
package com.astralz.project_notes_back.repositories;

import com.astralz.project_notes_back.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que extiende JpaRepository para manejar la persistencia de datos de las notas.
 * 
 * @Repository: Indica que esta interfaz es un repositorio de Spring Data JPA.
 * @JpaRepository: Extiende JpaRepository para manejar la persistencia de datos de las notas.
 * @Note: Entidad que representa una nota.
 * @Long: Tipo de dato del identificador de la nota.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    // JpaRepository ya trae métodos para CRUD y paginación
}
```

### 🧩 Servicio

#### Servicio [interface]

```java
package com.astralz.project_notes_back.services;

import com.astralz.project_notes_back.models.Note;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los métodos para manejar las notas.
 * 
 * @NoteService: Interfaz que define los métodos para manejar las notas.
 * @Note: Entidad que representa una nota.
 * @Long: Tipo de dato del identificador de la nota.
 */
public interface NoteService {
    /**
     * Obtiene todas las notas.
     * @return Lista de notas.
     */
    List<Note> findAll();

    /**
     * Obtiene una nota por su ID.
     * @param id ID de la nota.
     * @return Nota encontrada.
     */
    Optional<Note> findById(Long id);
    
    /**
     * Guarda una nota.
     * @param note Nota a guardar.
     * @return Nota guardada.
     */
    Note save(Note note);

    /**
     * Actualiza una nota.
     * @param id ID de la nota.
     * @param updatedNote Nota actualizada.
     * @return Nota actualizada.
     */
    Optional<Note> update(Long id, Note updatedNote);

    /**
     * Elimina una nota por su ID.
     * @param id ID de la nota.
     * @return true si la nota fue eliminada, false en caso contrario.
     */
    boolean deleteById(Long id);
}
```

#### Servicio Impl [Implement]

```java
package com.astralz.project_notes_back.services.impl;

import com.astralz.project_notes_back.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.astralz.project_notes_back.repositories.NoteRepository;
import com.astralz.project_notes_back.services.NoteService;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de notas.
 * 
 * @Service: Indica que esta clase es un servicio de Spring.
 * @NoteRepository: Repositorio que maneja la persistencia de datos para notas.
 * @Autowired: Inyección de dependencia del repositorio NoteRepository.
 * @Note: Entidad que representa una nota.
 * @Long: Tipo de dato del identificador de la nota.
 */
@Service
public class NoteServiceImpl implements NoteService {

    // Repositorio que maneja la persistencia de datos para notas
    private final NoteRepository noteRepository;

    /**
     * Constructor con inyección de dependencia del repositorio NoteRepository.
     * @Autowired: Inyección de dependencia del repositorio NoteRepository.
     * @param noteRepository Repositorio que maneja la persistencia de datos para notas.
     */
    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Obtiene todas las notas.
     * @return Lista de notas.
     */
    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    /**
     * Obtiene una nota por su ID.
     * @param id ID de la nota.
     * @return Nota encontrada.
     */
    @Override
    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    /**
     * Guarda una nota.
     * @param note Nota a guardar.
     * @return Nota guardada.
     */
    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    /**
     * Actualiza una nota.
     * @param id ID de la nota.
     * @param updatedNote Nota actualizada.
     * @return Nota actualizada.
     */
    @Override
    public Optional<Note> update(Long id, Note updatedNote) {
        return noteRepository.findById(id).map(note -> {
            note.setTitle(updatedNote.getTitle());
            note.setContent(updatedNote.getContent());
            note.setCreationDate(updatedNote.getCreationDate());
            return noteRepository.save(note);
        });
    }

    /**
     * Elimina una nota por su ID.
     * @param id ID de la nota.
     * @return true si la nota fue eliminada, false en caso contrario.
     */
    @Override
    public boolean deleteById(Long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
```


### 🧩 Controlador REST

```java
package com.astralz.project_notes_back.controllers;

import com.astralz.project_notes_back.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.astralz.project_notes_back.services.NoteService;

import java.util.List;

/**
 * Controlador REST para manejar operaciones sobre notas.
 * Permite crear, obtener, actualizar y eliminar notas.
 * 
 * @CrossOrigin: Permite que el controlador se comunique con el frontend [* = todos los origenes].
 * @RestController: Indica que esta clase es un controlador REST.
 * @RequestMapping: Mapea la URL /api/notes para este controlador.
 * @NoteService: Servicio que maneja la lógica de negocio para notas.
 * @Note: Entidad que representa una nota.
 * @Long: Tipo de dato del identificador de la nota.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notes")  // Ruta base para este controlador
public class NoteController {

    // Servicio que maneja la lógica de negocio para notas
    private final NoteService noteService;

    /**
     * Constructor con inyección de dependencia del servicio NoteService.
     * @param noteService servicio que maneja la lógica de negocio para notas
     */
    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * Obtener todas las notas.
     * @GetMapping: Mapea la URL /api/notes para este método.
     * 
     * GET /api/notes
     * @return lista de notas en formato JSON
     */
    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.findAll();
    }

    /**
     * Obtener una nota por su ID.
     * 
     * GET /api/notes/{id}
     * @param id identificador de la nota
     * @return nota encontrada o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crear una nueva nota.
     * 
     * POST /api/notes
     * @param note objeto Note con los datos de la nueva nota
     * @return la nota creada con código 201 Created
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note createdNote = noteService.save(note);
        return ResponseEntity.status(201).body(createdNote);
    }

    /**
     * Actualizar una nota existente.
     * 
     * PUT /api/notes/{id}
     * @param id id de la nota a actualizar
     * @param note datos actualizados de la nota
     * @return nota actualizada o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note) {
        return noteService.update(id, note)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Eliminar una nota por su ID.
     * 
     * DELETE /api/notes/{id}
     * @param id id de la nota a eliminar
     * @return código 204 No Content si fue eliminada o 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        if (noteService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
```

---

## 🌐 Conexión a base de datos

### `application.properties`

```properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/project_notes_back?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Opciones de `ddl-auto`:

* `none`
* `validate`
* `update` (la más común en dev)
* `create-drop`

---

## ⚙️ Comandos Maven útiles

| Comando                  | Descripción                                   |
| ------------------------ | --------------------------------------------- |
| `./mvnw install`         | Instala dependencias y compila                |
| `./mvnw clean`           | Limpia caché (como `php artisan cache:clear`) |
| `./mvnw spring-boot:run` | Inicia servidor                               |
| `./mvnw dependency:tree` | Muestra dependencias                          |
| `./mvnw test`            | Corre tests                                   |

---

## 🔥 Utilidades prácticas

* `@RestController`: para APIs
* `@Service`: lógica de negocio
* `@Repository`: repositorios JPA
* `@RequestMapping("/ruta")`: define endpoint
* `@GetMapping`, `@PostMapping`, etc.: métodos HTTP
* `@RequestBody`: input de JSON
* `@Autowired`: inyección automática

---

## 🛡️ Qué sigue después

* [ ] Agregar seguridad con JWT (`spring-boot-starter-security`)
* [ ] Validación con `@Valid`, `@NotBlank`, etc.
* [ ] Respuesta estándar tipo API Resource
* [ ] Relaciones `@OneToMany`, `@ManyToOne`
* [ ] Separar configuración (CORS, seguridad, Swagger)

---

## 📚 Documentación y enlaces útiles

* 🌐 [Spring Boot Docs](https://docs.spring.io/spring-boot/docs/current/reference/html/)
* 📘 [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* 🔐 [Spring Security](https://spring.io/projects/spring-security)
* 🛠️ [Spring Initializr](https://start.spring.io)
* 🎓 [Baeldung Tutorials](https://www.baeldung.com/)

