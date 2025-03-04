package com.example.theatre.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.theatre.dto.TheatreRequestDto;
import com.example.theatre.entity.Theatre;
import com.example.theatre.service.TheatreService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TheatreController.class)
class TheatreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TheatreService theatreService;

    @InjectMocks
    private TheatreController theatreController;

    private Theatre theatre;
    private TheatreRequestDto theatreRequestDto;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(theatreController).build();
        
        theatre = new Theatre();
        theatre.setTheatreId(1l);
        theatre.setTheatreName("Grand Theatre");

        theatreRequestDto = new TheatreRequestDto();
        theatreRequestDto.setTheatreName("Grand Theatre");
        theatreRequestDto.setLocation("Noida");
    }

    @Test
    void testCreateTheatre() throws Exception {
        when(theatreService.createTheatre(any(TheatreRequestDto.class))).thenReturn(theatre);

        mockMvc.perform(post("/api/theatres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(theatreRequestDto)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.theatreId").value(1))
                .andExpect(jsonPath("$.theatreName").value("Grand Theatre"));

        verify(theatreService, times(1)).createTheatre(any(TheatreRequestDto.class));
    }

    @Test
    void testGetAllTheatres() throws Exception {
        List<Theatre> theatres = Arrays.asList(theatre);
        when(theatreService.getAllTheatres()).thenReturn(theatres);

        mockMvc.perform(get("/api/theatres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].theatreId").value(1))
                .andExpect(jsonPath("$[0].theatreName").value("Grand Theatre"));

        verify(theatreService, times(1)).getAllTheatres();
    }

    @Test
    void testUpdateTheatre() throws Exception {
        when(theatreService.updateTheatre(eq(1L), any(TheatreRequestDto.class))).thenReturn(theatre);

        mockMvc.perform(put("/api/theatres/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(theatreRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.theatreId").value(1))
                .andExpect(jsonPath("$.theatreName").value("Grand Theatre"));

        verify(theatreService, times(1)).updateTheatre(eq(1L), any(TheatreRequestDto.class));
    }
}

