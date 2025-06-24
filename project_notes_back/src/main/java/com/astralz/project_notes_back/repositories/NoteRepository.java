package com.astralz.project_notes_back.repositories;

import com.astralz.project_notes_back.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 📦 NoteRepository
 * 
 * @Repository: Indica que esta interfaz es un repositorio de Spring Data JPA.
 * @JpaRepository: Extiende JpaRepository para manejar la persistencia de datos de las notas.
 * @Note: Entidad que representa una nota.
 * @Long: Tipo de dato del identificador de la nota.
 * 
 * Repositorio de acceso a datos (DAO) para la entidad {@link Note}.
 * Esta interfaz extiende {@link JpaRepository}, lo que le proporciona:
 * - Métodos CRUD (crear, leer, actualizar, eliminar).
 * - Soporte para paginación, ordenamiento y consultas personalizadas.
 * 
 * 🧠 ¿Qué es un repositorio en Spring?
 * Un repositorio es una abstracción de la capa de persistencia. 
 * Permite interactuar con la base de datos sin escribir SQL directamente,
 * usando métodos predefinidos como `findAll()`, `findById()`, `save()`, etc.
 * 
 * ⚠️ Nota: No es necesario usar la anotación @Repository aquí,
 * ya que Spring Data JPA detecta automáticamente las interfaces que
 * extienden de JpaRepository.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {
}
