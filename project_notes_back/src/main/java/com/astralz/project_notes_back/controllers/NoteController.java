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
@CrossOrigin(origins = "http://localhost:3000")
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
