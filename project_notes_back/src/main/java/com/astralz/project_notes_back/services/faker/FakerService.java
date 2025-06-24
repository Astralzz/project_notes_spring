package com.astralz.project_notes_back.services.faker;

import java.time.ZoneId;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.astralz.project_notes_back.models.Note;
import com.astralz.project_notes_back.models.User;
import com.astralz.project_notes_back.models.UserDetails;
import com.astralz.project_notes_back.repositories.NoteRepository;
import com.astralz.project_notes_back.repositories.UserDetailsRepository;
import com.astralz.project_notes_back.repositories.UserRepository;
import com.github.javafaker.Faker;

import jakarta.transaction.Transactional;

/**
 * Servicio que genera datos de prueba para las pruebas unitarias
 * LINK: https://www.baeldung.com/java-faker &
 * https://github.com/DiUS/java-faker
 * 
 * @Faker: Clase que genera datos de prueba para las pruebas unitarias
 * @Service: Indica que esta clase es un servicio.
 * @Profile: Indica que esta clase es un servicio de desarrollo [dev \ prod \
 *           test \ ...].
 */
@Profile("dev")
@Service
public class FakerService {

    // Faker es una clase que genera datos de prueba para las pruebas unitarias
    private final Faker faker;

    // Repositorio que maneja la persistencia de datos para notas
    private final NoteRepository noteRepository;

    // Repositorio que maneja la persistencia de datos para usuarios
    private final UserRepository userRepository;

    // Repositorio que maneja la persistencia de datos para detalles de usuarios
    private final UserDetailsRepository userDetailsRepository;

    /**
     * Constructor que inicializa el servicio de generación de datos de prueba
     * 
     * @param noteRepository: Repositorio que maneja la persistencia de datos para
     *                        notas
     * @param userRepository: Repositorio que maneja la persistencia de datos para
     *                        usuarios
     */
    public FakerService(NoteRepository noteRepository, UserRepository userRepository,
            UserDetailsRepository userDetailsRepository) {
        this.faker = new Faker();
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    /**
     * Genera un usuario de prueba
     * 
     * @return User: Usuario generado
     */
    public User generateUser() {
        User user = new User();
        user.setUsername(faker.name().username());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());

        // Obtenemos el usuario guardado
        return userRepository.save(user);
    }

    /**
     * Genera detalles del usuario prueba
     * 
     * @param user: Usuario
     */
    public void generateUserDetails(User user) {
        // Generamos detalles de usuario
        UserDetails userDetails = new UserDetails();
        userDetails.setPhoneNumber(faker.phoneNumber().subscriberNumber(10));
        userDetails.setBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        userDetails.setAddress(faker.address().fullAddress());
        userDetails.setUser(user);
        userDetailsRepository.save(userDetails);
    }

    /**
     * Genera notas de prueba
     * 
     * @param amount: Cantidad de notas a generar
     * @param user: Usuario
     */
    public void generateNotes(int amount, User user) {
        // Genera notas de prueba
        for (int i = 0; i < amount; i++) {
            Note note = new Note();
            note.setTitle(faker.lorem().sentence(3, 6));
            note.setContent(faker.lorem().paragraph());
            note.setUser(user);
            noteRepository.save(note);
        }
    }

    /**
     * Genera usuarios de prueba
     * 
     * @param amountUsers: Cantidad de usuarios a generar
     * @param amountNotes: Cantidad de notas a generar [Por defecto 3]
     * 
     * @Transactional: Indica que esta función es transaccional.
     */
    @Transactional
    public void generateUsersWithDetailsAndNotes(int amountUsers, int amountNotes) {
        // Genera usuarios de prueba
        for (int i = 0; i < amountUsers; i++) {

            // Generamos un usuario
            User savedUser = generateUser();

            // Generamos detalles de usuario
            generateUserDetails(savedUser);

            // Generamos notas de usuario
            generateNotes(amountNotes, savedUser);
        }
    }
}
