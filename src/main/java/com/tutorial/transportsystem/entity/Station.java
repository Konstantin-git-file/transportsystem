package com.tutorial.transportsystem.entity;

import com.tutorial.transportsystem.listener.BaseDateEntityListener;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "stations")
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

    @Builder
    public Station(Long id, String stationName, Town townEntities) {
        this.id = id;
        this.stationName = stationName;
        this.townEntities = townEntities;
    }
}
