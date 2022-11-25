package com.converter.numberconverter.service.texttonumber;

import java.util.Locale;

import org.springframework.stereotype.Service;

import com.converter.numberconverter.assembler.TextToNumberAssembler;
import com.converter.numberconverter.domain.CalculationNumber;
import com.converter.numberconverter.domain.CalculationText;

@Service
public class TextToNumberServiceImpl implements TextToNumberService {

	private final TextToNumberAssembler converter;

	private Integer firstNumber = 0;

	private Integer secondNumber = 0;

	public TextToNumberServiceImpl(TextToNumberAssembler converter) {
		this.converter = converter;
	}

	@Override
	public CalculationNumber getNumber(CalculationText calculationText, Locale locale) {

		firstNumber = converter.convertTextToNumber(calculationText.getFirstNumberText(), locale);
		secondNumber = converter.convertTextToNumber(calculationText.getSecondNumberText(), locale);
		
		return new CalculationNumber(firstNumber, secondNumber, calculationText.getCalculationType());
	}

}
