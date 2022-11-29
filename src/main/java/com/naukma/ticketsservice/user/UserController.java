package com.naukma.ticketsservice.user;

import com.naukma.ticketsservice.exceptions.NoSuchWagonException;
import com.naukma.ticketsservice.train.Train;
import com.naukma.ticketsservice.wagon.Wagon;
import com.naukma.ticketsservice.wagon.WagonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class UserController {

    private final UserRepository repository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<User> addWagon(@RequestBody UserDto user){

        User u = new User(user.getEmail(), user.getPassword(), "admin", "admin");
        Optional<Role> adminRole = roleRepository.findByName("ADMIN");
        if (adminRole.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        u.addRole(adminRole.get());
        return new ResponseEntity<>(repository.save(u), HttpStatus.OK);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
