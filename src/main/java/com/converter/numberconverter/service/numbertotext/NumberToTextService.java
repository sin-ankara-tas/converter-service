package com.converter.numberconverter.service.numbertotext;

import java.util.Locale;

import com.converter.numberconverter.domain.CalculationNumber;
import com.converter.numberconverter.domain.CalculationText;

public interface NumberToTextService {

	public CalculationText getText(CalculationNumber number, Locale locale);

}
