package com.astralz.project_notes_back.services.models;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astralz.project_notes_back.interfaces.IBaseService;

/**
 * 📦 BaseService
 * 
 * @M: Tipo de entidad que maneja la lógica de negocio.
 * @ID: Tipo de identificador de la entidad.
 */
public abstract class BaseModelService<M, ID> implements IBaseService<M, ID> {

    /**
     * Obtiene el repositorio de la entidad.
     * 
     * @return Repositorio de la entidad.
     */
    protected final JpaRepository<M, ID> repository;


    /**
     * Constructor con inyección de dependencia del repositorio M.
     * 
     * @param repository Repositorio que maneja la persistencia de datos para la
     *                   entidad M.
     */
    public BaseModelService(JpaRepository<M, ID> repository) {
        this.repository = repository;
    }

    /**
     * Obtiene todas las M.
     * 
     * @return Lista de M.
     */
    @Override
    public List<M> findAll() {
        return repository.findAll();
    }

    /**
     * Obtiene una M por su ID.
     * 
     * @param id ID de la M.
     * @return M encontrada.
     */
    @Override
    public Optional<M> findById(ID id) {
        return repository.findById(id);
    }

    /**
     * Guarda una M.
     * 
     * @param entity M a guardar.
     * @return M guardada.
     */
    @Override
    public M save(M entity) {
        return repository.save(entity);
    }

    /**
     * Actualiza una M.
     * 
     * @param id ID de la M.
     * @param entity M a actualizar.
     * @return M actualizada.
     */
    @Override
    public Optional<M> update(ID id, M entity) {

        // ? Si la M existe, la actualiza y retorna la M actualizada
        if (repository.existsById(id)) {

            // ? Actualiza la M
            return Optional.of(repository.save(entity));
        }

        // ? Si la M no existe, retorna empty
        return Optional.empty();
    }

    /**
     * Elimina una M por su ID.
     * 
     * @param id ID de la M.
     * @return true si la M fue eliminada, false en caso contrario.
     */
    @Override
    public boolean deleteById(ID id) {

        // ? Si la M existe, la elimina y retorna true
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        
        // ? Si la M no existe, retorna false
        return false;
    }

}
