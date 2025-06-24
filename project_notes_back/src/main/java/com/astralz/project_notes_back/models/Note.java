package com.astralz.project_notes_back.models;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entidad Note que representa una nota en la base de datos.
 *
 * @Entity: Declara que esta clase es una entidad JPA.
 * @Table: Mapea esta clase a la tabla 'notes' en la base de datos.
 * @UniqueConstraint: Indica que el campo title debe ser único.
 * 
 *                    Lombok:
 * @Data: Genera getters, setters, toString, equals y hashCode.
 * @NoArgsConstructor: Genera constructor vacío.
 * @AllArgsConstructor: Genera constructor con todos los campos.
 */
@Entity
@Table(name = "users_notes", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "title" })
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    /**
     * Identificador único de la nota.
     * Se genera automáticamente.
     * 
     * @Id: Indica que este campo es la clave primaria de la entidad.
     * @GeneratedValue(strategy = GenerationType.IDENTITY): Genera el valor
     *                          automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título de la nota (no nulo, máx. 120 caracteres).
     * 
     * @NotBlank: Indica que el campo no puede ser nulo o vacío.
     * @Size: Indica que el campo debe tener entre 3 y 120 caracteres.
     * @Column(nullable = false, length = 120): Indica que el campo no puede ser
     *                  nulo y tiene un máximo de 120 caracteres.
     */
    @NotBlank(message = "El título es obligatorio.")
    @Size(min = 3, max = 120, message = "El título debe tener entre 3 y 120 caracteres.")
    @Column(nullable = false, length = 120)
    private String title;

    /**
     * Contenido de la nota.
     * Tipo texto largo.
     * 
     * @NotBlank: Indica que el campo no puede ser nulo o vacío (solo strings).
     * @Size: Indica que el campo debe tener entre 3 y 2400 caracteres.
     * @Column(columnDefinition = "TEXT"): Indica que el campo es de tipo texto
     *                          largo.
     */
    @NotBlank(message = "El contenido es obligatorio.")
    @Size(min = 3, max = 2400, message = "El contenido debe tener entre 3 y 2400 caracteres.")
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * Fecha de creación de la nota (solo fecha, sin hora).
     * 
     * @CreationTimestamp: Genera la fecha de creación automáticamente.
     * @Column(updatable = false): Evita que se actualice accidentalmente.
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate creationDate;

    /**
     * 1:N relación con User [1:N = 1 nota pertenece a 1 usuario]
     * 
     * @ManyToOne: Indica que esta relación es de muchos a uno.
     * @NotNull: Indica que el campo no puede ser nulo.
     * @JoinColumn: Indica que el campo user_id es la clave foránea.
     */
    @ManyToOne
    @NotNull(message = "Esta nota no tiene un usuario asignado.")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
