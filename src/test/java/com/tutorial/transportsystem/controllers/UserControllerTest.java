package com.tutorial.transportsystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.transportsystem.controllers.UserController;
import com.tutorial.transportsystem.dto.LoginDTO;
import com.tutorial.transportsystem.dto.UserDTO;
import com.tutorial.transportsystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testRegister() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("test");
        userDTO.setPassword("pass");
        userDTO.setEmail("test@example.com");

        Mockito.when(userService.register(any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("test"));
    }

    @Test
    void testLogin() throws Exception {
        LoginDTO loginDTO = new LoginDTO("test", "pass");

        Mockito.when(userService.login(any(LoginDTO.class))).thenReturn(1L);

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L));
    }

    @Test
    void testGetAll() throws Exception {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setLogin("test");

        Mockito.when(userService.getAll(0, 10, null)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].login").value("test"));
    }

    @Test
    void testGetById() throws Exception {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setLogin("user1");

        Mockito.when(userService.getById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("user1"));
    }

    @Test
    void testUpdate() throws Exception {
        UserDTO dto = new UserDTO();
        dto.setLogin("newLogin");

        Mockito.when(userService.update(eq(1L), any(UserDTO.class))).thenReturn(dto);

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("newLogin"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }
}