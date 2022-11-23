package com.naukma.ticketsservice.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PopulateRolesAndUsers {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public PopulateRolesAndUsers(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;

        createRoles();
        createAdmins();
    }

    private void createRoles() {
        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));
    }

    private void createAdmins() {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        String p = b.encode("123");
        User admin = new User("admin", p, "admin", "admin");
        admin.addRole(roleRepository.findByName("ADMIN").get());
        userRepository.save(admin);
    }
}
