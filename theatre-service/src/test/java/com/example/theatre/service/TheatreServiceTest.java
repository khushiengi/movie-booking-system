package com.example.theatre.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.theatre.dto.TheatreRequestDto;
import com.example.theatre.entity.Theatre;
import com.example.theatre.mapper.TheatreRequestDtoToEntityMapper;
import com.example.theatre.repository.TheatreRepository;

@ExtendWith(MockitoExtension.class)
class TheatreServiceTest {

    @Mock
    private TheatreRepository theatreRepository;

    @Mock
    private TheatreRequestDtoToEntityMapper mapper;

    @Mock
    private TheatreProducer theatreProducer;

    @InjectMocks
    private TheatreService theatreService;

    private Theatre theatre;
    private TheatreRequestDto theatreRequestDto;

    @BeforeEach
    void setUp() {
        theatre = new Theatre();
        theatre.setTheatreId(1L);
        theatre.setTheatreName("Grand Theatre");
        theatre.setLocation("New York");

        theatreRequestDto = new TheatreRequestDto();
        theatreRequestDto.setTheatreName("Grand Theatre");
        theatreRequestDto.setLocation("New York");
    }

    @Test
    void testCreateTheatre() {
        // Mock behavior
        when(mapper.toEntity(theatreRequestDto)).thenReturn(theatre);
        when(theatreRepository.save(theatre)).thenReturn(theatre);

        // Call the method
        Theatre createdTheatre = theatreService.createTheatre(theatreRequestDto);

        // Verify and assertions
        assertNotNull(createdTheatre);
        assertEquals("Grand Theatre", createdTheatre.getTheatreName());
        assertEquals("New York", createdTheatre.getLocation());

        verify(theatreRepository, times(1)).save(theatre);
        verify(theatreProducer, times(1)).sendTheatreEvent("CREATE", theatre);
    }

    @Test
    void testGetAllTheatres() {
        // Mock behavior
        List<Theatre> theatres = Arrays.asList(theatre);
        when(theatreRepository.findAll()).thenReturn(theatres);

        // Call the method
        List<Theatre> result = theatreService.getAllTheatres();

        // Verify and assertions
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Grand Theatre", result.get(0).getTheatreName());

        verify(theatreRepository, times(1)).findAll();
    }

    @Test
    void testUpdateTheatre_Success() {
        // Mock behavior
        when(theatreRepository.findById(1L)).thenReturn(Optional.of(theatre));
        when(theatreRepository.save(any(Theatre.class))).thenReturn(theatre);

        // Call the method
        Theatre updatedTheatre = theatreService.updateTheatre(1L, theatreRequestDto);

        // Verify and assertions
        assertNotNull(updatedTheatre);
        assertEquals("Grand Theatre", updatedTheatre.getTheatreName());

        verify(theatreRepository, times(1)).findById(1L);
        verify(theatreRepository, times(1)).save(any(Theatre.class));
    }

    @Test
    void testUpdateTheatre_NotFound() {
        // Mock behavior
        when(theatreRepository.findById(1L)).thenReturn(Optional.empty());

        // Expect exception
        Exception exception = assertThrows(RuntimeException.class, () -> {
            theatreService.updateTheatre(1L, theatreRequestDto);
        });

        assertEquals("Theatre not found", exception.getMessage());
        verify(theatreRepository, times(1)).findById(1L);
        verify(theatreRepository, never()).save(any(Theatre.class));
    }
}
