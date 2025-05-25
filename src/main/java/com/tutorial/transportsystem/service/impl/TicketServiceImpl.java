package com.tutorial.transportsystem.service.impl;

import com.tutorial.transportsystem.dto.TicketDTO;
import com.tutorial.transportsystem.entity.City;
import com.tutorial.transportsystem.entity.Ticket;
import com.tutorial.transportsystem.mapper.TicketMapper;
import com.tutorial.transportsystem.repository.*;
import com.tutorial.transportsystem.service.TicketService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final UserRepository userRepository;
    private final PassportRepository passportRepository;

    @Override
    public TicketDTO create(TicketDTO dto) {
        if (dto.getFromCity() == dto.getToCity()) {
            throw new IllegalArgumentException("Город отправления и назначения не должны совпадать");
        }

        Ticket ticket = ticketMapper.toEntity(dto);
        ticket.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден")));

//        if (dto.getPassportId() != null) {
//            ticket.setPassport(passportRepository.findById(dto.getPassportId())
//                    .orElseThrow(() -> new EntityNotFoundException("Паспорт не найден")));
//        }

        return ticketMapper.toDto(ticketRepository.save(ticket));
    }

    @Override
    public TicketDTO update(Long id, TicketDTO dto) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Билет не найден"));

        if (dto.getFromCity() == dto.getToCity()) {
            throw new IllegalArgumentException("Город отправления и назначения не должны совпадать");
        }

        ticket.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден")));

        ticket.setFromCity(dto.getFromCity());
        ticket.setFromStation(dto.getFromStation());
        ticket.setToCity(dto.getToCity());
        ticket.setToStation(dto.getToStation());
        ticket.setBooked(dto.isBooked());
        ticket.setPaid(dto.isPaid());

//        if (dto.getPassportId() != null) {
//            ticket.setPassport(passportRepository.findById(dto.getPassportId())
//                    .orElseThrow(() -> new EntityNotFoundException("Паспорт не найден")));
//        }

        return ticketMapper.toDto(ticketRepository.save(ticket));
    }

    @Override
    public TicketDTO getById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Билет не найден"));
        return ticketMapper.toDto(ticket);
    }

    @Override
    public List<TicketDTO> getAll(int page, int size, String toCity) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Ticket> pageResult = (toCity == null || toCity.isBlank())
                ? ticketRepository.findAll(pageable)
                : ticketRepository.findAllByToCity(City.valueOf(toCity), pageable);
        return pageResult.map(ticketMapper::toDto).getContent();
    }

    @Override
    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }
}