package com.astralz.project_notes_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.astralz.project_notes_back.models.UserDetails;

/**
 * 📦 UserDetailsRepository
 * 
 * @Repository: Indica que esta interfaz es un repositorio de Spring Data JPA.
 * @JpaRepository: Extiende JpaRepository para manejar la persistencia de datos
 *                 de los detalles de los usuarios.
 * @UserDetails: Entidad que representa los detalles de un usuario.
 * @Long: Tipo de dato del identificador de los detalles de un usuario.
 */
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
