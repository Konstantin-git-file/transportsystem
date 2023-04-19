package com.tutorial.transportsystem.dto.generalDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tutorial.transportsystem.entity.Town;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class StationDto implements Serializable {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime createdAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime updatedAt;

  private Long id;
  private String stationName;
  private Town town;
}
