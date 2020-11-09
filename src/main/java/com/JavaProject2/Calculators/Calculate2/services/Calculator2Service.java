package com.JavaProject2.Calculators.Calculate2.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.lang.Math;

@Service
public class Calculator2Service {
    public List<Integer> powerOf3(List<Integer> integerList) {
        return integerList.stream()
                .map(v -> (int) Math.pow(v, 3))
                .collect(Collectors.toList());
    }

    public Double averageOfEvens(List<Integer> integerList) {
        Double avgEvenResult = integerList.stream()
                .filter(v -> v % 2 == 0)
                .mapToDouble(v -> v)
                .average()
                .orElse(Double.NaN);

        return (double)(Math.round(avgEvenResult * 100.0) / 100.0);
    }
}
