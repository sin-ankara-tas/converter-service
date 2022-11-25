package com.converter.numberconverter.domain;

public class CalculationNumber {
	
    private Integer firstNumber;
	
    private Integer secondNumber;
    
    private CalculationType calculationType;
    
    public CalculationNumber() {
		// TODO Auto-generated constructor stub
	}
    
    //TODO: Null gelebilir
	public CalculationNumber( Integer firstNumber, Integer secondNumber, CalculationType calculationType) {
		super();
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.calculationType = calculationType;
	}

	public Integer getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(Integer firstNumber) {
		this.firstNumber = firstNumber;
	}

	public Integer getSecondNumber() {
		return secondNumber;
	}

	public void setSecondNumber(Integer secondNumber) {
		this.secondNumber = secondNumber;
	}

	public CalculationType getCalculationType() {
		return calculationType;
	}

	public void setCalculationType(CalculationType calculationType) {
		this.calculationType = calculationType;
	}

//	//TODO
//	public boolean isValidCalculation() {
//		
//		if (this == null || this.calculationType == null || this.firstNumber == null
//				|| this.secondNumber == null) {
//			return false;
//		}
//
//		return true;
//	}
}