package com.naukma.ticketsservice.controllers;

import com.naukma.ticketsservice.aspects.LogExecTime;
import com.naukma.ticketsservice.user.User;
import com.naukma.ticketsservice.user.UserDto;
import com.naukma.ticketsservice.user.UserPrincipal;
import com.naukma.ticketsservice.user.UserPrincipalDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    private final UserPrincipalDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    public ProfileController(UserPrincipalDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    @LogExecTime
    public String index(Model model) {
        User user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        UserDto userDto = new UserDto(user.getEmail(), "", user.getFirstName(), user.getLastName());
        userDto.setId(user.getId());
        model.addAttribute("userDto", userDto);
        model.addAttribute("oldPassword", "");
        return "profile/profile";
    }

    @PostMapping("/profile/update")
    public String update(Model model,
                         @ModelAttribute("userDto") UserDto userDto,
                         @ModelAttribute("oldPassword") String oldPassword) {
        User user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return "redirect:/profile?not_correct_password";
        }


        userDetailsService.update(userDto.getId(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                userDto.getFirstName(), userDto.getLastName());

        model.addAttribute("userDto", userDto);
        return "redirect:/profile?successful_changed";
    }

}
