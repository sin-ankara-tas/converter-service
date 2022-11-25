package com.converter.numberconverter.service.numbertotext;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.converter.numberconverter.assembler.NumberToTextAssembler;
import com.converter.numberconverter.domain.CalculationNumber;
import com.converter.numberconverter.domain.CalculationText;

@Service
public class NumberToTextServiceImpl implements NumberToTextService {

	private NumberToTextAssembler assembler;
	
	public NumberToTextServiceImpl(MessageSource messageSource, NumberToTextAssembler assembler) {
		
		this.assembler = assembler;
	}

	@Override
	public CalculationText getText(CalculationNumber calculationNumber, Locale locale) {
		
		String firstNumberText =  assembler.convertNumberToText(calculationNumber.getFirstNumber(), locale);
		String secondNumberText =  assembler.convertNumberToText(calculationNumber.getSecondNumber(), locale);
		
		return new CalculationText(firstNumberText, secondNumberText, calculationNumber.getCalculationType());
	}
}





      