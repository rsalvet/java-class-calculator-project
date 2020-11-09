package com.JavaProject2.Calculators.Calculator1Tests;

import com.JavaProject2.Calculators.Calculate1.model.CalculationResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class Calculator1ApplicationTests {

	@Resource
	private TestRestTemplate testRestTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void testForMissingRequiredParameter(){
		ResponseEntity<String> entity =
				testRestTemplate.getForEntity("/calculator/calculate1", String.class);
		assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
		assertEquals("Invalid request, no parameters found.", entity.getBody());

	}

	@Test
	void testForInvalidParameters(){
		ResponseEntity<String> entity =
				testRestTemplate.getForEntity("/calculator/calculate1?input=a,b,c,d", String.class);
		assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
		assertEquals("Invalid parameters.", entity.getBody());
	}

	@Test
	void calculator1TestForMixedInputs() {
		ResponseEntity<CalculationResult> entity =
				testRestTemplate.getForEntity("/calculator/calculate1?input=1,2,5,12,11,9,17,-5, -1, -3, -2",
						CalculationResult.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertNotNull(entity.getBody());
		CalculationResult result = entity.getBody();
		assertEquals(17, result.getMax());
		assertEquals(-5, result.getMinOdds());
		List<Integer> integerList = Arrays.asList(-5,-3,-1,1,5,9,11,17);
		assertEquals(integerList, result.getOdds());
	}

	@Test
	void calculator1TestForOnlyOddNumbers(){
		ResponseEntity<CalculationResult> entity =
				testRestTemplate.getForEntity("/calculator/calculate1?input=-5,5,1,7,9,23,-23,-1,-43",
						CalculationResult.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertNotNull(entity.getBody());
		CalculationResult result = entity.getBody();
		assertEquals(23, result.getMax());
		assertEquals(-43, result.getMinOdds());
		List<Integer> integerList = Arrays.asList(-43, -23, -5, -1, 1, 5, 7, 9, 23);
		assertEquals(integerList, result.getOdds());
	}

	@Test
	void calculator1TestForOnlyEvenNumbers() {
		ResponseEntity<CalculationResult> entity =
				testRestTemplate.getForEntity("/calculator/calculate1?input=-4,6,2,8,10,24,-22,0,-42",
						CalculationResult.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertNotNull(entity.getBody());
		CalculationResult result = entity.getBody();
		assertEquals(24, result.getMax());
		assertEquals(null, result.getMinOdds());
		List<Integer> integerList = Arrays.asList();
		assertEquals(integerList, result.getOdds());
	}

	@Test
	void testForEmptyInput(){
		ResponseEntity<CalculationResult> entity =
				testRestTemplate.getForEntity("/calculator/calculate1?input=",
						CalculationResult.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertNotNull(entity.getBody());
		CalculationResult result = entity.getBody();
		assertEquals(null, result.getMax());
		assertEquals(null, result.getMinOdds());
		List<Integer> integerList = Arrays.asList();
		assertEquals(integerList, result.getOdds());
	}
}
