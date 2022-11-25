package com.converter.numberconverter.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ConverterRestControllerCT {
	
	@Autowired
    private MockMvc mockMvc;
	
	protected HttpHeaders httpHeaders;
	
	@BeforeEach
	public void before() {
//		httpHeaders = new HttpHeaders()
	}

}
