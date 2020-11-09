package com.JavaProject2.Calculators.Calculate2.controllers;

import com.JavaProject2.Calculators.Calculate1.Service.Calculator1Service;
import com.JavaProject2.Calculators.Calculate1.controllers.Calculator1Controller;
import com.JavaProject2.Calculators.Calculate1.model.CalculationResult;
import com.JavaProject2.Calculators.Calculate2.model.Calculation2Result;
import com.JavaProject2.Calculators.Calculate2.services.Calculator2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/calculator")
public class Calculator2Controller {
    @Resource
    private Calculator2Service calculator2service;
    @Resource
    private Calculator1Service calculator1Service;

    Logger logger = LoggerFactory.getLogger(Calculator1Controller.class);

    @ExceptionHandler(MissingServletRequestParameterException.class)
    ResponseEntity<String> missingParametersBadRequest(){
        logger.warn("The server received an invalid request and responded with 400");
        return new ResponseEntity<>
                ("Invalid request, no parameters found.",
                        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    ResponseEntity<String> invalidParameterBadRequest() {
        logger.warn("The server received a request with invalid parameters and responded with 400");
        return new ResponseEntity<>
                ("Invalid parameters.",
                        HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/calculate2")
    @Cacheable(value = "calculation2result")
    public Calculation2Result performCalculate2(@RequestParam("input") List<Integer> list){
        Calculation2Result result = new Calculation2Result();

        result.setMax(calculator1Service.findMax(list));
        result.setPowerOf3s(calculator2service.powerOf3(list));
        result.setAverageOfEvents(calculator2service.averageOfEvens(list));

        logger.info("A calculation was performed successfully with input: " + list + "\n" +
                "The results were:\nmax: " + result.getMax() + "\npow of 3s: " + result.getPowerOf3s()
                + "\naverage of evens: " + result.getAverageOfEvens());

        return result;
    }
}
