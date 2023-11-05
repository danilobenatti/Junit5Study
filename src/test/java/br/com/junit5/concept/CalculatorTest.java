package br.com.junit5.concept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorTest {
	
	DecimalFormat df = new DecimalFormat();
	Locale loc = new Locale.Builder().setRegion("en").setLanguage("US").build();
	
	private static Calculator calc;
	
	private static int count = 0; // add "static" for hold value
	
	protected static Logger log = LogManager.getLogger();
	
	@BeforeAll
	static void setupAll() {
		log.info("Start Before All, Hello everybody!");
		calc = new Calculator();
	}
	
	@AfterAll
	static void tearDownAll() {
		log.info("End After All, Goodbye!");
	}
	
	@BeforeEach
	void setup() {
		Configurator.initialize(CalculatorTest.class.getName(),
			"./src/main/resources/log4j2.properties");
		log.info(String.format("Start Test_: %d", ++count));
	}
	
	@AfterEach
	void tearDown() {
		log.info(String.format("End Test_: %d", count));
	}
	
	@Test
	void calculatorSum() {
		log.info(String.format("Count: %d", count));
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
		log.info(String.format("Count: %d", count));
		// assertTrue("House".equals("House")); \\ use preferably 'assertEquals'
		assertEquals("House", String.format("%s", "House"));
		assertTrue("HOUSE".equalsIgnoreCase("house"));
		assertTrue("House".startsWith("Ho"));
		assertTrue("House".endsWith("se"));
		assertFalse("House".contentEquals("house"));
		
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
		log.info(String.format("Count: %d", count));
		float divide = calc.divide(6, 2);
		assertEquals(3.0, divide);
	}
	
	@Test
	void returnNegativeNumberFromDivision() {
		log.info(String.format("Count: %d", count));
		float divide = calc.divide(6, -2);
		assertEquals(-3, divide);
	}
	
	@Test
	void returnDecimalNumberFromDivision() {
		log.info(String.format("Count: %d", count));
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
		log.info(String.format("Count: %d", count));
		float divide = calc.divide(0, 3);
		assertEquals(0, divide);
	}
	
	@Test
	void returnDivisionByZero() {
		log.info(String.format("Count: %d", count));
		float divide = calc.divide(3, 0);
		assertEquals(Double.POSITIVE_INFINITY, divide);
	}
	
	@SuppressWarnings("unused")
	@Test
	void throwExceptionWhenDivideByZeroWithJUnit4() {
		log.info(String.format("Count: %d", count));
		try {
			float divide = 10 / 0;
			Assertions.fail("Should show an exception on division 10 / 10");
		} catch (ArithmeticException ex) {
			assertEquals("/ by zero", ex.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	void throwExceptionWhenDivideByZeroWithJUnit5() {
		log.info(String.format("Count: %d", count));
		ArithmeticException ex = assertThrows(ArithmeticException.class, () -> {
			float divide = 10 / 0;
		});
		assertEquals("/ by zero", ex.getMessage());
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "Test1", "Test2", "Test3" })
	void testStrings(String param) {
		System.out.println(param);
		assertNotNull(param);
	}
	
	@ParameterizedTest
	@CsvSource(delimiter = ';', value = { "6; 2; 3", "6; -2; -3",
		"10; 3; 3.3333332538604736", "0; 2; 0" })
	void divisionTestParameterized(int num, int den, double result) {
		float divide = calc.divide(num, den);
		assertEquals(result, divide);
	}
	
}
