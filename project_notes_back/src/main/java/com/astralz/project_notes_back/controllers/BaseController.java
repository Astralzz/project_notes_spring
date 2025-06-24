package com.astralz.project_notes_back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.astralz.project_notes_back.services.models.BaseModelService;

import jakarta.validation.Valid;

/**
 * 📦 BaseController
 * 
 * @CrossOrigin: Permite que el controlador se comunique con el frontend [* =
 *               todos los origenes].
 * @RestController: Indica que esta clase es un controlador REST.
 * @M: Tipo de entidad que maneja la lógica de negocio.
 * @ID: Tipo de identificador de la entidad.
 * @S: Tipo de servicio que extiende BaseService para la entidad M.
 * 
 * @BaseController: Controlador base que maneja las operaciones CRUD.
 * 
 * # CRUD
 * @service: Servicio que maneja la lógica de negocio para la entidad T.
 * @getAll: Obtiene todas las entidades.
 * @getById: Obtiene una entidad por su ID.
 * @create: Crea una nueva entidad.
 * @update: Actualiza una entidad existente.
 * @delete: Elimina una entidad por su ID.
 * 
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public abstract class BaseController<M, ID, S extends BaseModelService<M, ID>> {

    /**
     * Servicio que maneja la lógica de negocio para la entidad T.
     */
    protected final S service;

    /**
     * Constructor con inyección de dependencia del servicio T.
     * 
     * @param service servicio que maneja la lógica de negocio para la entidad T.
     */
    @Autowired
    public BaseController(S service) {
        this.service = service;
    }

    /**
     * Obtener todas las notas.
     * 
     * GET /api/{M}
     * 
     * @GetMapping: Mapea la URL /api/{M} para este método [GET].
     * 
     * @return lista de notas en formato JSON
     */
    @GetMapping
    public List<M> getAll() {
        return this.service.findAll();
    }

    /**
     * Obtener una nota por su ID.
     * 
     * GET /api/{M}/{id}
     * 
     * @GetMapping: Mapea la URL /api/{M}/{id} para este método [GET].
     * @PathVariable: Indica que el parámetro id es una variable de la URL.
     * 
     * @param id identificador de la nota
     * @return nota encontrada o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<M> getById(@PathVariable ID id) {
        return this.service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crear una nueva nota.
     * 
     * POST /api/{M}
     * 
     * @PostMapping: Mapea la URL /api/{M} para este método [POST].
     * @Valid: Indica que el objeto M debe ser validado.
     * @RequestBody: Indica que el objeto M debe ser recibido en el cuerpo de la
     *               petición.
     * 
     * @param note objeto M con los datos de la nueva nota
     * @return la nota creada con código 201 Created
     */
    @PostMapping
    public ResponseEntity<M> create(@Valid @RequestBody M note) {
        M createdM = this.service.save(note);
        return ResponseEntity.status(201).body(createdM);
    }

    /**
     * Actualizar una nota existente.
     * 
     * PUT /api/{M}/{id}
     * 
     * @PutMapping: Mapea la URL /api/{M}/{id} para este método [PUT].
     * @Valid: Indica que el objeto M debe ser validado.
     * @RequestBody: Indica que el objeto M debe ser recibido en el cuerpo de la
     *               petición.
     * 
     * @param id   id de la nota a actualizar
     * @param note datos actualizados de la nota
     * @return nota actualizada o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<M> update(@PathVariable ID id, @Valid @RequestBody M note) {
        return this.service.update(id, note)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Eliminar una nota por su ID.
     * 
     * DELETE /api/{M}/{id}
     * 
     * @DeleteMapping: Mapea la URL /api/{M}/{id} para este método [DELETE].
     * 
     * @param id id de la nota a eliminar
     * 
     * @return código 204 No Content si fue eliminada o 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        // ? La nota se elimino correctamente
        if (this.service.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        // ? La nota no existe
        return ResponseEntity.notFound().build();
    }
}
