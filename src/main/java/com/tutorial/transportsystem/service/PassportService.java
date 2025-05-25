package com.tutorial.transportsystem.service;

import com.tutorial.transportsystem.dto.PassportDTO;

import java.util.List;

public interface PassportService {
    PassportDTO create(PassportDTO dto);
    PassportDTO update(Long id, PassportDTO dto);
    PassportDTO getById(Long id);
    List<PassportDTO> getAll(int page, int size, String serial);
    void delete(Long id);
}