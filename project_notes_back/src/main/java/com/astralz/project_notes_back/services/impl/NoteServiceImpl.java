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
