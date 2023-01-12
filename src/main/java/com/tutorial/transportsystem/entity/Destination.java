package com.tutorial.transportsystem.entity;

import com.tutorial.transportsystem.listener.BaseDateEntityListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "t_destination")
@Accessors(chain = true)
@EntityListeners(value = BaseDateEntityListener.class)
@NoArgsConstructor

public class Destination extends BaseDateEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "town_town_id")
    private Town town;

    public Destination(Town town) {
        this.town = town;
    }

}
