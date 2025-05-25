package com.tutorial.transportsystem.service;

import com.tutorial.transportsystem.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    TicketDTO create(TicketDTO dto);
    TicketDTO update(Long id, TicketDTO dto);
    TicketDTO getById(Long id);
    List<TicketDTO> getAll(int page, int size, String cityAndStation);
    void delete(Long id);
}