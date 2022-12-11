package com.naukma.ticketsservice.train;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
public class TrainWebController {

    private final TrainService trainService;

    @Autowired
    public TrainWebController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("/admin/trains")
    public String index(Model model) {
        List<Train> trains = trainService.getTrains();
        model.addAttribute("trains", trains);
        model.addAttribute("newTrain", new TrainDto());
        return "train/trains";
    }

    @PostMapping("/admin/trains/create")
    public String createTrainFromAdminPanel(@Valid @ModelAttribute("newTrain") TrainDto train,
                                              BindingResult result,
                                              Model model) {

        if (result.hasErrors()) {
            return "redirect:?failedCreation&error=bad_request";
        }

        Train created = trainService.create(new Train(train.getName(), train.getSpeed(), train.getCapacity()));
        if (created == null) {
            result.rejectValue("name", null,
                    "Name is taken");
            return "redirect:?failedCreation&error=name_is_taken";
        }
        return "redirect:/admin/trains";
    }


    @PostMapping("/admin/trains/update/{id}")
    public String updateTrainFromAdminPanel(@Valid @ModelAttribute("newTrain") TrainDto train,
                                              @PathVariable Long id,
                                              BindingResult result,
                                              Model model) {

        if (result.hasErrors())
            return "redirect:/admin/trains?failedUpdate&error=bad_request";


        Train updated = trainService.update(id, train);
        if (updated == null) {
            return "redirect:/admin/trains?failedUpdate&error=bad_request";
        }

        return "redirect:/admin/trains";
    }

    @PostMapping("/admin/trains/delete/{id}")
    public String deleteTrainFromAdminPanel(@PathVariable Long id, Model model) {
        Optional<Train> train = trainService.find(id);
        if (train.isPresent()) {
            if (trainService.delete(id))
                return "redirect:/admin/trains";
            return "redirect:/admin/trains?failedDeletion&error=delete_runs_with_such_train_first";
        }
        return "redirect:/admin/trains?failedDeletion&error=no_such_train";
    }


}
