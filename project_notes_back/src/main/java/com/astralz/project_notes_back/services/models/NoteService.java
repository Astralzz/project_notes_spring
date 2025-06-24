package com.astralz.project_notes_back.services.models;

import com.astralz.project_notes_back.models.Note;
import com.astralz.project_notes_back.repositories.NoteRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * Interfaz que define los métodos para manejar las notas.
 * 
 * @NoteService: Interfaz que define los métodos para manejar las notas.
 * @Note: Entidad que representa una nota.
 * @Long: Tipo de dato del identificador de la nota.
 */
@Service
public class NoteService extends BaseModelService<Note, Long> {

    /**
     * Constructor con inyección de dependencia del repositorio NoteRepository.
     * 
     * @param noteRepository Repositorio que maneja la persistencia de datos para
     *                       notas.
     */
    public NoteService(NoteRepository noteRepository) {
        super(noteRepository);
    }

    /**
     * Obtiene todas las notas.
     * 
     * @return Lista de notas.
     */
    @Override
    public Optional<Note> update(Long id, Note updatedNote) {

        // ? Existe la nota
        if (repository.existsById(id)) {

            // ? Actualiza la nota
            return super.findById(id).map(existingNote -> {
                existingNote.setTitle(updatedNote.getTitle());
                existingNote.setContent(updatedNote.getContent());
                return repository.save(existingNote);
            });

        }

        return Optional.empty();
    }

}
