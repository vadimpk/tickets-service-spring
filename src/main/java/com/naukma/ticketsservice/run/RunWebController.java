package com.naukma.ticketsservice.run;

import com.naukma.ticketsservice.aspects.LogInAndOutArgs;
import com.naukma.ticketsservice.route.RouteService;
import com.naukma.ticketsservice.train.TrainService;
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
    private final RouteService routeService;
    private final TrainService trainService;

    @Autowired
    public RunWebController(RunService runService, RouteService routeService, TrainService trainService) {
        this.runService = runService;
        this.routeService = routeService;
        this.trainService = trainService;
    }

    @GetMapping("/admin/runs")
    public String index(Model model) {
        List<Run> runs = runService.getRuns();
        model.addAttribute("runs", runs);
        model.addAttribute("newRun",new RunDto());
        model.addAttribute("routes", routeService.getRoutes());
        model.addAttribute("trains", trainService.getTrains());
        return "run/runs";
    }

    @PostMapping("/admin/runs/create")
    @LogInAndOutArgs
    public String create(@Valid @ModelAttribute("newRun") RunDto run, BindingResult result,
            Model model) {

        if(result.hasErrors()){
            return "redirect:?failedCreation&error=bad_request";
        }

        Run created = runService.create(run);
        if(created == null) {
            result.rejectValue("name", null,"bad request");
            return "redirect:?failedCreation&error=bad_request";
        }
        return "redirect:/admin/runs";
    }

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

    @PostMapping("/admin/runs/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        Optional<Run> run = runService.find(id);
        if (run.isPresent()) {
            if(runService.delete(id)) {
                return "redirect:/admin/runs";
            }
            return "redirect:/admin/runs?failedDeletion&error=failed_to_delete";
        }
        return "redirect:/admin/runs?failedDeletion&error=no_such_run";
    }
}
