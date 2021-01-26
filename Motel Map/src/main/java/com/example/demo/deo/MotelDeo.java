package com.example.demo.deo;

import com.example.demo.model.Motel;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface MotelDeo {
    void insertMotel(int id, Motel motel, int x, int y);

    default void insertMotel(Motel motel) {
        int id = new Random().nextInt(1000);
        int x = new Random().nextInt(20);
        int y = new Random().nextInt(20);
        insertMotel(id, motel, x, y);
    }

    List<Motel> selectAllMotels();

    Optional<Motel> selectMotelById(int id);

    int deleteMotelById(int id);

    int updateMotelById(int id, Motel motel);

}
