package com.astralz.project_notes_back.services.models;

import java.util.List;

import org.springframework.stereotype.Service;

import com.astralz.project_notes_back.models.Note;
import com.astralz.project_notes_back.models.User;
import com.astralz.project_notes_back.models.UserDetails;
import com.astralz.project_notes_back.repositories.NoteRepository;
import com.astralz.project_notes_back.repositories.UserDetailsRepository;
import com.astralz.project_notes_back.repositories.UserRepository;

import jakarta.transaction.Transactional;

/**
 * 📦 UserService
 * 
 * @Service: Indica que esta clase es un servicio.
 * @UserRepository: Repositorio que maneja la persistencia de datos para
 *                  usuarios.
 */
@Service
public class UserService extends BaseModelService<User, Long> {

    private final NoteRepository noteRepository;

    /**
     * Repositorio que maneja la persistencia de datos para detalles de usuarios.
     */
    private final UserDetailsRepository userDetailsRepository;

    /**
     * Repositorio que maneja la persistencia de datos para usuarios.
     */
    private final UserRepository userRepository;

    /**
     * Constructor con inyección de dependencia del repositorio UserRepository.
     * 
     * @param userRepository        Repositorio que maneja la persistencia de datos
     *                              para
     *                              usuarios.
     * @param userDetailsRepository Repositorio que maneja la persistencia de datos
     *                              para detalles de usuarios.
     * @param noteRepository        Repositorio que maneja la persistencia de datos
     *                              para notas.
     */
    public UserService(UserRepository userRepository, UserDetailsRepository userDetailsRepository,
            NoteRepository noteRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.noteRepository = noteRepository;
    }

    /**
     * Guarda un usuario junto con sus detalles.
     * 
     * @Transactional: Indica que esta operación debe ser transaccional.
     * 
     * @param user    Usuario a guardar.
     * @param details Detalles del usuario.
     * @return Usuario guardado.
     */
    @Transactional
    public User saveWithDetails(User user, UserDetails details, List<Note> notes) {
        // Primero guardas el usuario (generando el ID)
        User savedUser = userRepository.save(user);

        // Establece la relación bidireccional de UserDetails
        details.setUser(savedUser);
        userDetailsRepository.save(details);

        // Si hay notas, asigna la relación con el usuario y guarda
        if (notes != null && !notes.isEmpty()) {
            // Asigna la relación con el usuario y guarda
            for (Note note : notes) {
                note.setUser(savedUser);
            }
            noteRepository.saveAll(notes);
        }

        return savedUser;
    }
}
