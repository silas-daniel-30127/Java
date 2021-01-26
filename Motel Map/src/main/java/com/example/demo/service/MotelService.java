package com.example.demo.service;

import com.example.demo.deo.MotelDeo;
import com.example.demo.model.Motel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotelService {
    private final MotelDeo motelDeo;

    @Autowired
    public MotelService(@Qualifier("mySQLMotel") MotelDeo motelDeo) {
        this.motelDeo = motelDeo;
    }

    public void addMotel(Motel motel) {
        motelDeo.insertMotel(motel);
    }

    public List<Motel> getAllMotels() {
        return motelDeo.selectAllMotels();
    }

    public Optional<Motel> selectMotelById(int id) {
        return motelDeo.selectMotelById(id);
    }

    public int deleteMotelById(int id) {
        return motelDeo.deleteMotelById(id);
    }

    public int updateMotelById(int id, Motel motel) {
        return motelDeo.updateMotelById(id, motel);
    }
}
