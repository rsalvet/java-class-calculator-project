package com.JavaProject2.Calculators.Calculate1.controllers;

import com.JavaProject2.Calculators.Calculate1.Service.Calculator1Service;
import com.JavaProject2.Calculators.Calculate1.model.CalculationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/calculator/calculate1")
public class Calculator1Controller {

    @Resource
    private Calculator1Service calculator1service;
    @Autowired
    private CalculationResult calculationResult;

    Logger logger = LoggerFactory.getLogger(Calculator1Controller.class);

    @GetMapping()
    @Cacheable(value = "calculation1Result")
    public CalculationResult integers(@RequestParam List<Integer> input){
        calculationResult.setOdds(calculator1service.findOdds(input));
        calculationResult.setMinOdds(calculator1service.findMinOdds(input));
        calculationResult.setMax(calculator1service.findMax(input));
        logger.info("A calculation was performed successfully with input: " + input + "\n" +
                "The results were:\nmax: " + calculationResult.getMax() + "\nminOdds: " + calculationResult.getMinOdds()
                + "\nOdds: " + calculationResult.getOdds());
        return calculationResult;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    ResponseEntity<String> missingParametersBadRequest(){
        logger.warn("The server received an invalid request and responded with 400");
        return new ResponseEntity<>
                ("Invalid request, no parameters found.",
                        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    ResponseEntity<String> invalidParameterBadRequest(){
        logger.warn("The server received a request with invalid parameters and responded with 400");
        return new ResponseEntity<>
                ("Invalid parameters.",
                        HttpStatus.BAD_REQUEST);
    }


}
