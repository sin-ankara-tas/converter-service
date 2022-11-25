package com.converter.numberconverter.controller;

import java.util.Locale;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.converter.numberconverter.domain.CalculationNumber;
import com.converter.numberconverter.domain.CalculationText;
import com.converter.numberconverter.service.numbertotext.NumberToTextService;
import com.converter.numberconverter.service.texttonumber.TextToNumberService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/converter")
public class ConverterRestController {

	private final NumberToTextService numberToTextService;

	private final TextToNumberService texttoNumberService;

	public ConverterRestController(TextToNumberService texttoNumberService, NumberToTextService numberToTextService) {

		this.numberToTextService = numberToTextService;

		this.texttoNumberService = texttoNumberService;
	}

	@PostMapping(path = "/numbertotext", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CalculationText getText(@RequestBody CalculationNumber number,
			@RequestHeader(name = "Content-Language", required = false) Locale locale) {

		return numberToTextService.getText(number, locale);
	}

	@PostMapping(path = "/texttonumber", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CalculationNumber getNumber(@RequestBody CalculationText text,
			@RequestHeader(name = "Content-Language", required = false) Locale locale) {

		return texttoNumberService.getNumber(text, locale);
	}

	@GetMapping(path = "/test")
	public String getMerhaba() {
		return "Merhaba";
	}

}
