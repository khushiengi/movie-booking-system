package com.example.theatre.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.theatre.dto.TheatreRequestDto;
import com.example.theatre.entity.Theatre;
import com.example.theatre.mapper.TheatreRequestDtoToEntityMapper;
import com.example.theatre.repository.TheatreRepository;

@Service
public class TheatreService {

    private TheatreRepository theatreRepository;
    
    private TheatreRequestDtoToEntityMapper mapper;

    public TheatreService(TheatreRepository theatreRepository, TheatreRequestDtoToEntityMapper mapper) {
		this.theatreRepository = theatreRepository;
		this.mapper = mapper;
	}

	public Theatre createTheatre(TheatreRequestDto theatre) {
    	
		/*
		 * Theatre theatreEntity = Theatre.builder() .theatreName(resolve(() ->
		 * theatre.getTheatreName()).orElse("")) .location(theatre.getLocation())
		 * .build();
		 */
    	Theatre theatreEntity = mapper.toEntity(theatre);
        return theatreRepository.save(theatreEntity);
    }

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }
    
    public static <T> Optional<T> resolve(Supplier<T> resolver){
    	try {
    		return Optional.ofNullable(resolver.get());
    	} catch (Exception e) {
			return Optional.empty();
		}
    }
}
