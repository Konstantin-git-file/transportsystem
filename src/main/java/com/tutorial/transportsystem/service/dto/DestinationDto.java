package com.tutorial.transportsystem.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tutorial.transportsystem.entity.Town;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@Builder
public class DestinationDto implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private final Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private final Long id;
    private final Town town;
}
