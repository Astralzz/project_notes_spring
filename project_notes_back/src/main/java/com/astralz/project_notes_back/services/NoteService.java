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
