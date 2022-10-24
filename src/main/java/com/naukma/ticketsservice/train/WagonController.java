package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.TicketsServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @GetMapping("/wagon/{id}")
    public Wagon show(@PathVariable String id){
        log.info("Get request for " + id);
        return service.findWagon(UUID.fromString(id));
    }

    @PostMapping("/wagon")
    public void addWagon(@RequestBody Wagon wagon){
        service.createWagon(wagon);
    }

    @PutMapping("/wagon/{id}")
    public Wagon update(@PathVariable String id, @RequestBody Map<String, String> body){
        Wagon wagon = new Wagon(Integer.parseInt(body.get("number_of_seats")));
        return service.update(wagon.getId(), wagon);
    }

    @DeleteMapping("wagon/{id}")
    public boolean delete(@PathVariable String id){
        UUID uuid = UUID.fromString(id);
        service.delete(uuid);
        return true;
    }


}
