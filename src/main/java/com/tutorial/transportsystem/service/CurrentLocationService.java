package com.tutorial.transportsystem.service;

import com.tutorial.transportsystem.dto.CurrentLocationDTO;

import java.util.List;

public interface CurrentLocationService {
    CurrentLocationDTO create(CurrentLocationDTO dto);
    CurrentLocationDTO update(Long id, CurrentLocationDTO dto);
    CurrentLocationDTO getById(Long id);
    List<CurrentLocationDTO> getAll();
    void delete(Long id);
}
