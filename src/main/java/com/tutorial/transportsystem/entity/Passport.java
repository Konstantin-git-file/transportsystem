package com.tutorial.transportsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Passport {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @NotBlank(message = "Серийный номер должен быть не пустым")
    @Pattern(regexp = "^\\d{10}\\D", message = "Серийный номер должен состоять из 10 цифр")
    String serial;

    @NotBlank(message = "Номер не должен быть пустым")
    @Pattern(regexp = "^\\d{10}\\D", message = "Номер должен состоять из 10 цифр")
    String number;

    @Enumerated(EnumType.STRING)
    Gender gender;

    public enum Gender {
        MALE, FEMALE
    }
}