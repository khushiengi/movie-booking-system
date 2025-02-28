package com.example.theatre.mapper;

import org.mapstruct.Mapper;

import com.example.theatre.dto.TheatreRequestDto;
import com.example.theatre.entity.Theatre;

@Mapper(componentModel = "spring")
public interface TheatreRequestDtoToEntityMapper {

	Theatre toEntity(TheatreRequestDto theatreRequestDto);
	TheatreRequestDto toDto(Theatre theatre);
	
}
