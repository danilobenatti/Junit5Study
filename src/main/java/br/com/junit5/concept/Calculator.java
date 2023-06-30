package br.com.junit5.concept;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Calculator {
	
	protected static Logger logger = LogManager.getLogger();
	
	private static final String MSG = "Result: %s";
	
	public int sum(int a, int b) {
		return a + b;
	}
	
	public float divide(int a, int b) {
		return (float) a / b;
	}
	
	public static void main(String[] args) {
		
		Configurator.initialize(Calculator.class.toString(),
			"./src/main/resources/log4j2.properties");
		
		Calculator calc = new Calculator();
		
		logger.info(() -> String.format(MSG, calc.sum(0, 1)));
		logger.info(() -> String.format(MSG, calc.sum(1, 2)));
		logger.info(() -> String.format(MSG, calc.sum(2, 3)));
		logger.info(() -> String.format(MSG, calc.sum(3, 4) == 7)); // <- test
		logger.info(() -> String.format(MSG, calc.sum(5, 6) == 11)); // <- test
		logger.info(() -> String.format(MSG, calc.sum(7, 8) == 14)); // <- test
		logger.info(() -> String.format(MSG, calc.sum(9, 10) == 19)); // <- test
		
	}
}
