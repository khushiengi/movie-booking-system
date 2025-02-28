package com.example.theatre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.theatre.entity.Screen;
import com.example.theatre.repository.ScreenRepository;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    public Screen createScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }
}
