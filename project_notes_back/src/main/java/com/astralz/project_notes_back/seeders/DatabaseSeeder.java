package com.astralz.project_notes_back.seeders;

import com.astralz.project_notes_back.repositories.NoteRepository;
import com.astralz.project_notes_back.repositories.UserRepository;
import com.astralz.project_notes_back.services.faker.FakerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que se encarga de generar notas de prueba
 * 
 * @Configuration: Indica que esta clase es una configuración de Spring [config
 *                 = Configuración]
 * @ApplicationRunner: Indica que esta clase es un runner de Spring [runner = Al
 *                     iniciar la aplicación]
 * @Profile: Indica que esta clase es un perfil de Spring [dev \ prod \ test \
 *           ...]
 */
@Profile({ "dev", "prod" })
@Configuration
public class DatabaseSeeder {

    // Faker es una clase que genera datos de prueba para las pruebas unitarias
    private final FakerService fakerService;

    // Logger para el seeder [Para mostrar errores o información en la consola]
    private final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    /**
     * Constructor que inicializa el servicio de generación de datos de prueba
     * 
     * @param fakerService: Servicio que genera datos de prueba para las pruebas
     *                      unitarias
     * @Autowired(required = false): Indica que el faker es opcional (Para que no se
     *                     generen datos de prueba en la producción).
     */
    public DatabaseSeeder(@Autowired(required = false) FakerService fakerService) {
        this.fakerService = fakerService;
    }

    /**
     * Método que se encarga de generar notas de prueba
     * 
     * @param userRepository: Repositorio que maneja la persistencia de datos para
     *                        usuarios
     * @param noteRepository: Repositorio que maneja la persistencia de datos para
     *                        notas
     * @return ApplicationRunner: Runner de Spring
     */
    @Bean
    public ApplicationRunner setDataFactory(UserRepository userRepository, NoteRepository noteRepository) {
        return args -> {

            try {

                // Genera las notas obligatorias
                // this.generateStaticNotes(noteRepository);

                // Factory (only dev)
                // Si hay menos de 6 usuarios, genera 15 usuarios de prueba (Para solo
                // crear una vez)
                if (this.fakerService != null && userRepository.count() <= 5) {

                    // Counts
                    int amountUsers = 15;
                    int amountNotes = 20;

                    // Genera usuarios con detalles y notas
                    this.fakerService.generateUsersWithDetailsAndNotes(amountUsers, amountNotes);

                    // Muestra el número de notas generadas
                    logger.info("📄 EXITO: " + amountUsers + " usuarios aleatorios generados");
                }

                // ! Error
            } catch (Exception e) {
                logger.error("❌ ERROR: Los datos no se han generado correctamente");
                logger.error("❌ ERROR: " + e.getMessage());
            }
        };
    }
}
