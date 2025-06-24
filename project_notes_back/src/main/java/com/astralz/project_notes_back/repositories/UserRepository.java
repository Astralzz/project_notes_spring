package com.astralz.project_notes_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.astralz.project_notes_back.models.User;

/**
 * 📦 UserRepository
 * 
 * @Repository: Indica que esta interfaz es un repositorio de Spring Data JPA.
 * @JpaRepository: Extiende JpaRepository para manejar la persistencia de datos
 *                 de los usuarios.
 * @User: Entidad que representa un usuario.
 * @Long: Tipo de dato del identificador de un usuario.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
