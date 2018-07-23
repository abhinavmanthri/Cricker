package com.example.abhinav.cricker;

/**
 * Created by Abhinav on 21-02-2018.
 */

public class BatsmenBean {
    private String name;
    private int runs;
    private int balls;
    private int fours;
    private int sixes;
    private double strikeRate;

    public BatsmenBean(String name, int runs, int balls, int fours, int sixes, double strikeRate) {
        this.name = name;
        this.runs = runs;
        this.balls = balls;
        this.fours = fours;
        this.sixes = sixes;
        this.strikeRate = strikeRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }
}
