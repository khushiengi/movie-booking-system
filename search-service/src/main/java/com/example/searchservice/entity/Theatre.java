package com.example.searchservice.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "theatres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theatre {

	@Id
	private Long id;

	@Field(type = FieldType.Text)
    private String name;
    
	@Field(type = FieldType.Text)
    private String location;
	
	//private List<Screen> screens;

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
