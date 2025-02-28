package com.example.theatre.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowDto {
	
	private String movieName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
