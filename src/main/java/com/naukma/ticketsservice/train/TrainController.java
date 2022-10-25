package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.TicketsServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("api/v1")
@RestController
public class TrainController {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    private final TrainService service;

    @Autowired
    public TrainController(TrainService service) {
        this.service = service;
    }

    @GetMapping("/train/")
    public List<Train> index(){
        return service.getTrains();
    }

    @GetMapping(name = "/train/{id}")
    public Train show(@PathVariable String id){
        log.info("show id = " + id);

        return service.findTrain(UUID.fromString(id));
    }

    @PostMapping(value = "/train/")
    public void addWagon(@RequestBody Train train){
        log.info(train.toString());
//        service.createTrain(train);
    }


//    @PutMapping("/wagon/{name}")
//    public Wagon update(@PathVariable String name, @RequestBody Map<String, String> body){
//        Wagon wagon = new Wagon(body.get("name"), Integer.parseInt(body.get("number_of_seats")));
//        return service.update(wagon.getId(), wagon);
//    }
//
//    @DeleteMapping("wagon/{name}")
//    public int delete(@PathVariable String name){
//        return service.delete(name);
//    }
//

}
