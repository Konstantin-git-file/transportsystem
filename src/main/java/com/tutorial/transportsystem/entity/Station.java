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
@Table(name = "t_station")
@Accessors(chain = true)
@EntityListeners(value = BaseDateEntityListener.class)
@NoArgsConstructor

public class Station extends BaseDateEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "station", nullable = false)
    private String stationName;

    @ManyToOne
    Town townEntities;

}
