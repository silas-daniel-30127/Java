package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Motel {
    private final int id;
    @NotBlank
    private final String name;
    private final int pos_x;
    private final int pos_y;

    public Motel(@JsonProperty("id") int id,
                 @JsonProperty("name") String name,
                 @JsonProperty("pos_x") int pos_x,
                 @JsonProperty("pos_y") int pos_y) {
        this.id = id;
        this.name = name;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    @Override
    public String toString() {
        return "Motel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pos_x=" + pos_x +
                ", pos_y=" + pos_y +
                '}';
    }
}
