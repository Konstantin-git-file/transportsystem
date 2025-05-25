package com.tutorial.transportsystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.transportsystem.dto.TicketDTO;
import com.tutorial.transportsystem.entity.City;
import com.tutorial.transportsystem.entity.Station;
import com.tutorial.transportsystem.service.TicketService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Autowired
    private ObjectMapper objectMapper;

    private TicketDTO buildSampleTicket() {
        TicketDTO dto = new TicketDTO();
        dto.setId(1L);
        dto.setUserId(100L);
        dto.setFromCity(City.МОСКВА);
        dto.setFromStation(Station.КАЗАНСКИЙ);
        dto.setToCity(City.КАЗАНЬ);
        dto.setToStation(Station.КАЗАНЬ_ПАССАЖИРСКАЯ);
        dto.setBooked(true);
        dto.setPaid(false);
        return dto;
    }

    @Test
    @DisplayName("POST /api/tickets - success")
    void createTicket_success() throws Exception {
        TicketDTO dto = buildSampleTicket();

        Mockito.when(ticketService.create(any())).thenReturn(dto);

        mockMvc.perform(post("/api/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.fromCity").value(dto.getFromCity().toString()));
    }

    @Test
    @DisplayName("PUT /api/tickets/{id} - success")
    void updateTicket_success() throws Exception {
        TicketDTO dto = buildSampleTicket();

        Mockito.when(ticketService.update(eq(1L), any())).thenReturn(dto);

        mockMvc.perform(put("/api/tickets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(dto.getUserId()));
    }

    @Test
    @DisplayName("GET /api/tickets/{id} - found")
    void getTicketById_found() throws Exception {
        TicketDTO dto = buildSampleTicket();

        Mockito.when(ticketService.getById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/tickets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()));
    }

    @Test
    @DisplayName("GET /api/tickets - list")
    void getAllTickets() throws Exception {
        TicketDTO dto = buildSampleTicket();

        Mockito.when(ticketService.getAll(0, 10, null)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/tickets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fromCity").value(dto.getFromCity().toString()));
    }

    @Test
    @DisplayName("DELETE /api/tickets/{id} - success")
    void deleteTicket() throws Exception {
        mockMvc.perform(delete("/api/tickets/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(ticketService).delete(1L);
    }
}
