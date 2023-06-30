package br.com.junit.concept;

import static org.junit.jupiter.api.Assertions.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.Test;

import br.com.junit5.concept.Calculator;

class CalculatorTest {
	
	DecimalFormat df = new DecimalFormat();
	Locale loc = new Locale.Builder().setRegion("en").setLanguage("US").build();
	
	@Test
	void calculatorSum() {
		
		Calculator calc = new Calculator();
		assertEquals(1, calc.sum(0, 1));
		assertEquals(3, calc.sum(1, 2));
		assertEquals(5, calc.sum(2, 3));
		assertEquals(7, calc.sum(3, 4));
		assertEquals(11, calc.sum(5, 6));
		assertEquals(15, calc.sum(7, 8));
		assertEquals(19, calc.sum(9, 10));
		
	}
	
	@Test
	void assertionsExample() {
//		assertTrue("Casa".equals("Casa")); \\ use preferably 'assertEquals'
		assertEquals("Casa", String.format("%s", "Casa"));
		assertTrue("CaSa".equalsIgnoreCase("cAsA"));
		assertTrue("Casa".startsWith("Ca"));
		assertTrue("Casa".endsWith("sa"));
		assertFalse("Casa".contentEquals("casa"));
		
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		Set<String> set3 = null;
		
		assertEquals(set1, set2);
		assertSame(set1, set1);
		assertNotSame(set1, set2);
		
		set1.add("A");
		set2.add("B");
		
		assertNotEquals(set1, set2);
		assertNotEquals(set1, set3);
		assertNotNull(set1);
		assertNull(set3);
	}
	
	@Test
	void returnIntegerNumberFromDivision() {
		Calculator calc = new Calculator();
		float divide = calc.divide(6, 2);
		assertEquals(3.0, divide);
	}
	
	@Test
	void returnNegativeNumberFromDivision() {
		Calculator calc = new Calculator();
		float divide = calc.divide(6, -2);
		assertEquals(-3, divide);
	}
	
	@Test
	void returnDecimalNumberFromDivision() {
		Calculator calc = new Calculator();
		float divide = calc.divide(10, 3); // 3,3333333333333333333333333333333
		
		assertEquals(3.333, Math.round(divide * 1000.0) / 1000.0);
		
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(loc));
		df.setMaximumFractionDigits(4);
		assertEquals("3.3333", df.format(calc.divide(10, 3)));
		
		assertEquals(3.3333332538604736, divide);
		
		assertEquals(3.35, divide, 0.02);
	}
	
	@Test
	void returnZeroWithNumeratorZeroFromDivision() {
		Calculator calc = new Calculator();
		float divide = calc.divide(0, 3);
		assertEquals(0, divide);
	}
	
	@Test
	void returnDivisionByZero() {
		Calculator calc = new Calculator();
		float divide = calc.divide(3, 0);
		assertEquals(Double.POSITIVE_INFINITY, divide);
	}
	
}
