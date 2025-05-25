package com.tutorial.transportsystem.service.impl;

import com.tutorial.transportsystem.dto.PassportDTO;
import com.tutorial.transportsystem.entity.Passport;
import com.tutorial.transportsystem.mapper.PassportMapper;
import com.tutorial.transportsystem.repository.PassportRepository;
import com.tutorial.transportsystem.service.PassportService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;

    @Override
    public PassportDTO create(PassportDTO dto) {
        Passport entity = passportMapper.toEntity(dto);
        return passportMapper.toDto(passportRepository.save(entity));
    }

    @Override
    public PassportDTO update(Long id, PassportDTO dto) {
        Passport entity = passportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Паспорт не найден"));
        entity.setSerial(dto.getSerial());
        entity.setNumber(dto.getNumber());
        entity.setGender(dto.getGender());
        return passportMapper.toDto(passportRepository.save(entity));
    }

    @Override
    public PassportDTO getById(Long id) {
        return passportMapper.toDto(passportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Паспорт не найден")));
    }

    @Override
    public List<PassportDTO> getAll(int page, int size, String serial) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Passport> pageResult = (serial == null || serial.isBlank())
                ? passportRepository.findAll(pageable)
                : passportRepository.findAllBySerial(serial, pageable);
        return pageResult.map(passportMapper::toDto).getContent();
    }

    @Override
    public void delete(Long id) {
        passportRepository.deleteById(id);
    }
}