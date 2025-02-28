package com.example.theatre.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private String theatreName;
    
	@JsonProperty("location")
    private String location;

	@JsonProperty("screens")
    private List<ScreenDto> screens;

}
