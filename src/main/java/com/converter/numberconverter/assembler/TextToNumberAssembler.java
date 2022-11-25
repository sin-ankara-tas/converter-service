package com.converter.numberconverter.assembler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class TextToNumberAssembler extends Assembler{
	
	private HashMap<String, Integer> numbersTextMap = new HashMap<String, Integer>();
	
	private HashMap<String, Integer> digitsTextMap = new HashMap<String, Integer>();
	
	private final MessageSource messageSource;
	
	public TextToNumberAssembler(MessageSource messageSource) {
		this.messageSource = messageSource;
		addingValuesToMaps();
	}
	
	private void addingValuesToMaps() {
		numbersTextMap.put("", 0 );
		numbersTextMap.put("one", 1);
		numbersTextMap.put("two", 2);
		numbersTextMap.put("three", 3);
		numbersTextMap.put("four", 4);
		numbersTextMap.put("five", 5);
		numbersTextMap.put("six", 6);
		numbersTextMap.put("seven", 7);
		numbersTextMap.put("eight", 8);
		numbersTextMap.put("nine", 9);
		numbersTextMap.put("ten", 10);
		numbersTextMap.put("twenty", 20);
		numbersTextMap.put("thirty", 30);
		numbersTextMap.put("forty", 40);
		numbersTextMap.put("fifty", 50);
		numbersTextMap.put("sixty", 60);
		numbersTextMap.put("seventy", 70);
		numbersTextMap.put("eighty", 80);
		numbersTextMap.put("ninety", 90);
		
		digitsTextMap.put("billion", BILLION);
		digitsTextMap.put("million", MILLION);
		digitsTextMap.put("thousand", THOUSAND);
		digitsTextMap.put("", ONE);
	}
		
	public Integer convertTextToNumber(String number, Locale locale) {
		
		try {
			List<String> splitList = Arrays.asList(number.split("\\s+"));
			List<String> tempTextList = new ArrayList<String>();
			
			Integer tempNumber = 0;
			Integer converted3DigitsNumber = 0;
			boolean isNegative = false;
			
			for(String numberText : splitList) {

				String text = messageSource.getMessage(numberText, null, locale);
				
				if(text == null) {
					return 0;
				}
				
				if(text.equals("negative")) {
					isNegative = true;
					continue;
				}
				
				if(digitsTextMap.containsKey(text)) {
					converted3DigitsNumber = get3DigitsNumber(tempTextList, locale);
					
					if(converted3DigitsNumber != 0) {
						tempNumber = tempNumber + converted3DigitsNumber * digitsTextMap.get(text);
					}
					
					tempTextList.clear();
				} else {
					tempTextList.add(text);
				}
			}
			
			converted3DigitsNumber = get3DigitsNumber(tempTextList, locale);
			tempNumber = tempNumber + converted3DigitsNumber;
			
			if(isNegative)
				tempNumber = tempNumber * -1;
			 
			return tempNumber;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	
	}

	private Integer get3DigitsNumber(List<String> tempList, Locale locale) {
		Integer tempNumber = 0;
		
		for(int i = 0; i < tempList.size(); i++) {
			if(tempList.get(i).equals("hundred")) {
				if(i == 0) {
					tempNumber = 100;
				} else {
					tempNumber = tempNumber * 100;
				}
			}
			
			if(numbersTextMap.get(tempList.get(i)) != null) {
				tempNumber = tempNumber + numbersTextMap.get(tempList.get(i));
			}
		}
		
		return tempNumber;
	}
}
