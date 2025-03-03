package com.example.theatre.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheatreRequestDto {

	@JsonProperty("theatreName")
	@Schema(description = "Theatre name", example = "IMAX Theatre")
    private String theatreName;
    
	@JsonProperty("location")
	@Schema(description = "Theatre location", example = "New York")
    private String location;

	@JsonProperty("screens")
    private List<ScreenDto> screens;

}
