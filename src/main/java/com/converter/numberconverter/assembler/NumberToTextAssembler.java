package com.converter.numberconverter.assembler;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class NumberToTextAssembler extends Assembler {
	
	private HashMap<Integer, String> numbersTextMap = new HashMap<Integer, String>();
	
	private HashMap<Integer, String> digitsTextMap = new HashMap<Integer, String>();
	
	private Integer number = 0;
	
	private Locale locale;

	@Autowired
	private final MessageSource messageSource;
	
	public NumberToTextAssembler(MessageSource messageSource) {
		
		this.messageSource =  messageSource;
		
		addingValuesToMaps();
	}

	private void addingValuesToMaps() {
		numbersTextMap.put(0, "zero");
		numbersTextMap.put(1, "one");
		numbersTextMap.put(2, "two");
		numbersTextMap.put(3, "three");
		numbersTextMap.put(4, "four");
		numbersTextMap.put(5, "five");
		numbersTextMap.put(6, "six");
		numbersTextMap.put(7, "seven");
		numbersTextMap.put(8, "eight");
		numbersTextMap.put(9, "nine");
		numbersTextMap.put(10, "ten");
		numbersTextMap.put(20, "twenty");
		numbersTextMap.put(30, "thirty");
		numbersTextMap.put(40, "forty");
		numbersTextMap.put(50, "fifty");
		numbersTextMap.put(60, "sixty");
		numbersTextMap.put(70, "seventy");
		numbersTextMap.put(80, "eighty");
		numbersTextMap.put(90, "ninety");
		
		digitsTextMap.put(BILLION, "billion");
		digitsTextMap.put(MILLION, "million");
		digitsTextMap.put(THOUSAND, "thousand");
		digitsTextMap.put(ONE, "ones");
	}
	
	public String convertNumberToText(Integer number, Locale locale) {
		
		this.locale = locale;
		
		this.number = number;
		
		boolean isNegative = false;
		
		if(number < 0) {
			this.number = number * -1;
			isNegative = true;
		}
		
		if(numbersTextMap.containsKey(number)) {
			String i18nText = messageSource.getMessage(numbersTextMap.get(number), null, locale);
			return addMinusText(isNegative) + i18nText;
		}
		
		String convertedNumberText = getDigitsText(BILLION) + getDigitsText(MILLION)
				+ getDigitsText(THOUSAND) + getDigitsText(ONE);
		
		return addMinusText(isNegative) + convertedNumberText;
	}
	
	private String addMinusText(boolean isNegative) {
		if(isNegative) {
			 return messageSource.getMessage("negative", null, locale);
		}
		return "";
	}
	
	private String getDigitsText(Integer digit) {
		
		Integer part = number / digit;
		
		String numberText = "";
		
		if(part != 0) {
			numberText = convert3DigitsNumber(part);
				
			String i18nText = messageSource.getMessage(digitsTextMap.get(digit), null, locale);
			numberText = numberText + " " + i18nText;
			
			number = number - digit * part;
			numberText = numberText + " ";
			return numberText;
		}
		
		return numberText;
	}

	private String convert3DigitsNumber(Integer part) {
	
		String threeDigitsNumberText = getHundredsDigitText(part) + getTensDigitText(part) + getOnesDigitText(part);
				
		return threeDigitsNumberText;
	}
	
	private String getOnesDigitText(Integer part) {
		Integer onesDigits = part % 10;
		
		if(onesDigits != 0 && onesDigits != 1) {
			String i18nText = messageSource.getMessage(numbersTextMap.get(onesDigits), null, locale);
			return " " + i18nText;
		}
		return "";
	}

	private String getTensDigitText(Integer part) {
		Integer tensDigit = (part % 100) / 10;
		
		if(tensDigit != 0) {
			String i18nText = messageSource.getMessage(numbersTextMap.get(tensDigit * 10), null, locale);
			return " " + i18nText;
		}
		
		return "";
	}

	private String getHundredsDigitText(Integer part) {

		Integer hundredsDigit = part / 100;
		
		String hundred = messageSource.getMessage("hundred", null, locale);
		
		if(hundredsDigit != 0) {
			if(hundredsDigit == 1) {
				return hundred;
			} else {
				String i18nText = messageSource.getMessage(numbersTextMap.get(hundredsDigit), null, locale);
				return i18nText + " " + hundred;
			}
		}
		
		return "";
	}
}
