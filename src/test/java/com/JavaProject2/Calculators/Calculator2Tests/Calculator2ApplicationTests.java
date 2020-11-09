package com.JavaProject2.Calculators.Calculator2Tests;

import com.JavaProject2.Calculators.Calculate2.model.Calculation2Result;
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
public class Calculator2ApplicationTests {
    @Resource
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testForMissingRequiredParameter(){
        ResponseEntity<String> entity =
                testRestTemplate.getForEntity("/calculator/calculate2", String.class);
        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
        assertEquals("Invalid request, no parameters found.", entity.getBody());

    }

    @Test
    void testForInvalidParameters(){
        ResponseEntity<String> entity =
                testRestTemplate.getForEntity("/calculator/calculate2?input=a,c,<,a", String.class);
        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
        assertEquals("Invalid parameters.", entity.getBody());
    }

    @Test
    void calculator2TestForNormalInputs() {
        ResponseEntity<Calculation2Result> entity =
                testRestTemplate.getForEntity("/calculator/calculate2?input=1,2,3,4,5,6",
                        Calculation2Result.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody());
        Calculation2Result result = entity.getBody();
        assertEquals(6, result.getMax());
        assertEquals(4, result.getAverageOfEvens());

        assertEquals(Arrays.asList(1, 8, 27, 64, 125, 216), result.getPowerOf3s());
    }

    @Test
    void calculator2TestForEvenInputs() {
        ResponseEntity<Calculation2Result> entity =
                testRestTemplate.getForEntity("/calculator/calculate2?input=2,4,6,8,10",
                        Calculation2Result.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody());
        Calculation2Result result = entity.getBody();
        assertEquals(10, result.getMax());
        assertEquals(6.0, result.getAverageOfEvens());

        assertEquals(Arrays.asList(8, 64, 216, 512, 1000), result.getPowerOf3s());
    }

    @Test
    void calculator2TestForOddInputs() {
        ResponseEntity<Calculation2Result> entity =
                testRestTemplate.getForEntity("/calculator/calculate2?input=1,3,5,7,9",
                        Calculation2Result.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody());
        Calculation2Result result = entity.getBody();
        assertEquals(9, result.getMax());
        assertEquals(0.0, result.getAverageOfEvens());

        assertEquals(Arrays.asList(1, 27, 125, 343, 729), result.getPowerOf3s());
    }

    @Test
    void calculator2TestForMixedInputs() {
        ResponseEntity<Calculation2Result> entity =
                testRestTemplate.getForEntity("/calculator/calculate2?input=1,2,3,4,5,6,-4",
                        Calculation2Result.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody());
        Calculation2Result result = entity.getBody();
        assertEquals(6, result.getMax());
        assertEquals(2, result.getAverageOfEvens());

        assertEquals(Arrays.asList(1, 8, 27, 64, 125, 216, -64), result.getPowerOf3s());
    }

    @Test
    void testForEmptyInput(){
        ResponseEntity<Calculation2Result> entity =
                testRestTemplate.getForEntity("/calculator/calculate2?input=",
                        Calculation2Result.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody());
        Calculation2Result result = entity.getBody();
        assertEquals(null, result.getMax());
        assertEquals(Arrays.asList(), result.getPowerOf3s());
        assertEquals(0.0, result.getAverageOfEvens());
    }
}
