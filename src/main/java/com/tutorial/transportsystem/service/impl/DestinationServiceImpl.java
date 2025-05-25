package com.tutorial.transportsystem.service.impl;


import com.tutorial.transportsystem.dto.DestinationDTO;
import com.tutorial.transportsystem.entity.City;
import com.tutorial.transportsystem.entity.Station;
import com.tutorial.transportsystem.entity.Destination;
import com.tutorial.transportsystem.mapper.DestinationMapper;
import com.tutorial.transportsystem.repository.DestinationRepository;
import com.tutorial.transportsystem.service.DestinationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;
    private final DestinationMapper destinationMapper;

    @Override
    public DestinationDTO create(DestinationDTO dto) {
        validateCityAndStation(dto.getCity(), dto.getStation());
        Destination destination = destinationMapper.toEntity(dto);
        return destinationMapper.toDto(destinationRepository.save(destination));
    }

    @Override
    public DestinationDTO update(Long id, DestinationDTO dto) {
        validateCityAndStation(dto.getCity(), dto.getStation());
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пункт назначения не найден"));
        destination.setLocalDateTime(dto.getLocalDateTime());
        destination.setCity(dto.getCity());
        destination.setStation(dto.getStation());
        return destinationMapper.toDto(destinationRepository.save(destination));
    }

    @Override
    public DestinationDTO getById(Long id) {
        return destinationMapper.toDto(destinationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пункт назначения не найден")));
    }

    @Override
    public List<DestinationDTO> getAll() {
        return destinationRepository.findAll().stream()
                .map(destinationMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        destinationRepository.deleteById(id);
    }

    private void validateCityAndStation(City city, Station station) {
        if (station.getCity() != city) {
            throw new IllegalArgumentException("Станция " + station + " не принадлежит городу " + city);
        }
    }
}