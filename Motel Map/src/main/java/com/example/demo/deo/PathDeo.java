package com.example.demo.deo;

import com.example.demo.model.Motel;

import java.util.List;

public interface PathDeo {
    Motel findFirstMotel();

    List<Motel> findMotelsInZone();
}
