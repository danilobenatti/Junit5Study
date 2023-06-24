package br.com.junit.concept;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.junit5.concept.Calculator;

class CalculatorTest {
	
	@Test
	void CalculatorSum() {
		
		Calculator calc = new Calculator();
		assertEquals(1, calc.sum(0, 1));
		assertEquals(3, calc.sum(1, 2));
		assertEquals(5, calc.sum(2, 3));
		assertEquals(7, calc.sum(3, 4));
		assertEquals(11, calc.sum(5, 6));
		assertEquals(15, calc.sum(7, 8));
		assertEquals(19, calc.sum(9, 10));
		
	}
	
}
