package com.JavaProject2.Calculators.Calculate1.model;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculationResult {

    private Integer max = null;
    private Integer minOdds = null;
    private List<Integer> odds;

    public CalculationResult(){
    }

    @Cacheable("max")
    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Cacheable("minOdds")
    public Integer getMinOdds() {
        return minOdds;
    }

    public void setMinOdds(Integer minOdds) {
        this.minOdds = minOdds;
    }

    @Cacheable("odds")
    public List<Integer> getOdds() {
        return odds;
    }

    public void setOdds(List<Integer> odds) {
        this.odds = odds;
    }
}
