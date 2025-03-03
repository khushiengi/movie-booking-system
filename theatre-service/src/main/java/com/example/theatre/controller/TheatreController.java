package com.example.theatre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.theatre.dto.TheatreRequestDto;
import com.example.theatre.entity.Theatre;
import com.example.theatre.service.TheatreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/theatres")
@Tag(name = "Theatre API", description = "Endpoints for managing theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping
    @Operation(
            summary = "Create a new theatre",
            description = "Adds a new theatre to the database.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Theatre details",
                required = true,
                content = @Content(schema = @Schema(implementation = TheatreRequestDto.class))
            ),
            responses = {
                @ApiResponse(responseCode = "201", description = "Theatre created successfully"),
                @ApiResponse(responseCode = "400", description = "Invalid request data")
            }
        )

    public Theatre createTheatre(@RequestBody TheatreRequestDto theatre) {
        return theatreService.createTheatre(theatre);
    }

    @GetMapping
    @Operation(
            summary = "Get all theatres",
            description = "Retrieves a list of all available theatres.",
            responses = {
                @ApiResponse(responseCode = "200", description = "List of theatres retrieved successfully"),
                @ApiResponse(responseCode = "404", description = "No theatres found")
            }
        )
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }
    
    @PutMapping("/{id}")
    @Operation(
            summary = "Update Theatre Details",
            description = "Updates the details of an existing theatre by its ID",
            responses = {
                @ApiResponse(responseCode = "200", description = "Theatre updated successfully"),
                @ApiResponse(responseCode = "404", description = "Theatre not found"),
                @ApiResponse(responseCode = "400", description = "Invalid request")
            }
        )
    public Theatre updateTheatre(@PathVariable Long id, @RequestBody @Schema(description = "Theatre details to be updated") TheatreRequestDto theatre) {
        return theatreService.updateTheatre(id, theatre);
    }
}
