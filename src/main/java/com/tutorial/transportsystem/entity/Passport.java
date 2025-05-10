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

    @NotBlank(message = "Serial may not be blank")
    @Pattern(regexp = "^\\d{10}\\D", message = "Serial must be 10 digits followed by a non-digit")
    String serial;

    @NotBlank(message = "Number may not be blank")
    @Pattern(regexp = "^\\d{10}\\D", message = "Number must be 10 digits followed by a non-digit")
    String number;

    @Enumerated(EnumType.STRING)
    Gender gender;

    public enum Gender {
        MALE, FEMALE
    }
}