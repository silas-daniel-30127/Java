package com.example.demo.api;

import com.example.demo.deo.Path;
import com.example.demo.model.Motel;
import com.example.demo.model.Position;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("api/v1/path")
@RestController
public class PositionController {
    private final Path path = new Path();

    public PositionController() {
    }


    @GetMapping(path = "/findFirst")
    public Motel findFirstMotel(@Valid @NonNull @NotBlank @RequestBody Position position) {
        path.setCurrent_x(position.getX());
        path.setCurrent_y(position.getY());
        path.setRange(position.getRange());
        return path.findFirstMotel();
    }

    @GetMapping(path = "findAll")
    public List<Motel> findAll(@Valid @NonNull @NotBlank @RequestBody Position position) {
        path.setCurrent_x(position.getX());
        path.setCurrent_y(position.getY());
        path.setRange(position.getRange());
        return path.findMotelsInZone();
    }
}

