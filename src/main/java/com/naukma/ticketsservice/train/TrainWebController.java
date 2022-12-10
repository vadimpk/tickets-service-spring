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

    @GetMapping("/train")
    public String index(Model model) {
        List<Train> trains = trainService.getTrains();
        model.addAttribute("trains", trains);
        return "train/index";
    }

    @GetMapping("/train/create")
    public String create(Model model) {
        TrainDto train = new TrainDto(-1,"", 10, new HashSet<>(), new HashSet<>());
        model.addAttribute("train", train);
        return "train/create";
    }

    @PostMapping("/train")
    public String registration(@Valid @ModelAttribute("train") TrainDto trainDto,
                               BindingResult result,
                               Model model) {
        Optional<Train> existingTrain = trainService.find(trainDto.getName());

        if (existingTrain.isPresent()) {
            result.rejectValue("name", null,
                    "Name is not unique");
        }

        if (result.hasErrors()) {
            model.addAttribute("train", trainDto);
            return "/train/create";
        }

        Train train = new Train(trainDto.getName(), trainDto.getSpeed());
        trainService.save(train);
        return "redirect:/train/create?success";
    }

    @GetMapping("/train/update/{id}")
    public String update(Model model, @PathVariable long id) {
        Optional<Train> train = trainService.find(id);
        if (train.isEmpty()) throw new RuntimeException("not found train with id =" + id);

        model.addAttribute("train", train);
        return "/train/update";
    }

    @PostMapping("/train/update")
    public String update(@Valid @ModelAttribute("train") TrainDto trainDto,
                               BindingResult result,
                               Model model) {
        Optional<Train> trainToChange = trainService.find(trainDto.getId());
        if (trainToChange.isEmpty()) throw new RuntimeException("not found train with id=" + trainDto.getId());

        // check if new name is not unique
        Optional<Train> check = trainService.find(trainDto.getName());
        if (check.isPresent() && !check.get().getId().equals(trainDto.getId())) {
            result.rejectValue("name", null,
                    "Name is not unique");
        }
        if (result.hasErrors()) {
            model.addAttribute("train", trainDto);
            return "/train/update";
        }
        trainToChange.get().setName(trainDto.getName());
        trainToChange.get().setSpeed(trainDto.getSpeed());
        trainService.update(trainToChange.get().getId(), trainToChange.get());
        return "redirect:/train";
    }

    @GetMapping("/train/delete/{id}")
    public String delete(@PathVariable long id, Model model) {
        Optional<Train> train = trainService.find(id);
        if (train.isPresent()) {
            trainService.delete(id);
            return "redirect:/train";
        }
        return "redirect:?failedDeletion";
    }

}
