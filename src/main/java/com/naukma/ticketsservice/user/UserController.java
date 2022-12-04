package com.naukma.ticketsservice.user;

import com.naukma.ticketsservice.exceptions.NoSuchWagonException;
import com.naukma.ticketsservice.train.Train;
import com.naukma.ticketsservice.wagon.Wagon;
import com.naukma.ticketsservice.wagon.WagonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class UserController {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserController(UserRepository repository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        Optional<User> existingUser = repository.findByEmail(userDto.getEmail());

        if(existingUser.isPresent()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getFirstName(), userDto.getLastName());
        user.addRole(roleRepository.findByName("USER").get());
        repository.save(user);
        return "redirect:/register?success";
    }


}