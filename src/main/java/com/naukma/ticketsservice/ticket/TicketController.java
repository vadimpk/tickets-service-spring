package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.TicketsServiceApplication;
import com.naukma.ticketsservice.run.Run;
import com.naukma.ticketsservice.run.RunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1")
@RestController
public class TicketController {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    private final TicketService service;
    private final RunService runService;

    @Autowired
    public TicketController(TicketService service, RunService runService) {
        this.service = service;
        this.runService = runService;
    }

    @GetMapping("/ticket/{runId}")
    public ResponseEntity<List<Ticket>> index(@PathVariable String runId){
        log.info("Showing tickets by run id = " + runId);

        Optional<Run> run = runService.findRun(Long.valueOf(runId));
        if (run.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.findTicketsByRun(run.get()), HttpStatus.OK);
    }

    @PostMapping("/ticket")
    public ResponseEntity<HttpStatus> add(@RequestBody TicketDto ticketDto){
        // run if such run is present
        Optional<Run> run = runService.findRun(ticketDto.getRunId());
        if (run.isEmpty()) {
            throw new NoSuchRunException();
        }

        service.createTicket(run.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("ticket/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        Optional<Ticket> w = service.findTicketById(id);
        if (w.isPresent()) {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        throw new NoSuchRunException();
    }

    @ExceptionHandler(value = NoSuchRunException.class)
    public ResponseEntity<String> exception(NoSuchRunException exception) {
        return new ResponseEntity<>("Not found such run", HttpStatus.NOT_FOUND);
    }
}