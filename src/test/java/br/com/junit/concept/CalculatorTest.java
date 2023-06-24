package br.com.junit.concept;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import br.com.junit5.concept.Calculator;

class CalculatorTest {
	
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
	
}
