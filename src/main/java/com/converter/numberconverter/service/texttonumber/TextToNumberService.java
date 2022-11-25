package com.converter.numberconverter.service.texttonumber;

import java.util.Locale;

import com.converter.numberconverter.domain.CalculationNumber;
import com.converter.numberconverter.domain.CalculationText;

public interface TextToNumberService {
	
	public CalculationNumber getNumber(CalculationText text, Locale locale);

}
