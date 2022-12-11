package com.naukma.ticketsservice.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class RunWebController {

    private final RunService runService;

    @Autowired
    public RunWebController(RunService runService) {
        this.runService = runService;
    }

    @GetMapping("/admin/runs")
    public String index(Model model) {
        List<Run> runs = runService.getRuns();
        model.addAttribute("runs", runs);
        model.addAttribute("newRun",new RunDto());
        return "run/runs";
    }

    @GetMapping("/admin/runs/create")
    public String create(@Valid @ModelAttribute("newRun") RunDto run, BindingResult result,
            Model model) {
        if(result.hasErrors()){
            return "redirect:/?failedCreation&error=bad_request";
        }
        Run created = runService.create(new Run(run.getName(),
                runService.findRouteById(run.getRouteId()).get(),
                runService.findTrainById(run.getTrainId()).get(),
                run.getDepartureTime(), run.getArrivalTime(),
                run.getDepartureDate(), run.getArrivalDate()));
        if(created == null) {
            result.rejectValue("name", null,"Name is taken");
            return "redirect:?failedCreation&error=name_is_taken";
        }
        return "redirect:/admin/runs";
    }

//    @PostMapping("/run")
//    public String registration(@Valid @ModelAttribute("run") RunDto runDto,
//                               BindingResult result,
//                               Model model) {
//        Optional<Run> existingRun = runService.findRunByName(runDto.getName());
//
//        if (existingRun.isPresent()) {
//            result.rejectValue("name", null,
//                    "Name is not unique");
//        }
//
//        if (result.hasErrors()) {
//            model.addAttribute("run", runDto);
//            return "/run/create";
//        }
//
//        Run run = new Run(runDto.getName(), runService.findRouteById(runDto.getRouteId()).get(), runService.findTrainById(runDto.getTrainId()).get(), runDto.getDepartureTime(), runDto.getArrivalTime(),runDto.getDepartureDate(),runDto.getArrivalDate());
//        runService.createRun(run);
//        return "redirect:/run/create?success";
//    }

//    @GetMapping("/run/update/{name}")
//    public String update(Model model, @PathVariable String name) {
//        Optional<Run> run = runService.findRunByName(name);
//        if (run.isEmpty()) throw new RuntimeException("not found run with name=" + name);
//
//        model.addAttribute("run", run);
//        return "/run/update";
//    }

    @PostMapping("/admin/runs/update/{id}")
    public String update(@Valid @ModelAttribute("newRun") RunDto run,
                         @PathVariable Long id,
                         BindingResult result,
                         Model model) {

        if(result.hasErrors())
            return "redirect:/admin/runs?failedUpdate&error=bad_request";

        Run updated = runService.update(id,run);

        if(updated == null) {
            return "redirect:/admin/runs?failedUpdate&error=bad_request";
        }

        return "redirect:/admin/runs";
    }

    @GetMapping("/admin/run/delete/{name}")
    public String delete(@PathVariable Long id, Model model) {
        Optional<Run> run = runService.find(id);
        if (run.isPresent()) {
            if(runService.delete(id)) {
                return "redirect:/admin/run";
            }
            return "redirect:/admin/run?failedDeletion&error=delete_run";
        }
        return "redirect:/admin/run?failedDeletion&error=no_such_run";
    }

}
