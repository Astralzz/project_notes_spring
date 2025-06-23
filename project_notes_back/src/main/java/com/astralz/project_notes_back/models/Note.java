package com.astralz.project_notes_back.models;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entidad Note que representa una nota en la base de datos.
 *
 * @Entity: Declara que esta clase es una entidad JPA.
 * @Table: Mapea esta clase a la tabla 'notes' en la base de datos.
 * 
 * Lombok:
 * @Data: Genera getters, setters, toString, equals y hashCode.
 * @NoArgsConstructor: Genera constructor vacío.
 * @AllArgsConstructor: Genera constructor con todos los campos.
 */
@Entity
@Table(name = "notes")
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
     * Título de la nota (no nulo, máx. 100 caracteres).
     * 
     * @Column(nullable = false, length = 100): Indica que el campo no puede ser
     *                  nulo y tiene un máximo de 100 caracteres.
     */
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * Contenido de la nota.
     * Tipo texto largo.
     * 
     * @Column(columnDefinition = "TEXT"): Indica que el campo es de tipo texto
     *                          largo.
     */
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

}
