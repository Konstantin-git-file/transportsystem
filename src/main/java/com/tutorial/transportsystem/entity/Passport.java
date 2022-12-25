package com.tutorial.transportsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Passport {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id", nullable = false)
    Long id;

    @Pattern(regexp = "^\\d{10}\\D")
    String serial;

    @Pattern(regexp = "^\\d{10}\\D")
    String number;

    @Pattern(regexp = "(?:m|M|male|Male|f|F|female|Female|FEMALE|MALE)$")
    String gender;

}
