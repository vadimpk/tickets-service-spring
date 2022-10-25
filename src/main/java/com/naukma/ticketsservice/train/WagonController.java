package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.TicketsServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("api/v1")
@RestController
public class WagonController {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    private final WagonService service;

    @Autowired
    public WagonController(WagonService service) {
        this.service = service;
    }

    @GetMapping("/wagon")
    public List<Wagon> wagon(){
        return service.getWagons();
    }

    @GetMapping("/wagon/{name}")
    public Wagon show(@PathVariable String name){
        log.info(name);

        return service.findWagon(name);
    }

    @PostMapping("/wagon")
    public void addWagon(@RequestBody Wagon wagon){
        service.createWagon(wagon);
    }

    @PutMapping("/wagon/{name}")
    public Wagon update(@PathVariable String name, @RequestBody Map<String, String> body){
        Wagon wagon = new Wagon(body.get("name"), Integer.parseInt(body.get("number_of_seats")));
        return service.update(wagon.getId(), wagon);
    }

    @DeleteMapping("wagon/{name}")
    public int delete(@PathVariable String name){
        return service.delete(name);
    }


}
