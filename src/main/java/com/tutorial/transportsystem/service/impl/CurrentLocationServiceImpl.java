package com.tutorial.transportsystem.service.impl;

import com.tutorial.transportsystem.dto.CurrentLocationDTO;
import com.tutorial.transportsystem.entity.CurrentLocation;
import com.tutorial.transportsystem.mapper.CurrentLocationMapper;
import com.tutorial.transportsystem.repository.CurrentLocationRepository;
import com.tutorial.transportsystem.service.CurrentLocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrentLocationServiceImpl implements CurrentLocationService {

    private final CurrentLocationRepository locationRepository;
    private final CurrentLocationMapper locationMapper;

    @Override
    public CurrentLocationDTO create(CurrentLocationDTO dto) {
        return locationMapper.toDto(locationRepository.save(locationMapper.toEntity(dto)));
    }

    @Override
    public CurrentLocationDTO update(Long id, CurrentLocationDTO dto) {
        CurrentLocation location = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Локация не найдена"));
        location.setLocalDateTime(dto.getLocalDateTime());
        location.setCity(dto.getCity());
        location.setStation(dto.getStation());
        return locationMapper.toDto(locationRepository.save(location));
    }

    @Override
    public CurrentLocationDTO getById(Long id) {
        return locationMapper.toDto(locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Локация не найдена")));
    }

    @Override
    public List<CurrentLocationDTO> getAll() {
        return locationRepository.findAll().stream()
                .map(locationMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        locationRepository.deleteById(id);
    }
}