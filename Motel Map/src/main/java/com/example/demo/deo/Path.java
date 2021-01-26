package com.example.demo.deo;

import com.example.demo.model.Motel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class Path implements PathDeo {
    private int current_x = 0;
    private int current_y = 0;
    private final List<Motel> motels;
    private int range = 0;

    public Path(@JsonProperty("x") int x,
                @JsonProperty("y") int y,
                @JsonProperty("range") int range) {
        this.current_x = x;
        this.current_y = y;
        this.motels = new MotelBeam().selectAllMotels();
        this.range = range;
    }

    public Path() {
        motels = new MotelBeam().selectAllMotels();
    }

    public Motel findFirstMotel() {
        double min_distance = range * range;
        Motel chosenMotel = null;
        for (Motel motel : motels) {
            double dist = Math.sqrt((current_x - motel.getPos_x()) * (current_x - motel.getPos_x()) +
                    (current_y - motel.getPos_y()) * (current_y - motel.getPos_y()));
            if (dist < min_distance && dist <= range) {
                min_distance = dist;
                chosenMotel = motel;
            }
        }
        return chosenMotel;
    }

    public List<Motel> findMotelsInZone() {
//        List<Motel> motelList = new ArrayList<>();
//        for (Motel motel:motels) {
//            double dist = Math.sqrt((x - motel.getPos_x()) * (x - motel.getPos_x()) +
//                    (y - motel.getPos_y()) * (y - motel.getPos_y()));
//            if(dist <= range) {
//                motelList.add(motel);
//            }
//        }
        return motels.stream().filter(motel -> Math.sqrt((current_x - motel.getPos_x()) * (current_x - motel.getPos_x()) +
                (current_y - motel.getPos_y()) * (current_y - motel.getPos_y())) <= range).collect(Collectors.toList());
    }

    public int getCurrent_x() {
        return current_x;
    }

    public void setCurrent_x(int current_x) {
        this.current_x = current_x;
    }

    public int getCurrent_y() {
        return current_y;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setCurrent_y(int current_y) {
        this.current_y = current_y;

    }
}
