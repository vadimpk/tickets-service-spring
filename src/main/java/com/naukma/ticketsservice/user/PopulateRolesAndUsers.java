package com.naukma.ticketsservice.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PopulateRolesAndUsers {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PopulateRolesAndUsers(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        createRoles();
        createAdmins();
        createUser();
    }

    private void createRoles() {
        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));
    }

    private void createAdmins() {
        User admin = new User("admin@mail", passwordEncoder.encode("123"), "admin", "admin");
        admin.addRole(roleRepository.findByName("ADMIN").get());
        userRepository.save(admin);
    }

    private void createUser() {
        User admin = new User("user@mail", passwordEncoder.encode("123"), "user", "user");
        admin.addRole(roleRepository.findByName("USER").get());
        userRepository.save(admin);
    }
}
