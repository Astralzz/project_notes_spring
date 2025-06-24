package com.astralz.project_notes_back.models;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_details", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "phone_number" })
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

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
     * Número de teléfono del usuario.
     * 
     * @NotBlank: Indica que el campo no puede ser nulo o vacío (solo strings).
     * @Size: Indica que el campo debe tener entre 8 y 12 caracteres.
     * @Column (nullable = false, length = 12, unique = true): Indica que el campo
     *         no puede ser nulo, tiene un máximo de 12 caracteres y es
     *         único.
     */
    @NotBlank(message = "El número de teléfono es obligatorio.")
    @Size(min = 8, max = 12, message = "El número de teléfono debe tener entre 8 y 12 caracteres.")
    @Column(nullable = false, length = 12, unique = true)
    private String phoneNumber;


    /**
     * Fecha de nacimiento del usuario.
     * 
     * @Column(nullable = true): Indica que el campo puede ser nulo.
     */
    @PastOrPresent(message = "La fecha de nacimiento no puede ser en el futuro.")
    @Column(nullable = true)
    private LocalDate birthDate;

    /**
     * Dirección del usuario.
     * 
     * @Size: Indica que el campo debe tener entre 3 y 120 caracteres.
     * @Column(nullable = true, length = 120): Indica que el campo puede ser nulo
     *                  y tiene un máximo de 120 caracteres.
     */
    @Size(min = 3, max = 120, message = "La dirección debe tener entre 3 y 120 caracteres.")
    @Column(nullable = true, length = 120)
    private String address;

    /**
     * Relación 1:1 con el usuario. [1:1 = 1 usuario puede tener un solo detalle]
     * 
     * @OneToOne: Indica que esta relación es de uno a uno.
     * @NotNull: Indica que el campo no puede ser nulo.
     * @JoinColumn: Indica que esta relación es de uno a uno.
     * @CascadeType.ALL: Indica que se debe eliminar o actualizar todos los
     *                   detalles cuando se elimina el usuario.
     * @orphanRemoval: Indica que se debe eliminar los detalles cuando se elimina
     *                 el usuario.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull(message = "Estos detalles no tienen un usuario asignado.")
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

}
