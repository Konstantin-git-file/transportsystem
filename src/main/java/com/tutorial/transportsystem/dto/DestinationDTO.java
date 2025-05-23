package com.tutorial.transportsystem.dto;

import com.tutorial.transportsystem.entity.CityAndStation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DestinationDTO {
    private Long id;
    private LocalDateTime localDateTime;
    private CityAndStation cityAndStation;
}
