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
