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
@Table(name = "town")
@Accessors(chain = true)
@EntityListeners(value = BaseDateEntityListener.class)
@NoArgsConstructor

public class Town extends BaseDateEntity {

    @Id
    @Column(name = "town_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String townName;

    @Column(name = "priority", nullable = false)
    private int priority;

//    @OneToMany
//    @JoinColumn(name = "station_id")
//    private List<StationEntity> station;

    @Builder
    public Town(Long id, String townName) {
        this.id = id;
        this.townName = townName;
       ;
    }
}