package com.naukma.ticketsservice.controllers;

import com.naukma.ticketsservice.aspects.LogExecTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControllers {

    @GetMapping("/")
    @LogExecTime
    public String index() {
        return "index";
    }

    @GetMapping("/run")
    public String runIndex() {return "run/index"; }
}
