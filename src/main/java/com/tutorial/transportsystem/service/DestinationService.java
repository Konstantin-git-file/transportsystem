package com.tutorial.transportsystem.service;

import com.tutorial.transportsystem.dto.DestinationDTO;

import java.util.List;

public interface DestinationService {
    DestinationDTO create(DestinationDTO dto);
    DestinationDTO update(Long id, DestinationDTO dto);
    DestinationDTO getById(Long id);
    List<DestinationDTO> getAll();
    void delete(Long id);
}