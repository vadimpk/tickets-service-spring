package com.naukma.ticketsservice.train;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/wagon")
@RestController
public class WagonController {

    private final WagonService service;

    @Autowired
    public WagonController(WagonService service) {
        this.service = service;
    }

    @PostMapping
    public void addWagon(@RequestBody Wagon wagon){
        service.createWagon(wagon);
    }

    @GetMapping
    public List<Wagon> getWagons() {
        return service.getWagons();
    }
}
