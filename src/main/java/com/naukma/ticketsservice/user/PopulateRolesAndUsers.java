package com.naukma.ticketsservice.user;


import org.springframework.beans.factory.annotation.Autowired;
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
        User admin = new User("admin", "123", "admin", "admin");
        admin.addRole(roleRepository.findByName("ADMIN").get());
        userRepository.save(admin);
    }
}
