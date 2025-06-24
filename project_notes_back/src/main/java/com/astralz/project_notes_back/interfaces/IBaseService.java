package com.astralz.project_notes_back.interfaces;

import java.util.List;
import java.util.Optional;

/**
 * 📦 IBaseService
 * 
 * @M: Tipo de entidad que maneja la lógica de negocio.
 * @ID: Tipo de identificador de la entidad.
 */
public interface IBaseService<M, ID> {

    /**
     * Obtiene todas las M.
     * 
     * @return Lista de M.
     */
    public abstract List<M> findAll();

    /**
     * Obtiene una nota por su ID.
     * 
     * @param id ID de la nota.
     * @return Nota encontrada.
     */
    public abstract Optional<M> findById(ID id);

    /**
     * Guarda una nota.
     * 
     * @param m M a guardar.
     * @return M guardada.
     */
    public abstract M save(M m);

    /**
     * Actualiza una nota.
     * 
     * @param id ID de la M.
     * @param m  M actualizada.
     * @return M actualizada.
     */
    public abstract Optional<M> update(ID id, M m);

    /**
     * Elimina una M por su ID.
     * 
     * @param id ID de la M.
     * @return true si la M fue eliminada, false en caso contrario.
     */
    public abstract boolean deleteById(ID id);

}
