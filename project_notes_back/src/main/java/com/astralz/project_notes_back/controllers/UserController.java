package com.astralz.project_notes_back.controllers;

import com.astralz.project_notes_back.models.Note;
import com.astralz.project_notes_back.models.User;
import com.astralz.project_notes_back.models.UserDetails;
import com.astralz.project_notes_back.services.models.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 📦 UserController
 * 
 * @RequestMapping: Mapea la URL /api/users para este controlador.
 * @UserService: Servicio que maneja la lógica de negocio para usuarios.
 * @User: Entidad que representa un usuario.
 * @Long: Tipo de dato del identificador de la usuario.
 * @BaseController: Controlador base que maneja las operaciones CRUD.
 */
@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController<User, Long, UserService> {

    /**
     * Constructor con inyección de dependencia del servicio UserService.
     * 
     * @param userService servicio que maneja la lógica de negocio para usuarios.
     */
    public UserController(UserService userService) {
        super(userService);
    }

    /**
     * POST personalizado para crear un usuario y sus detalles.
     * 
     * @param payload DTO que contiene el usuario y sus detalles.
     * @return Usuario creado con código 201 Created.
     */
    @PostMapping("/full")
    public ResponseEntity<User> createWithDetails(@Valid @RequestBody UserWithDetailsPayload payload) {
        User created = service.saveWithDetails(payload.getUser(), payload.getUserDetails(), payload.getNotes());
        return ResponseEntity.status(201).body(created);
    }

    /**
     * 📦 Payload interno para encapsular usuario y detalles.
     * 
     * @Data: Indica que esta clase es un DTO.
     * @NotNull: Indica que el campo no puede ser nulo.
     * @Valid: Indica que el campo debe ser validado.
     * @User: Entidad que representa un usuario.
     * @UserDetails: Entidad que representa los detalles de un usuario.
     */
    @Data
    public static class UserWithDetailsPayload {
        @NotNull(message = "El usuario no puede ser nulo")
        @Valid
        private User user;

        @NotNull(message = "Los detalles del usuario no pueden ser nulos")
        @Valid
        private UserDetails userDetails;

        @Valid
        private List<Note> notes;
    }

}
