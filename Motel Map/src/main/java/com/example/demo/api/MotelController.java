package com.example.demo.api;

import com.example.demo.model.Motel;
import com.example.demo.service.MotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("api/v1/motels")
@RestController
public class MotelController {
    private final MotelService motelService;

    @Autowired
    public MotelController(MotelService motelService) {
        this.motelService = motelService;
    }

    @PostMapping
    public void addMotel(@Valid @NonNull @NotBlank @RequestBody Motel motel) {
        motelService.addMotel(motel);
    }

    @GetMapping
    public List<Motel> getAllMotels() {
        return motelService.getAllMotels();
    }

    @GetMapping(path = "{id}")
    public Motel selectMotelById(@PathVariable("id") int id) {
        return motelService.selectMotelById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public int deleteMotelById(@PathVariable("id") int id) {
        return motelService.deleteMotelById(id);
    }

    @PutMapping(path = "{id}")
    public int updateMotelById(@PathVariable("id") int id, @Valid @NonNull @RequestBody Motel motel) {
        return motelService.updateMotelById(id, motel);
    }

}
