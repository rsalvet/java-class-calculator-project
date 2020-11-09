package com.JavaProject2.Calculators.Calculate1.Service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class Calculator1Service {

    public Integer findMax(List<Integer> integerList){
        Integer max;

        try{
            max = integerList.stream().
                    mapToInt(v -> v).
                    max().orElseThrow(NoSuchElementException::new);
        }catch(NoSuchElementException e){
            max = null;
        }

        return max;
    }

    public Integer findMinOdds(List<Integer> integerList){
        Integer minOdds;
        try {
            minOdds = integerList.stream()
                    .mapToInt(v -> v)
                    .filter(num -> num % 2 != 0)
                    .min().orElseThrow(NoSuchElementException::new);
        }catch(NoSuchElementException e){
            minOdds = null;
        }
        return minOdds;

    }

    public List<Integer> findOdds(List<Integer> integerList){
        List<Integer> odds = integerList.stream()
                .filter(num -> num % 2 != 0)
                .sorted()
                .collect(Collectors.toList());
        return odds;
    }

}

