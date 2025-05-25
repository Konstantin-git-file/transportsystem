package com.tutorial.transportsystem.service.ImplTest;

import com.tutorial.transportsystem.dto.TicketDTO;
import com.tutorial.transportsystem.entity.City;
import com.tutorial.transportsystem.entity.Station;
import com.tutorial.transportsystem.entity.Ticket;
import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.mapper.TicketMapper;
import com.tutorial.transportsystem.repository.PassportRepository;
import com.tutorial.transportsystem.repository.TicketRepository;
import com.tutorial.transportsystem.repository.UserRepository;
import com.tutorial.transportsystem.service.impl.TicketServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private TicketMapper ticketMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PassportRepository passportRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private Ticket ticket;
    private TicketDTO ticketDTO;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);

        ticket = new Ticket();
        ticket.setId(1L);
        ticket.setUser(user);
        ticket.setFromCity(City.МОСКВА);
        ticket.setFromStation(Station.valueOf("ЛЕНИНГРАДСКИЙ"));
        ticket.setToCity(City.САНКТ_ПЕТЕРБУРГ);
        ticket.setToStation(Station.valueOf("МОСКОВСКИЙ"));
        ticket.setBooked(false);
        ticket.setPaid(false);

        ticketDTO = new TicketDTO();
        ticketDTO.setId(1L);
        ticketDTO.setUserId(1L);
        ticketDTO.setFromCity(City.МОСКВА);
        ticketDTO.setFromStation(Station.valueOf("ЛЕНИНГРАДСКИЙ"));
        ticketDTO.setToCity(City.САНКТ_ПЕТЕРБУРГ);
        ticketDTO.setToStation(Station.valueOf("МОСКОВСКИЙ"));
        ticketDTO.setBooked(false);
        ticketDTO.setPaid(false);
    }

    @Test
    void createTicket_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(ticketMapper.toEntity(ticketDTO)).thenReturn(ticket);
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        when(ticketMapper.toDto(ticket)).thenReturn(ticketDTO);

        TicketDTO result = ticketService.create(ticketDTO);

        assertNotNull(result);
        assertEquals(ticketDTO.getId(), result.getId());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void createTicket_SameCity_ThrowsException() {
        ticketDTO.setToCity(City.МОСКВА);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ticketService.create(ticketDTO));

        assertEquals("Город отправления и назначения не должны совпадать", exception.getMessage());
        verify(ticketRepository, never()).save(any(Ticket.class));
    }

    @Test
    void createTicket_UserNotFound_ThrowsException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> ticketService.create(ticketDTO));

        assertEquals("Пользователь не найден", exception.getMessage());
        verify(ticketRepository, never()).save(any(Ticket.class));
    }

    @Test
    void updateTicket_Success() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        when(ticketMapper.toDto(ticket)).thenReturn(ticketDTO);

        TicketDTO result = ticketService.update(1L, ticketDTO);

        assertNotNull(result);
        assertEquals(ticketDTO.getId(), result.getId());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void updateTicket_SameCity_ThrowsException() {
        ticketDTO.setToCity(City.МОСКВА);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> ticketService.update(1L, ticketDTO));

        assertEquals("Билет не найден", exception.getMessage()); // Проверяется до проверки совпадения городов
        verify(ticketRepository, times(1)).findById(1L);
        verify(ticketRepository, never()).save(any(Ticket.class));
    }

    @Test
    void updateTicket_TicketNotFound_ThrowsException() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> ticketService.update(1L, ticketDTO));

        assertEquals("Билет не найден", exception.getMessage());
        verify(ticketRepository, never()).save(any(Ticket.class));
    }

    @Test
    void getById_Success() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketMapper.toDto(ticket)).thenReturn(ticketDTO);

        TicketDTO result = ticketService.getById(1L);

        assertNotNull(result);
        assertEquals(ticketDTO.getId(), result.getId());
        verify(ticketRepository, times(1)).findById(1L);
    }

    @Test
    void getById_TicketNotFound_ThrowsException() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> ticketService.getById(1L));

        assertEquals("Билет не найден", exception.getMessage());
        verify(ticketRepository, times(1)).findById(1L);
    }

    @Test
    void getAll_SuccessWithoutFilter() {
        Page<Ticket> page = new PageImpl<>(Collections.singletonList(ticket));
        when(ticketRepository.findAll(PageRequest.of(0, 10))).thenReturn(page);
        when(ticketMapper.toDto(ticket)).thenReturn(ticketDTO);

        List<TicketDTO> result = ticketService.getAll(0, 10, null);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(ticketRepository, times(1)).findAll(PageRequest.of(0, 10));
    }

    @Test
    void getAll_SuccessWithFilter() {
        Page<Ticket> page = new PageImpl<>(Collections.singletonList(ticket));
        when(ticketRepository.findAllByToCity(City.САНКТ_ПЕТЕРБУРГ, PageRequest.of(0, 10))).thenReturn(page);
        when(ticketMapper.toDto(ticket)).thenReturn(ticketDTO);

        List<TicketDTO> result = ticketService.getAll(0, 10, "САНКТ_ПЕТЕРБУРГ");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(ticketRepository, times(1)).findAllByToCity(City.САНКТ_ПЕТЕРБУРГ, PageRequest.of(0, 10));
    }

    @Test
    void delete_Success() {
        doNothing().when(ticketRepository).deleteById(1L);

        ticketService.delete(1L);

        verify(ticketRepository, times(1)).deleteById(1L);
    }
}