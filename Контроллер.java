package com.gusdecool.calculator;


import org.intellij.lang.annotations.MagicConstant;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Calculator {

	private ArrayList<Data> sequence = new ArrayList<>();

	public void addData(String buttonText) {
		int type = findDataType(buttonText);
		sequence.add(new Data(buttonText, type));
	}

	public ArrayList<Data> getSequence() {
		return sequence;
	}

	public void reset() {
		sequence.clear();
	}

	@Data.DataType
	public int findDataType(String buttonText) {
		int returnValue;
		switch (buttonText) {
			case "+":
			case "-":
			case "*":
			case "/":
				returnValue = Data.TYPE_OPERATOR;
				break;

			case "=":
			case "CE":
			case "C":
				returnValue = Data.TYPE_COMMAND;
				break;

			default:
				returnValue = Data.TYPE_INPUT;
				break;
		}

		return returnValue;
	}

	public String calculateResult() {
		BigDecimal firstValue = new BigDecimal(sequence.get(sequence.size() - 3).getValue());
		String operator = sequence.get(sequence.size() - 2).getValue();
		BigDecimal secondValue = new BigDecimal(sequence.get(sequence.size() - 1).getValue());

		BigDecimal result = firstValue;
		switch (operator) {
			case "+":
				result = firstValue.add(secondValue);
				break;
			case "-":
				result = firstValue.subtract(secondValue);
				break;
			case "*":
				result = firstValue.multiply(secondValue);
				break;
			case "/":
				result = firstValue.divide(secondValue);
				break;
		}

		addData(result.toString());

		return getLastInput();
	}

	public String getLastInput() {
		return sequence.get(sequence.size() - 1).getValue();
	}

	public void resetLastInput() {
		editLatestInput("0");
	}

	public void editLatestInput(String text) {
		int latestIndex = sequence.size() -1;
		Data data = sequence.get(latestIndex);
		data.setValue(text);
		sequence.set(latestIndex, data);
	}


	public static class Data {

		@MagicConstant(intValues = {TYPE_OPERATOR, TYPE_INPUT, TYPE_COMMAND})
		@interface DataType {
		}

		public static final int TYPE_OPERATOR = 1;
		public static final int TYPE_INPUT = 2;
		public static final int TYPE_COMMAND = 3;

		private String value;
		private int type;

		/**
		 * @param value Data value
		 * @param type  Data type
		 */
		public Data(String value, @DataType int type) {
			this.value = value;
			this.type = type;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public int getType() {
			return type;
		}
	}
}
