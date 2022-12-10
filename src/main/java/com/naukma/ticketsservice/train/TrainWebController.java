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

        // check if name is unique
        Optional<Train> check = trainService.find(train.getName());
        if (check.isPresent()) {
            result.rejectValue("name", null,
                    "Name is taken");
            return "redirect:?failedCreation&error=name_is_taken";
        }

        if (result.hasErrors()) {
            return "redirect:?failedCreation&error=bad_request";
        }

        trainService.create(new Train(train.getName(), train.getSpeed()));
        return "redirect:/admin/trains";
    }


    @PostMapping("/admin/trains/update/{id}")
    public String updateTrainFromAdminPanel(@Valid @ModelAttribute("newTrain") TrainDto train,
                                              @PathVariable Long id,
                                              BindingResult result,
                                              Model model) {

        // check if name is unique
        Optional<Train> check = trainService.find(id);
        if (check.isEmpty()) {
            result.rejectValue("name", null,
                    "No such obj");
            return "redirect:/admin/trains?failedUpdate&error=no_such_train";
        }

        // check if name is unique
        Optional<Train> checkName = trainService.find(train.getName());
        if (checkName.isPresent()) {
            result.rejectValue("name", null,
                    "Name is taken");
            return "redirect:/admin/trains?failedUpdate&error=name_is_taken";
        }

        if (result.hasErrors()) {
            return "redirect:/admin/trains?failedUpdate&error=bad_request";
        }

        check.get().setName(train.getName());
        check.get().setSpeed(train.getSpeed());

        trainService.update(check.get());
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
