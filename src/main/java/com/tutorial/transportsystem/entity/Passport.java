package com.tutorial.transportsystem.entity;

import com.tutorial.transportsystem.listener.BaseDateEntityListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "t_passport")
@Accessors(chain = true)
@EntityListeners(value = BaseDateEntityListener.class)
@NoArgsConstructor

public class Passport extends BaseDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Pattern(regexp = "^\\d{10}\\D")
    @Column(name = "serial")
    private String serial;

    @Pattern(regexp = "^\\d{10}\\D")
    @Column(name = "number")
    private String number;

    @Pattern(regexp = "(?:m|M|male|Male|f|F|female|Female|FEMALE|MALE)$")
    @Column(name = "gender")
    private String gender;


    @OneToOne(mappedBy = "passport", optional = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
