package com.naukma.ticketsservice.controllers;

import com.naukma.ticketsservice.aspects.LogInAndOutArgs;
import com.naukma.ticketsservice.run.RunService;
import com.naukma.ticketsservice.run.SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;

@Controller
public class SearchController {

    private final RunService runService;

    @Autowired
    public SearchController(RunService runService) {
        this.runService = runService;
    }

    @GetMapping("/searchRuns")
    @LogInAndOutArgs
    public String searchForRuns(@Valid @ModelAttribute("searchArgs")SearchDto searchDto,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "redirect:?failedSearch&error=bad_request";
        }

        System.out.println(searchDto.getDepartureDate());
        model.addAttribute("runs", runService.getByDepartureDate(searchDto.getDepartureDate()));

        return "index";
    }
}
