package com.JavaProject2.Calculators.Calculate2.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Calculation2Result {
    private Integer max = null;
    private List<Integer> powerOf3s;
    private Double averageOfEvens;

    public Integer getMax() { return max; }
    public void setMax(Integer max) {
        this.max = max;
    }

    public List<Integer> getPowerOf3s() { return powerOf3s; }
    public void setPowerOf3s(List<Integer> p3s) {
        this.powerOf3s = p3s;
    }

    public Double getAverageOfEvens() { return averageOfEvens; }
    public void setAverageOfEvents(Double avgEvens) {
        this.averageOfEvens = avgEvens;
    }
}
