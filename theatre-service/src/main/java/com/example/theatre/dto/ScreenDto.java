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
public class ScreenDto {
	
	@JsonProperty("screenName")
	private String screenName;

	@JsonProperty("seats")
    private List<SeatDto> seats;

	@JsonProperty("shows")
    private List<ShowDto> shows;

}
