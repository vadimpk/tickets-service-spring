package com.naukma.ticketsservice.train;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
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
        TrainDto train = new TrainDto(-1,"", 10 );
        model.addAttribute("train", train);
        return "train/create";
    }

    @PostMapping("/train")
    public String registration(@Valid @ModelAttribute("train") TrainDto trainDto,
                               BindingResult result,
                               Model model) {
        Optional<Train> existingTrain = trainService.findTrainByName(trainDto.getName());

        if (existingTrain.isPresent()) {
            result.rejectValue("name", null,
                    "Name is not unique");
        }

        if (result.hasErrors()) {
            model.addAttribute("train", trainDto);
            return "/train/create";
        }

        Train train = new Train(trainDto.getName(), trainDto.getSpeed());
        trainService.createTrain(train);
        return "redirect:/train/create?success";
    }

    @GetMapping("/train/update/{name}")
    public String update(Model model, @PathVariable String name) {
        Optional<Train> train = trainService.findTrainByName(name);
        if (train.isEmpty()) throw new RuntimeException("not found train with name=" + name);

        model.addAttribute("train", train);
        return "/train/update";
    }

    @PostMapping("/train/update")
    public String update(@Valid @ModelAttribute("train") TrainDto trainDto,
                               BindingResult result,
                               Model model) {
        Optional<Train> trainToChange = trainService.findTrain(trainDto.getId());
        if (trainToChange.isEmpty()) throw new RuntimeException("not found train with id=" + trainDto.getId());

        // check if new name is not unique
        Optional<Train> check = trainService.findTrainByName(trainDto.getName());
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

    @GetMapping("/train/delete/{name}")
    public String delete(@PathVariable String name) {
        Optional<Train> train = trainService.findTrainByName(name);
        if (train.isEmpty()) throw new RuntimeException("not found train: " + name);

        trainService.delete(train.get().getId());
        return "redirect:/train";
    }

}
