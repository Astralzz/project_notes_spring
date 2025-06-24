package com.astralz.project_notes_back.models;

import java.util.List;

import com.astralz.project_notes_back.enums.Role;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "email", "username" })
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

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
     * Nombre de usuario.
     * 
     * @NotBlank: Indica que el campo no puede ser nulo o vacío (solo strings).
     * @Size: Indica que el campo debe tener entre 3 y 20 caracteres.
     * @Column: Indica que el campo es una columna de la base de datos.
     */
    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Size(min = 3, max = 30, message = "El nombre de usuario debe tener entre 3 y 30 caracteres.")
    @Column(nullable = false, length = 30, unique = true)
    private String username;

    /**
     * Email del usuario.
     * 
     * @NotBlank: Indica que el campo no puede ser nulo o vacío (solo strings).
     * @Size: Indica que el campo debe tener entre 3 y 20 caracteres.
     * @Column: Indica que el campo es una columna de la base de datos.
     */
    @NotBlank(message = "El email es obligatorio.")
    @Size(min = 3, max = 50, message = "El email debe tener entre 3 y 50 caracteres.")
    @Email(message = "El email no es válido.")
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    /**
     * Contraseña del usuario.
     * 
     * @NotBlank: Indica que el campo no puede ser nulo o vacío (solo strings).
     * @Size: Indica que el campo debe tener entre 3 y 20 caracteres.
     * @Column: Indica que el campo es una columna de la base de datos.
     */
    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 8, max = 28, message = "La contraseña debe tener entre 8 y 28 caracteres.")
    @Column(nullable = false, length = 100)
    private String password;

    /**
     * Rol del usuario.
     * 
     * @Enumerated (EnumType.STRING): Indica que el campo es un enumerado.
     * @Column: Indica que el campo es una columna de la base de datos.
     * @Default: Indica que el valor por defecto es PUBLIC_USER.
     * 
     *           ✔️ Usa @Enumerated(EnumType.STRING) para guardar el nombre del enum
     *           en texto.
     *           ⚠️ IMPORTANTE: No usar columnDefinition con ENUMs si se quiere
     *           portabilidad.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role = Role.PUBLIC_USER;

    /**
     * Nombre del usuario (no nulo, máx. 120 caracteres).
     * 
     * @NotBlank: Indica que el campo no puede ser nulo o vacío (solo strings).
     * @Size: Indica que el campo debe tener entre 3 y 120 caracteres.
     * @Column(nullable = false, length = 120): Indica que el campo no puede ser
     *                  nulo y tiene un máximo de 120 caracteres.
     */
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 3, max = 120, message = "El nombre debe tener entre 3 y 120 caracteres.")
    @Column(nullable = false, length = 120)
    private String firstName;

    /**
     * Apellido del usuario (no nulo, máx. 120 caracteres).
     * 
     * @NotBlank: Indica que el campo no puede ser nulo o vacío (solo strings).
     * @Size: Indica que el campo debe tener entre 3 y 120 caracteres.
     * @Column(nullable = false, length = 120): Indica que el campo no puede ser
     *                  nulo y tiene un máximo de 120 caracteres.
     */
    @NotBlank(message = "El(los) apellido(s) es(son) obligatorio(s).")
    @Size(min = 3, max = 120, message = "El(los) apellido(s) debe(n) tener entre 3 y 120 caracteres.")
    @Column(nullable = false, length = 120)
    private String lastName;

    /**
     * Relación 1:1 con el usuario. [1:1 = 1 usuario puede tener un solo detalle]
     * 
     * @OneToOne: Indica que esta relación es de uno a uno.
     * @mappedBy: Indica que esta relación es de uno a uno.
     * @CascadeType.ALL: Indica que se debe eliminar o actualizar todos los
     *                   detalles cuando se elimina el usuario.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserDetails userDetails;

    /**
     * 1:N relación con Note [1:N = 1 usuario puede tener muchas notas]
     * 
     * @OneToMany: Indica que esta relación es de uno a muchos.
     * @mappedBy: Indica que esta relación es de uno a muchos.
     * @CascadeType.ALL: Indica que se debe eliminar o actualizar todas las notas
     *                   cuando se
     *                   elimina el usuario.
     * @orphanRemoval: Indica que se debe eliminar las notas cuando se elimina el
     *                 usuario.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;
}
