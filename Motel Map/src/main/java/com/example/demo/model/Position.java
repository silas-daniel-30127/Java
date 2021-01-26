package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Position {
    private int x;
    private int y;
    private int range;

    public Position(@JsonProperty("x") int x,
                    @JsonProperty("y") int y,
                    @JsonProperty("range") int range) {
        this.x = x;
        this.y = y;
        this.range = range;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
