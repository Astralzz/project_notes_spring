package com.astralz.project_notes_back.controllers;

import com.astralz.project_notes_back.models.Note;
import com.astralz.project_notes_back.services.models.NoteService;

import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para manejar operaciones sobre notas.
 * Permite crear, obtener, actualizar y eliminar notas.
 * 
 * @RequestMapping: Mapea la URL /api/notes para este controlador.
 * @NoteService: Servicio que maneja la lógica de negocio para notas.
 * @Note: Entidad que representa una nota.
 * @Long: Tipo de dato del identificador de la nota.
 * @BaseController: Controlador base que maneja las operaciones CRUD.
 */
@RestController
@RequestMapping("/api/notes")
public class NoteController extends BaseController<Note, Long, NoteService> {

    /**
     * Constructor con inyección de dependencia del servicio NoteService.
     * @param noteService servicio que maneja la lógica de negocio para notas
     */
    public NoteController(NoteService noteService) {
        super(noteService);
    }
}
