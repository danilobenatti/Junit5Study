package br.com.junit5.concept;

import static br.com.junit5.paunch.domain.builder.UserBuilder.oneUser;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.junit5.paunch.service.repository.UserRepository;

@ExtendWith(value = MockitoExtension.class)
class CalculatorMockTest {
	
	@Spy
	private static Calculator calc;
	
	@Mock
	private UserRepository repository;
	
	@Test
	void test() {
		when(calc.sum(Mockito.anyInt(), Mockito.eq(2))).thenReturn(5)
				.thenReturn(6).thenCallRealMethod();
		when(repository.save(Mockito.any())).thenReturn(oneUser().now());
		System.out.println(calc.sum(1, 1));
		System.out.println(calc.sum(1, 2));
		System.out.println(calc.sum(10, 2));
		System.out.println(calc.sum(11, 2));
		System.out.println(calc.sum(117, 2));
		System.out.println(repository.save(null));
	}
}
