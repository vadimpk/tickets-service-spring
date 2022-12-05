package com.naukma.ticketsservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControllers {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/run")
    public String runIndex() {return "run/index"; }
}
